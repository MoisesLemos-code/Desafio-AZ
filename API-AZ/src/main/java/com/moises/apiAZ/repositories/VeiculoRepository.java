package com.moises.apiAZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moises.apiAZ.domain.Veiculo;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{

}
