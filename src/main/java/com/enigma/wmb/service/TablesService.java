package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.tables.NewTablesRequest;
import com.enigma.wmb.dto.request.tables.SearchTablesRequest;
import com.enigma.wmb.dto.request.tables.UpdateTablesRequest;
import com.enigma.wmb.dto.response.TablesResponse;

import java.util.List;

public interface TablesService {
    TablesResponse create(NewTablesRequest request);
    List<TablesResponse> getAll(SearchTablesRequest request);
    TablesResponse getById(String id);
    TablesResponse updateTable(UpdateTablesRequest request);
    void delete(String id);
}
