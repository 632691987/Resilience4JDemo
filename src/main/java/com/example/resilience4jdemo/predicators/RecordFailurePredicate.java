package com.example.resilience4jdemo.predicators;

import com.example.resilience4jdemo.exceptions.BusinessAException;

import java.util.function.Predicate;

public class RecordFailurePredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        if (throwable.getCause() instanceof BusinessAException) return false;
        else return true;
    }
}