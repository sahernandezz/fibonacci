package com.proteccion.fibonacci.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciServiceImpl implements IFibonacciService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciServiceImpl.class);

    @Override
    public List<BigInteger> generateFibonacci(int seed1, int seed2, int count) throws Exception {
        try {
            List<BigInteger> fibonacciSeries = new ArrayList<>();
            fibonacciSeries.add(BigInteger.valueOf(seed1));
            fibonacciSeries.add(BigInteger.valueOf(seed2));
            for (int i = 2; i < count + 2; i++) {
                fibonacciSeries.add(fibonacciSeries.get(i - 1).add(fibonacciSeries.get(i - 2)));
            }
            return fibonacciSeries;
        } catch (Exception e) {
            LOGGER.error("Error generating Fibonacci series", e);
            throw new Exception(e);
        }
    }
}
