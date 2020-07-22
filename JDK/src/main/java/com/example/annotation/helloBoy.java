package com.example.annotation;

import org.springframework.stereotype.Service;

@Service
public class helloBoy {
    @Boy("chenhao")
    String a ;
    public void sayHello(){
        System.out.println("hello"+a);
    }
}
