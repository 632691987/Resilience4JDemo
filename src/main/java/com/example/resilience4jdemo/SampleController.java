package com.example.resilience4jdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("method1")
    public ResponseEntity<String> method1() {
        for (int index = 20; index < 200; index++) {
            try {
                sampleService.callingMethod1(20);
            } catch (Exception e) {
                System.out.println("xxxxxxxxxxxxxxxx");
            }
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
