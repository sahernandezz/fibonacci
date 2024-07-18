package com.proteccion.fibonacci.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

class FibonacciServiceTest {

    private final IFibonacciService fibonacciService = new FibonacciServiceImpl();

    @Test
    void testGenerateFibonacci() throws Exception {
        List<BigInteger> result = fibonacciService.generateFibonacci(2, 3, 4);
        List<BigInteger> expected = Arrays.asList(BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(8), BigInteger.valueOf(13), BigInteger.valueOf(21));
        assertEquals(expected, result);
    }
}