package com.ecocarbonltd.AEDParcer.controller;

import com.ecocarbonltd.AEDParcer.service.ParcerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParcerRestController {
    private final ParcerService service;

    @GetMapping("/{date}")
    public Map<String, String> getCurrecies(@PathVariable String date){
        return service.getCourse(date);
    }
}
