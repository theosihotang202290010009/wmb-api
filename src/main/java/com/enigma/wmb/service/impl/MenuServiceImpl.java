package com.enigma.wmb.service.impl;

import com.enigma.wmb.ValidatorUtil;
import com.enigma.wmb.dto.request.Menu.NewMenuRequest;
import com.enigma.wmb.dto.request.Menu.SearchMenuRequest;
import com.enigma.wmb.dto.request.Menu.UpdateMenuRequest;
import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.repository.MenuRepository;
import com.enigma.wmb.service.MenuService;
import com.enigma.wmb.specification.MenuSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Menu createMenu(NewMenuRequest request) {
        validatorUtil.validate(request);
        Menu menu = Menu.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public Page<Menu> getAll(SearchMenuRequest request) {
        if (request.getPage() <= 0) request.setPage(1);
        Specification<Menu> specification = MenuSpecification.getSpecification(request);
        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize());
        return menuRepository.findAll(specification,pageable);
    }

    @Override
    public Menu updateMenu(UpdateMenuRequest request) {
        Menu menuUpdate = findByIdMenu(request.getId());
        return menuRepository.saveAndFlush(menuUpdate);
    }

    @Override
    public Menu findByIdMenu(String id) {
        return menuRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't found ID "+ id));
    }

    @Override
    public void delete(String id) {
        Menu menuDelete = findByIdMenu(id);
        menuRepository.delete(menuDelete);
    }
}
