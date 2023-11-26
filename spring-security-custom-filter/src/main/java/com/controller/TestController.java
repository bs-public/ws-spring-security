package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.TestService;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
	this.testService = testService;
    }

    @GetMapping(value = "/test")
    public String test() {
	return testService.test();
    }

}
