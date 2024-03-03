package com.enigma.wmb.service.impl;

import com.enigma.wmb.dto.request.tables.NewTablesRequest;
import com.enigma.wmb.dto.request.tables.SearchTablesRequest;
import com.enigma.wmb.dto.request.tables.UpdateTablesRequest;
import com.enigma.wmb.dto.response.TablesResponse;
import com.enigma.wmb.entity.Tables;
import com.enigma.wmb.repository.TablesRepository;
import com.enigma.wmb.service.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TablesServiceImpl implements TablesService {
    private final TablesRepository tablesRepository;
    @Override
    public TablesResponse create(NewTablesRequest request) {
        Tables table = Tables.builder()
                .name(request.getName())
                .isEmpty(request.getIsEmpty())
                .build();
        Tables save = tablesRepository.saveAndFlush(table);
        return TablesResponse.builder()
                .name(save.getName())
                .isEmpty(save.getIsEmpty())
                .build();
    }

    @Override
    public List<TablesResponse> getAll(SearchTablesRequest request) {
        List<Tables> all = tablesRepository.findAll();
        return all.stream().map(tables -> TablesResponse.builder()
                .name(tables.getName())
                .isEmpty(tables.getIsEmpty())
                .build()).toList();
    }

    @Override
    public TablesResponse getById(String id) {
        Tables tables = tablesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find data with ID " + id));
        return TablesResponse.builder()
                .name(tables.getName())
                .isEmpty(tables.getIsEmpty())
                .build();

    }

    @Override
    public TablesResponse updateTable(UpdateTablesRequest request) {
        TablesResponse table = getById(request.getId());
        table.setName(request.getName());
        table.setIsEmpty(request.getIsEmpty());
        Tables updateTable = Tables.builder()
                .id(request.getId())
                .name(table.getName())
                .isEmpty(table.getIsEmpty())
                .build();
        Tables newTable = tablesRepository.saveAndFlush(updateTable);
        return TablesResponse.builder()
                .name(newTable.getName())
                .isEmpty(newTable.getIsEmpty())
                .build();
    }

    @Override
    public void delete(String id) {
        TablesResponse table = getById(id);
        Tables deleteTable = Tables.builder()
                .id(id)
                .name(table.getName())
                .isEmpty(table.getIsEmpty())
                .build();
        tablesRepository.delete(deleteTable);
    }
}
