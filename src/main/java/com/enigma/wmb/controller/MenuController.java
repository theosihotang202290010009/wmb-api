package com.enigma.wmb.controller;

import com.enigma.wmb.constant.APIUrl;
import com.enigma.wmb.dto.request.menu.NewMenuRequest;
import com.enigma.wmb.dto.request.menu.SearchMenuRequest;
import com.enigma.wmb.dto.request.menu.UpdateMenuRequest;
import com.enigma.wmb.dto.response.CommonResponse;
import com.enigma.wmb.dto.response.MenuResponse;
import com.enigma.wmb.dto.response.PagingResponse;
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
    public ResponseEntity<CommonResponse<MenuResponse>> createMenu(@RequestBody NewMenuRequest request) {
        MenuResponse menu = menuService.createMenu(request);
        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
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
    public ResponseEntity<CommonResponse<List<MenuResponse>>> getAll(
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
        Page<MenuResponse> allMenu = menuService.getAll(request);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allMenu.getTotalPages())
                .totalElement(allMenu.getTotalElements())
                .page(allMenu.getPageable().getPageNumber() + 1)
                .size(allMenu.getPageable().getPageSize())
                .hasNext(allMenu.hasNext())
                .hasPrevious(allMenu.hasPrevious())
                .build();
        CommonResponse<List<MenuResponse>> response = CommonResponse.<List<MenuResponse>>builder()
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
    public ResponseEntity<CommonResponse<MenuResponse>> findById(@PathVariable String id) {
        MenuResponse menu = menuService.findByIdMenu(id);
        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
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
    public ResponseEntity<CommonResponse<MenuResponse>> update(@RequestBody UpdateMenuRequest request) {
        MenuResponse menu = menuService.updateMenu(request);
        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
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
