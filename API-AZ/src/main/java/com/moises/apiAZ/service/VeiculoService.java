package com.moises.apiAZ.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moises.apiAZ.domain.Veiculo;
import com.moises.apiAZ.dto.VeiculoDTO;
import com.moises.apiAZ.repositories.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repo;
	
	public Veiculo find(Integer id) {
		Optional<Veiculo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Veiculo.class.getSimpleName(), null));
	}
	
	public Veiculo insert(Veiculo obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Veiculo update(Veiculo obj) {
		Veiculo newObj = find(obj.getCodigo());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir este veiculo!");
		}
	}
	
	public List<Veiculo> findAll(){
		return repo.findAll();
	}
	
	public List<Veiculo> findVendido(){
		return repo.findVeiculoVendido();
	}
	
	public List<Veiculo> findVenda(){
		return repo.findVeiculoVenda();
	}
	
	public Page<Veiculo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Veiculo fromDTO(VeiculoDTO objDto) {
		return new Veiculo(objDto.getCodigo(), objDto.getMarca(),  objDto.getModelo(), objDto.getValor(), objDto.isVendido());
	}
	
	private void updateData(Veiculo newObj, Veiculo obj) {
		newObj.setMarca(obj.getMarca());
		newObj.setModelo(obj.getModelo());
		newObj.setValor(obj.getValor());
		newObj.setVendido(obj.isVendido());
	}

}
