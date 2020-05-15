package com.moises.apiAZ.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moises.apiAZ.domain.Veiculo;



@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Veiculo obj WHERE obj.vendido=true") 
	List<Veiculo> findVeiculoVendido();
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Veiculo obj WHERE obj.vendido=false") 
	List<Veiculo> findVeiculoVenda();
	
}
