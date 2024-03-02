package com.enigma.wmb.controller;

import com.enigma.wmb.constant.APIUrl;
import com.enigma.wmb.dto.request.Menu.NewMenuRequest;
import com.enigma.wmb.dto.request.Menu.SearchMenuRequest;
import com.enigma.wmb.dto.request.Menu.UpdateMenuRequest;
import com.enigma.wmb.dto.response.CommonResponse;
import com.enigma.wmb.dto.response.PagingResponse;
import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = APIUrl.API_MENU)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<Menu>> createMenu(@RequestBody NewMenuRequest request) {
        Menu menu = menuService.createMenu(request);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully created new menu")
                .data(menu)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<Menu>>> getAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price", required = false) Long price,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        SearchMenuRequest request = SearchMenuRequest.builder()
                .name(name)
                .price(price)
                .page(page)
                .sortBy(sortBy)
                .direction(direction)
                .size(size)
                .build();
        Page<Menu> allMenu = menuService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allMenu.getTotalPages())
                .totalElement(allMenu.getTotalElements())
                .page(allMenu.getPageable().getPageNumber() + 1)
                .size(allMenu.getPageable().getPageSize())
                .hasNext(allMenu.hasNext())
                .hasPrevious(allMenu.hasPrevious())
                .build();
        CommonResponse<List<Menu>> response = CommonResponse.<List<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully get all data")
                .paging(pagingResponse)
                .data(allMenu.getContent())
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<Menu>> findById(@PathVariable String id) {
        Menu menu = menuService.findByIdMenu(id);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully find data with ID " + id)
                .data(menu)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<Menu>> update(@RequestBody UpdateMenuRequest request) {
        Menu menu = menuService.updateMenu(request);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully update data with ID " + request.getId())
                .data(menu)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable String id) {
        menuService.delete(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully delete data with ID " + id)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

}
