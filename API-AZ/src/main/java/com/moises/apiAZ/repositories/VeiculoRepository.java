package com.moises.apiAZ.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moises.apiAZ.domain.Veiculo;



@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Veiculo obj WHERE obj.vendido=true") 
	Page<Veiculo> findVeiculoVendido(Pageable pageable);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Veiculo obj WHERE obj.vendido=false") 
	Page<Veiculo> findVeiculoVenda(PageRequest pageRequest);
	
}
