package br.com.springmvc.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String camisetas;
	private String tenis;
	private String blusas;
	private String kit;
	@ElementCollection
	private List<Marca> marcas;

	public List<Marca> getMarcas(){
		return marcas;
	}
	public void setMarcas(List<Marca> marca){
		this.marcas = marca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCamisetas() {
		return camisetas;
	}
	public void setCamisetas(String camisetas) {
		this.camisetas = camisetas;
	}
	public String getTenis() {
		return tenis;
	}
	public void setTenis(String tenis) {
		this.tenis = tenis;
	}
	public String getBlusas() {
		return blusas;
	}
	public void setBlusas(String blusas) {
		this.blusas = blusas;
	}
	public String getKit () {
		return kit;
	}
	
	public void setKit (String kit) {
	this.kit = kit;
	}
	@Override
	public String toString() {
		return "Produto [camisetas=" + camisetas + ", tenis=" + tenis + ", blusas=" + blusas + "]";
	}

}
