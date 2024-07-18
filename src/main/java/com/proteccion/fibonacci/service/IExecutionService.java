package com.proteccion.fibonacci.service;

import com.proteccion.fibonacci.persistence.entity.Execution;

import java.math.BigInteger;
import java.util.List;

public interface IExecutionService {
    void saveExecution(String time, List<BigInteger> series);
    List<Execution> getAllExecutions();
}
