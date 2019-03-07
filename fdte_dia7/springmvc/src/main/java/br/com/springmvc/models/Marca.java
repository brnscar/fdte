package br.com.springmvc.models;

import javax.persistence.Embeddable;

@Embeddable
public class Marca {

	private String marca;
	private TipoMarca tipo;

	public String getMarca () {
		return marca;
	}
	public void setMarca (String marca) {
		this.marca = marca;
	}

	public TipoMarca getTipo() {
		return tipo;
	}
	public void setTipo(TipoMarca tipo) {
		this.tipo = tipo;
	}
}