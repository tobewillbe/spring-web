package com.example.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@ResponseBody // 응답을 할 때 html을 응답하는 것이
public class RestBasicController {

    @GetMapping("/china")
    public List<String> food(){
        List<String> foodList
                = Arrays.asList("마파두부", "꿔바로우", "마라탕");

        return foodList;
    }
}
