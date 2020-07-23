package am.davsoft.sfl_assessment.rest.api;

import am.davsoft.sfl_assessment.dto.order.CafeOrderDto;
import am.davsoft.sfl_assessment.dto.order.NewOrderDto;
import am.davsoft.sfl_assessment.dto.product.NewProductInOrderDto;
import am.davsoft.sfl_assessment.dto.product.RemoveProductInOrderDto;
import am.davsoft.sfl_assessment.dto.table.CafeTableDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 23, 2020
 */
@RequestMapping(value = "/api/default/waiter")
public interface WaiterRestController {
    @GetMapping(value = "/tables")
    ResponseEntity<List<CafeTableDto>> getAllTables(HttpServletRequest request);

    @GetMapping(value = "/tables/{id}")
    ResponseEntity<CafeTableDto> getTableById(@PathVariable(name = "id") Long id, HttpServletRequest request);

    @PostMapping(value = "/tables/free/{id}")
    ResponseEntity<CafeTableDto> freeTableById(@PathVariable(name = "id") Long id, HttpServletRequest request);

    @PostMapping(value = "/tables/reserve/{id}")
    ResponseEntity<CafeTableDto> reserveTableById(@PathVariable(name = "id") Long id, HttpServletRequest request);

    @PostMapping(value = "/tables/occupy/{id}")
    ResponseEntity<CafeTableDto> occupyTableById(@PathVariable(name = "id") Long id, HttpServletRequest request);

    @GetMapping(value = "/orders")
    ResponseEntity<List<CafeOrderDto>> getAllOrders(HttpServletRequest request);

    @GetMapping(value = "/orders/{id}")
    ResponseEntity<CafeOrderDto> getOrderById(@PathVariable(name = "id") Long id, HttpServletRequest request);

    @PostMapping(value = "/orders/create")
    ResponseEntity<CafeTableDto> createOrder(@RequestBody NewOrderDto newOrderDto, HttpServletRequest request);

    @PostMapping(value = "/orders/close/{id}")
    ResponseEntity<CafeOrderDto> closeOrderById(@PathVariable(name = "id") Long id, HttpServletRequest request);

    @PostMapping(value = "/orders/cancel/{id}")
    ResponseEntity<CafeOrderDto> cancelOrderById(@PathVariable(name = "id") Long id, HttpServletRequest request);

    @PostMapping(value = "/orders/products/add")
    ResponseEntity<CafeOrderDto> addProductToOrder(@RequestBody NewProductInOrderDto newProductInOrderDto, HttpServletRequest request);

    @PostMapping(value = "/orders/products/remove")
    ResponseEntity<CafeOrderDto> removeProductFromOrder(@RequestBody RemoveProductInOrderDto removeProductInOrderDto, HttpServletRequest request);
}
