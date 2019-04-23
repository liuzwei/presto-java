package com.aura.prestojava.controller;

import com.aura.prestojava.service.PrestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrestoController {

    @Autowired
    PrestoService prestoService;


    @RequestMapping("/brand")
    public String getUrl(){

        return prestoService.selectBrandPrice();
    }

}
