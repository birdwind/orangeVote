package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.system.SystemResource;
import com.orange.orange_vote.base.system.converter.SystemResourcePacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    @PostMapping(value = "/login")
    public SystemResource login(){

        return systemResourcePacker.pack();
    }

}
