package am.davsoft.sfl_assessment.rest.api;

import am.davsoft.sfl_assessment.dto.product.ProductDto;
import am.davsoft.sfl_assessment.dto.table.CafeTableDto;
import am.davsoft.sfl_assessment.dto.table.TableAssignmentDto;
import am.davsoft.sfl_assessment.dto.user.ManagerNewUserDto;
import am.davsoft.sfl_assessment.dto.user.ManagerUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 23, 2020
 */
@RequestMapping(value = "/api/default/manager")
public interface ManagerRestController {
    @GetMapping(value = "/users")
    ResponseEntity<List<ManagerUserDto>> getAllUsers();

    @GetMapping(value = "/users/{id}")
    ResponseEntity<ManagerUserDto> getUserById(@PathVariable(name = "id") Long id);

    @PostMapping(value = "/users/create")
    ResponseEntity<ManagerUserDto> createNewUser(@RequestBody ManagerNewUserDto managerNewUserDto, HttpServletRequest request);

    @GetMapping(value = "/tables")
    ResponseEntity<List<CafeTableDto>> getAllTables();

    @GetMapping(value = "/tables/waiter/{id}")
    ResponseEntity<List<CafeTableDto>> getAllTablesByWaiterId(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/tables/{id}")
    ResponseEntity<CafeTableDto> getTableById(@PathVariable(name = "id") Long id);

    @PostMapping(value = "/tables/create")
    ResponseEntity<CafeTableDto> createNewTable(@RequestBody CafeTableDto cafeTableDto, HttpServletRequest request);

    @PostMapping(value = "/tables/assign")
    ResponseEntity<CafeTableDto> assignTable(@RequestBody TableAssignmentDto assignmentDto);

    @GetMapping(value = "/products")
    ResponseEntity<List<ProductDto>> getAllProducts();

    @GetMapping(value = "/products/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") Long id);

    @PostMapping(value = "/products/create")
    ResponseEntity<ProductDto> createNewProduct(@RequestBody ProductDto productDto, HttpServletRequest request);
}
