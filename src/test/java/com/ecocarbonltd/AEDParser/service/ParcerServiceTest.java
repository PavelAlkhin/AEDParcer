package com.ecocarbonltd.AEDParser.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class ParcerServiceTest {
    @Autowired
    private ParserService service;

    @Test
    @DisplayName("2022-11-1")
    void getCourse1() {
        Map<String, String> course2 = service.getCourse("2022-11-1");
        course2.forEach((key,value)->System.out.println(key+":"+value));
    }

    @Test
    @DisplayName("2022-11-01")
    void getCourse2() {
        Map<String, String> course3 = service.getCourse("2022-11-10");
        course3.forEach((key,value)->System.out.println(key+":"+value));
    }

    @Test
    @DisplayName("2022-11-01")
    void getCourse3() {
        Map<String, String> course3 = service.getCourse("2022-11-01");
        course3.forEach((key,value)->System.out.println(key+":"+value));
    }
}