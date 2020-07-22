package com.example.study;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Testutil {
    @LocalCache
    int[] get(String a, List b){
        return new int[]{6,7,8,9};
    }
    int[] set(String a, int b){
        return new int[]{8,9};
    }
}
