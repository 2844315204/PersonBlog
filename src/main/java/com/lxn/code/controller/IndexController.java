package com.lxn.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/type")
    public String type(){

        return "admin/types";
    }
    @GetMapping("/types")
    public String types(){

        return "admin/types-input";
    }

    @GetMapping("/")
    public String index(){

        return "admin/login";
    }
    @GetMapping("/index")
    public String indexs(){

        return "index";
    }
}
