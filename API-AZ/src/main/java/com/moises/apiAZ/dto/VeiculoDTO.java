package com.moises.apiAZ.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.moises.apiAZ.domain.Veiculo;

public class VeiculoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 2, max = 20, message = "2 tamanho deve ser entre 5 e 20 caracteres!")
	private String marca;
	private String modelo;
	private double valor;
	private boolean vendido;

	public VeiculoDTO() {
	}

	public VeiculoDTO(Veiculo obj) {
		codigo  = obj.getCodigo();
		marca   = obj.getMarca();
		modelo  = obj.getModelo();
		valor   = obj.getValor();
		vendido = obj.isVendido();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

}
