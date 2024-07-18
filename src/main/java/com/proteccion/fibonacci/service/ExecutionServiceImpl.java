package com.proteccion.fibonacci.service;

import com.proteccion.fibonacci.persistence.entity.Execution;
import com.proteccion.fibonacci.persistence.repository.IExecutionRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ExecutionServiceImpl implements IExecutionService {

    private final IExecutionRepository executionRepository;

    public ExecutionServiceImpl(IExecutionRepository executionRepository) {
        this.executionRepository = executionRepository;
    }

    @Override
    public void saveExecution(String time, List<BigInteger> series) {
        Execution execution = new Execution();
        execution.setExecutionTime(time);
        execution.setSeries(series);
        executionRepository.save(execution);
    }

    @Override
    public List<Execution> getAllExecutions() {
        return executionRepository.findAll();
    }
}
