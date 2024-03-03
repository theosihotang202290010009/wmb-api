package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.Menu.NewMenuRequest;
import com.enigma.wmb.dto.request.Menu.SearchMenuRequest;
import com.enigma.wmb.dto.request.Menu.UpdateMenuRequest;
import com.enigma.wmb.dto.response.MenuResponse;
import com.enigma.wmb.entity.Menu;
import org.springframework.data.domain.Page;

public interface MenuService {
    MenuResponse createMenu(NewMenuRequest request);
    Page<MenuResponse> getAll(SearchMenuRequest request);
    MenuResponse updateMenu(UpdateMenuRequest request);
    MenuResponse findByIdMenu(String id);
    void delete(String id);
}
