package com.moises.apiAZ.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moises.apiAZ.domain.Veiculo;
import com.moises.apiAZ.repositories.VeiculoRepository;

@Service
public class DBService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public void instantiateTestDatabase() {
		
		Veiculo v1 = new Veiculo(null, "Hyundai", "HB20 2017", 37000, false);
		Veiculo v2 = new Veiculo(null, "Chevrolet", "Onix 2019", 40000, true);
		Veiculo v3 = new Veiculo(null, "Volkswagen", "Gol 2005", 23000, false);
		Veiculo v4 = new Veiculo(null, "Fiat", "Uno 2017", 20000, true);
		Veiculo v5 = new Veiculo(null, "Toyota", "Etios 2013", 21000, false);
		Veiculo v6 = new Veiculo(null, "Fiat", "Palio 2014", 22000, true);
		Veiculo v7 = new Veiculo(null, "Renault", "Clio 2002", 15000, false);
		Veiculo v8 = new Veiculo(null, "Chevrolet", "Classic 2012", 18000, false);
		Veiculo v9 = new Veiculo(null, "Ford", "Fusion 2010", 35000, false);
		Veiculo v10 = new Veiculo(null, "Volkswagen", "Jetta 2010", 37000, false);
		
		veiculoRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10));
	}
}
