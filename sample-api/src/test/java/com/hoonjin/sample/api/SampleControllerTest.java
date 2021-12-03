package com.hoonjin.sample.api;

import com.hoonjin.sample.domain.GlobalResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleControllerTest {

    @Test
    void fakeTest() {
        GlobalResponse response = new GlobalResponse("nothing");
        System.out.println(response.getResult());
    }

}