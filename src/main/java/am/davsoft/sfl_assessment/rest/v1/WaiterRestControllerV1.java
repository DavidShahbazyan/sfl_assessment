package am.davsoft.sfl_assessment.rest.v1;

import am.davsoft.sfl_assessment.dto.order.CafeOrderDto;
import am.davsoft.sfl_assessment.dto.order.NewOrderDto;
import am.davsoft.sfl_assessment.dto.product.NewProductInOrderDto;
import am.davsoft.sfl_assessment.dto.product.ProductInOrderDto;
import am.davsoft.sfl_assessment.dto.product.RemoveProductInOrderDto;
import am.davsoft.sfl_assessment.dto.table.CafeTableDto;
import am.davsoft.sfl_assessment.model.*;
import am.davsoft.sfl_assessment.service.OrderService;
import am.davsoft.sfl_assessment.service.ProductService;
import am.davsoft.sfl_assessment.service.TableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@RestController
@RequestMapping(value = "/api/v1/waiter")
public class WaiterRestControllerV1 {
    private final TableService tableService;
    private final OrderService orderService;
    private final ProductService productService;

    public WaiterRestControllerV1(TableService tableService, OrderService orderService, ProductService productService) {
        this.tableService = tableService;
        this.orderService = orderService;
        this.productService = productService;
    }

    private CafeOrder loadOrderByIdAndWaiterId(Long id, HttpServletRequest request) {
        return orderService.findByIdAndWaiterId(id, ((User) request.getUserPrincipal()).getId());
    }

    private CafeTable loadTableByIdAndWaiterId(Long id, HttpServletRequest request) {
        return tableService.findByIdAndWaiterId(id, ((User) request.getUserPrincipal()).getId());
    }


    @GetMapping(value = "/tables")
    public ResponseEntity<List<CafeTableDto>> getAllTables(HttpServletRequest request) {
        List<CafeTable> cafeTableList = tableService.findAllByWaiterId(((User) request.getUserPrincipal()).getId());
        if (cafeTableList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cafeTableList.stream().map(CafeTableDto::fromModel).collect(Collectors.toList()));
    }

