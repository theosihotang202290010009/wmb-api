package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.menu.NewMenuRequest;
import com.enigma.wmb.dto.request.menu.SearchMenuRequest;
import com.enigma.wmb.dto.request.menu.UpdateMenuRequest;
import com.enigma.wmb.dto.response.MenuResponse;
import org.springframework.data.domain.Page;

public interface MenuService {
    MenuResponse createMenu(NewMenuRequest request);
    Page<MenuResponse> getAll(SearchMenuRequest request);
    MenuResponse updateMenu(UpdateMenuRequest request);
    MenuResponse findByIdMenu(String id);
    void delete(String id);
}
