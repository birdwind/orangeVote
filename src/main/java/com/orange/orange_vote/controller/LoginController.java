package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.system.SystemResource;
import com.orange.orange_vote.base.system.converter.SystemResourcePacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api"})
public class LoginController {

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    @GetMapping(value = "/test")
    public SystemResource login(){

        return systemResourcePacker.pack();
    }

}
