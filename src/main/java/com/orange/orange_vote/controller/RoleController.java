package com.orange.orange_vote.controller;

import com.orange.orange_vote.entity.service.RoleService;
import com.orange.orange_vote.view.role.converter.RoleListItemConverter;
import com.orange.orange_vote.view.role.converter.RoleResourcePacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/role"})
public class RoleController {

    @Autowired
    private RoleResourcePacker roleResourcePacker;

    @Autowired
    private RoleListItemConverter roleListItemConverter;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/list")
    public String getRoleList() {
        return roleResourcePacker.pack(roleListItemConverter.convert(roleService.getAllRoles())).toJson();
    }
}
