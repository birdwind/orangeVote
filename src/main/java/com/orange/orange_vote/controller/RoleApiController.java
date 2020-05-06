package com.orange.orange_vote.controller;

import com.orange.orange_vote.entity.service.RoleService;
import com.orange.orange_vote.view.role.RoleResource;
import com.orange.orange_vote.view.role.converter.RoleListItemConverter;
import com.orange.orange_vote.view.role.converter.RoleResourcePacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = {"/api/role"}, produces = "application/json;charset=utf-8")
@Api(tags = "角色管理模組")
public class RoleApiController {

    @Autowired
    private RoleResourcePacker roleResourcePacker;

    @Autowired
    private RoleListItemConverter roleListItemConverter;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "獲取角色清單")
    @GetMapping(value = "/list")
    public RoleResource getRoleList() {
        return roleResourcePacker.pack(roleListItemConverter.convert(roleService.getAllRoles()));
    }
}
