package com.proteccion.fibonacci.persistence.repository;

import com.proteccion.fibonacci.persistence.entity.Execution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExecutionRepository extends JpaRepository<Execution, Long> {

}
