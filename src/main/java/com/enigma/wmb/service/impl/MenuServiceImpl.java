package com.enigma.wmb.service.impl;

import com.enigma.wmb.ValidatorUtil;
import com.enigma.wmb.dto.request.Menu.NewMenuRequest;
import com.enigma.wmb.dto.request.Menu.SearchMenuRequest;
import com.enigma.wmb.dto.request.Menu.UpdateMenuRequest;
import com.enigma.wmb.dto.response.MenuResponse;
import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.repository.MenuRepository;
import com.enigma.wmb.service.MenuService;
import com.enigma.wmb.specification.MenuSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final ValidatorUtil validatorUtil;

    @Override
    public MenuResponse createMenu(NewMenuRequest request) {
        validatorUtil.validate(request);
        Menu menu = Menu.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        Menu menuSave = menuRepository.saveAndFlush(menu);
        return MenuResponse.builder()
                .id(menuSave.getId())
                .name(menuSave.getName())
                .price(menuSave.getPrice())
                .build();
    }

    @Override
    public Page<MenuResponse> getAll(SearchMenuRequest request) {
        if (request.getPage() <= 0) request.setPage(1);
        Specification<Menu> specification = MenuSpecification.getSpecification(request);
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize(),sort);
        Page<Menu> getAll = menuRepository.findAll(specification, pageable);
        return getAll.map(menu -> MenuResponse.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .build());

    }

    @Override
    public MenuResponse updateMenu(UpdateMenuRequest request) {
        MenuResponse menuResponse = findByIdMenu(request.getId());

        menuResponse.setName(request.getName());
        menuResponse.setPrice(request.getPrice());
        Menu menuToUpdate = Menu.builder()
                .id(menuResponse.getId())
                .name(menuResponse.getName())
                .price(menuResponse.getPrice())
                .build();

        Menu updatedMenu = menuRepository.saveAndFlush(menuToUpdate);

        return MenuResponse.builder()
                .id(updatedMenu.getId())
                .name(updatedMenu.getName())
                .price(updatedMenu.getPrice())
                .build();
    }

    @Override
    public MenuResponse findByIdMenu(String id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't found ID " + id));
        return MenuResponse.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .build();
    }

    @Override
    public void delete(String id) {
        MenuResponse menuResponse = findByIdMenu(id);
        Menu menu = Menu.builder()
                .id(menuResponse.getId())
                .name(menuResponse.getName())
                .price(menuResponse.getPrice())
                .build();
        menuRepository.delete(menu);
    }
}
