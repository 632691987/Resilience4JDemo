package com.example.resilience4jdemo;

import com.example.resilience4jdemo.exceptions.BusinessBException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @CircuitBreaker(name="backendA", fallbackMethod = "fallback123")
    public String callingMethod1(int flag) {
        if (flag > 10) throw new BusinessBException();
        return "Return value from normal call";
    }

    private String fallback123(Throwable throwable) {
        System.out.println("---------------------------------------------");
        System.out.println("Fallback123[Throwable] method called");
        System.out.println("---------------------------------------------");
        return "from fallback123[Throwable]";
    }

    private String fallback123(CallNotPermittedException exception) {
        System.out.println("---------------------------------------------");
        System.out.println("Fallback123[CallNotPermittedException] method called");
        System.out.println("---------------------------------------------");
        return "from fallback123[CallNotPermittedException]";
    }

}
