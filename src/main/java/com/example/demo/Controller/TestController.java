package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("testapi")
public class TestController {

    @GetMapping("test")
    public String test(){
        return "test is succsefull";
    }
}
