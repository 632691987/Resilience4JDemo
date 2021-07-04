package com.example.resilience4jdemo;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;

public class CircuitBreakerUtil {

    /**
     * @Description: 获取熔断器的状态
     */
    public static void getCircuitBreakerStatus(String time, CircuitBreaker circuitBreaker){
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        // Returns the failure rate in percentage.
        float failureRate = metrics.getFailureRate();
        // Returns the current number of buffered calls.
        int bufferedCalls = metrics.getNumberOfBufferedCalls();
        // Returns the current number of failed calls.
        int failedCalls = metrics.getNumberOfFailedCalls();
        // Returns the current number of successed calls.
        int successCalls = metrics.getNumberOfSuccessfulCalls();

        // Returns the max number of buffered calls.
        //int maxBufferCalls = metrics.getMaxNumberOfBufferedCalls();

        // Returns the current number of not permitted calls.
        long notPermittedCalls = metrics.getNumberOfNotPermittedCalls();
    }

    /**
     * @Description: 监听熔断器事件
     */
    public static void addCircuitBreakerListener(CircuitBreaker circuitBreaker){
        circuitBreaker.getEventPublisher()
                .onSuccess(event -> log("服务调用成功：" + event.toString()))
                .onError(event -> log("服务调用失败：" + event.toString()))
                .onIgnoredError(event -> log("服务调用失败，但异常被忽略：" + event.toString()))
                .onReset(event -> log("熔断器重置：" + event.toString()))
                .onStateTransition(event -> log("熔断器状态改变：" + event.toString()))
                .onCallNotPermitted(event -> log(" 熔断器已经打开：" + event.toString()))
        ;
    }

    private static void log(String message) {
        System.out.println(message);
    }
}