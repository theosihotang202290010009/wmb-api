package com.enigma.wmb.service;

import com.enigma.wmb.dto.request.Menu.NewMenuRequest;
import com.enigma.wmb.dto.request.Menu.SearchMenuRequest;
import com.enigma.wmb.dto.request.Menu.UpdateMenuRequest;
import com.enigma.wmb.entity.Menu;
import org.springframework.data.domain.Page;

public interface MenuService {
    Menu createMenu(NewMenuRequest request);
    Page<Menu> getAll(SearchMenuRequest request);
    Menu updateMenu(UpdateMenuRequest request);
    Menu findByIdMenu(String id);
    void delete(String id);
}
