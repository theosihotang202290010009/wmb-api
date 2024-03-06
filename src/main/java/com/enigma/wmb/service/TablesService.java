package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.tables.NewTablesRequest;
import com.enigma.wmb.dto.request.tables.SearchTablesRequest;
import com.enigma.wmb.dto.request.tables.UpdateTablesRequest;
import com.enigma.wmb.dto.response.TablesResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TablesService {
    TablesResponse create(NewTablesRequest request);
    Page<TablesResponse> getAll(SearchTablesRequest request);
    TablesResponse getById(String id);
    TablesResponse updateTable(UpdateTablesRequest request);
    void delete(String id);
}
