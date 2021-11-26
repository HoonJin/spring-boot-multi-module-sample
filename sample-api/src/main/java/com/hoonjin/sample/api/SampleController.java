package com.hoonjin.sample.api;

import com.hoonjin.sample.domain.GlobalResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/")
    public GlobalResponse home() {
        return new GlobalResponse("success");
    }
}
