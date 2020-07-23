package am.davsoft.sfl_assessment.rest.v1;

import am.davsoft.sfl_assessment.dto.product.ProductDto;
import am.davsoft.sfl_assessment.dto.table.CafeTableDto;
import am.davsoft.sfl_assessment.dto.table.TableAssignmentDto;
import am.davsoft.sfl_assessment.dto.user.ManagerNewUserDto;
import am.davsoft.sfl_assessment.dto.user.ManagerUserDto;
import am.davsoft.sfl_assessment.model.CafeTable;
import am.davsoft.sfl_assessment.model.Product;
import am.davsoft.sfl_assessment.model.User;
import am.davsoft.sfl_assessment.rest.api.ManagerRestController;
import am.davsoft.sfl_assessment.service.ProductService;
import am.davsoft.sfl_assessment.service.TableService;
import am.davsoft.sfl_assessment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@RestController
@RequestMapping(value = "/api/v1/manager")
public class ManagerRestControllerV1 implements ManagerRestController {
    private final UserService userService;
    private final TableService tableService;
    private final ProductService productService;

    public ManagerRestControllerV1(UserService userService, TableService tableService, ProductService productService) {
        this.userService = userService;
        this.tableService = tableService;
        this.productService = productService;
    }

    //region Users
    @Override
    public ResponseEntity<List<ManagerUserDto>> getAllUsers() {
        List<User> userList = userService.getAll();
        if (userList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userList.stream().map(ManagerUserDto::fromModel).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ManagerUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ManagerUserDto.fromModel(user));
    }

    @Override
    public ResponseEntity<ManagerUserDto> createNewUser(@RequestBody ManagerNewUserDto managerNewUserDto, HttpServletRequest request) {
        User saved = userService.register(managerNewUserDto.toModel());
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/users/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }
    //endregion

    //region Tables
    @Override
    public ResponseEntity<List<CafeTableDto>> getAllTables() {
        List<CafeTable> cafeTableList = tableService.getAll();
        if (cafeTableList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cafeTableList.stream().map(CafeTableDto::fromModel).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<CafeTableDto>> getAllTablesByWaiterId(@PathVariable(name = "id") Long id) {
        List<CafeTable> cafeTableList = tableService.findAllByWaiterId(id);
        if (cafeTableList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cafeTableList.stream().map(CafeTableDto::fromModel).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<CafeTableDto> getTableById(@PathVariable(name = "id") Long id) {
        CafeTable cafeTable = tableService.findById(id);
        if (cafeTable == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CafeTableDto.fromModel(cafeTable));
    }

    @Override
    public ResponseEntity<CafeTableDto> createNewTable(@RequestBody CafeTableDto cafeTableDto, HttpServletRequest request) {
        CafeTable saved = tableService.create(cafeTableDto.toModel());
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/tables/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<CafeTableDto> assignTable(@RequestBody TableAssignmentDto assignmentDto) {
        CafeTable cafeTable = tableService.findById(assignmentDto.getTableId());
        if (cafeTable == null) {
            return ResponseEntity.badRequest().build();
        }
        User waiter = userService.findById(assignmentDto.getWaiterId());
        if (waiter == null) {
            return ResponseEntity.badRequest().build();
        }
        cafeTable.setWaiter(waiter);
        CafeTable updatedTable = tableService.update(cafeTable);
        return ResponseEntity.ok(CafeTableDto.fromModel(updatedTable));
    }
    //endregion

    //region Products
    @Override
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> productList = productService.getAll();
        if (productList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productList.stream().map(ProductDto::fromModel).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ProductDto.fromModel(product));
    }

    @Override
    public ResponseEntity<ProductDto> createNewProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {
        Product saved = productService.create(productDto.toModel());
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/tables/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }
    //endregion
}
