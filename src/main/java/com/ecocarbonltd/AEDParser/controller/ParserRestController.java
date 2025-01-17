package com.ecocarbonltd.AEDParser.controller;

import com.ecocarbonltd.AEDParser.service.ParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParserRestController {
    private final ParserService service;

    @GetMapping("/{date}")
    public Map<String, String> getCurrencies(@PathVariable String date){
        return service.getCourse(date);
    }
}