    @GetMapping(value = "/tables/{id}")
    public ResponseEntity<CafeTableDto> getTableById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        CafeTable cafeTable = loadTableByIdAndWaiterId(id, request);
        if (cafeTable == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CafeTableDto.fromModel(cafeTable));
    }

    @PostMapping(value = "/tables/free/{id}")
    public ResponseEntity<CafeTableDto> freeTableById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        CafeTable cafeTable = loadTableByIdAndWaiterId(id, request);
        if (cafeTable == null) {
            return ResponseEntity.noContent().build();
        }
        cafeTable.setTableState(TableState.FREE);
        return ResponseEntity.ok(CafeTableDto.fromModel(tableService.update(cafeTable)));
    }

    @PostMapping(value = "/tables/reserve/{id}")
    public ResponseEntity<CafeTableDto> reserveTableById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        CafeTable cafeTable = loadTableByIdAndWaiterId(id, request);
        if (cafeTable == null) {
            return ResponseEntity.noContent().build();
        }
        cafeTable.setTableState(TableState.RESERVED);
        return ResponseEntity.ok(CafeTableDto.fromModel(tableService.update(cafeTable)));
    }

    @PostMapping(value = "/tables/occupy/{id}")
    public ResponseEntity<CafeTableDto> occupyTableById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        CafeTable cafeTable = loadTableByIdAndWaiterId(id, request);
        if (cafeTable == null) {
            return ResponseEntity.noContent().build();
        }
        cafeTable.setTableState(TableState.OCCUPIED);
        return ResponseEntity.ok(CafeTableDto.fromModel(tableService.update(cafeTable)));
    }

    @GetMapping(value = "/orders")
    public ResponseEntity<List<CafeOrderDto>> getAllOrders(HttpServletRequest request) {
        List<CafeOrder> cafeOrderList = orderService.findAllByWaiterId(((User) request.getUserPrincipal()).getId());
        if (cafeOrderList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cafeOrderList.stream().map(CafeOrderDto::fromModel).collect(Collectors.toList()));
    }

    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<CafeOrderDto> getOrderById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        CafeOrder cafeOrder = loadOrderByIdAndWaiterId(id, request);
        if (cafeOrder == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CafeOrderDto.fromModel(cafeOrder));
    }

    @PostMapping(value = "/orders/create")
    public ResponseEntity<CafeTableDto> createOrder(@RequestBody NewOrderDto newOrderDto, HttpServletRequest request) {
        CafeTable cafeTable = loadTableByIdAndWaiterId(newOrderDto.getTableId(), request);
        if (cafeTable == null) {
            return ResponseEntity.badRequest().build();
        }
        if (TableState.OCCUPIED.equals(cafeTable.getTableState())) {
            return ResponseEntity.badRequest().build();
        }
        cafeTable.setTableState(TableState.OCCUPIED);
        CafeOrder order = new CafeOrder();
        order.setOrderState(OrderState.OPEN);
        order.setWaiter(cafeTable.getWaiter());
        cafeTable.setTableOrder(orderService.create(order));
        CafeTable saved = tableService.update(cafeTable);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/orders/{id}")
                        .buildAndExpand(saved.getTableOrder().getId())
                        .toUri())
                .build();
    }

    @PostMapping(value = "/orders/close/{id}")
    public ResponseEntity<CafeOrderDto> closeOrderById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        CafeOrder cafeOrder = loadOrderByIdAndWaiterId(id, request);
        if (cafeOrder == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!OrderState.OPEN.equals(cafeOrder.getOrderState())) {
            return ResponseEntity.badRequest().build();
        }
        cafeOrder.calculateTotalAmount();
        cafeOrder.setOrderState(OrderState.CLOSED);
        return ResponseEntity.ok(CafeOrderDto.fromModel(orderService.update(cafeOrder)));
    }

    @PostMapping(value = "/orders/cancel/{id}")
    public ResponseEntity<CafeOrderDto> cancelOrderById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        CafeOrder cafeOrder = loadOrderByIdAndWaiterId(id, request);
        if (cafeOrder == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!OrderState.OPEN.equals(cafeOrder.getOrderState())) {
            return ResponseEntity.badRequest().build();
        }
        cafeOrder.calculateTotalAmount();
        cafeOrder.setOrderState(OrderState.CANCELED);
        return ResponseEntity.ok(CafeOrderDto.fromModel(orderService.update(cafeOrder)));
    }

    @PostMapping(value = "/orders/products/add")
    public ResponseEntity<CafeOrderDto> addProductToOrder(@RequestBody NewProductInOrderDto newProductInOrderDto, HttpServletRequest request) {
        CafeOrder order = loadOrderByIdAndWaiterId(newProductInOrderDto.getOrderId(), request);
        if (!OrderState.OPEN.equals(order.getOrderState())) {
            return ResponseEntity.badRequest().build();
        }
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setProduct(productService.findById(newProductInOrderDto.getProductId()));
        productInOrder.setAmount(newProductInOrderDto.getAmount());
        List<ProductInOrder> orderedProductsList = order.getOrderedProducts();
        if (orderedProductsList == null) {
            orderedProductsList = new ArrayList<>();
        }
        orderedProductsList.add(productInOrder);
        order.setOrderedProducts(orderedProductsList);
        return ResponseEntity.ok(CafeOrderDto.fromModel(orderService.update(order)));
    }

    @PostMapping(value = "/orders/products/remove")
    public ResponseEntity<CafeOrderDto> removeProductFromOrder(@RequestBody RemoveProductInOrderDto removeProductInOrderDto, HttpServletRequest request) {
        CafeOrder order = loadOrderByIdAndWaiterId(removeProductInOrderDto.getOrderId(), request);
        if (!OrderState.OPEN.equals(order.getOrderState())) {
            return ResponseEntity.badRequest().build();
        }
        if (order.getOrderedProducts() != null && !order.getOrderedProducts().isEmpty()) {
            order.getOrderedProducts().removeIf(productInOrder -> productInOrder.getId().equals(removeProductInOrderDto.getProductInOrderId()));
        }
        return ResponseEntity.ok(CafeOrderDto.fromModel(orderService.update(order)));
    }
}
