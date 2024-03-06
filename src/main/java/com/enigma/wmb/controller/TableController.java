package com.enigma.wmb.controller;

import com.enigma.wmb.constant.APIUrl;
import com.enigma.wmb.dto.request.tables.NewTablesRequest;
import com.enigma.wmb.dto.request.tables.SearchTablesRequest;
import com.enigma.wmb.dto.response.CommonResponse;
import com.enigma.wmb.dto.response.PagingResponse;
import com.enigma.wmb.dto.response.TablesResponse;
import com.enigma.wmb.service.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = APIUrl.API_TABLES)
@RequiredArgsConstructor
public class TableController {
    private final TablesService tablesService;
    @PostMapping
    public ResponseEntity<CommonResponse<TablesResponse>> create(@RequestBody NewTablesRequest request){
        TablesResponse tablesResponse = tablesService.create(request);
        CommonResponse<TablesResponse> response = CommonResponse.<TablesResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully created new table")
                .data(tablesResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TablesResponse>>> getAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ){
        SearchTablesRequest request = SearchTablesRequest.builder()
                .name(name)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        Page<TablesResponse> getAll = tablesService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(getAll.getTotalPages())
                .totalElement(getAll.getTotalElements())
                .page(getAll.getPageable().getPageNumber() + 1)
                .size(getAll.getPageable().getPageSize())
                .hasNext(getAll.hasNext())
                .hasPrevious(getAll.hasPrevious())
                .build();

        CommonResponse<List<TablesResponse>> response = CommonResponse.<List<TablesResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully get data tables")
                .data(getAll.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    //lanjutin sisanya besok
}
