package com.proteccion.fibonacci.service;

import java.math.BigInteger;
import java.util.List;

public interface IFibonacciService {
    List<BigInteger> generateFibonacci(int seed1, int seed2, int count) throws Exception;
}
