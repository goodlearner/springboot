package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anyho on 2019/3/7.
 */
@RestController
public class HelloWorldController
{
    @RequestMapping("/hello")
    public String index()
    {
        return "Hello Springboot!";
    }
}
