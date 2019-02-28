package br.com.springmvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto {
		@Id
		@GeneratedValue
		private int id;
		private String camisetas;
		private String tenis;
		private String blusas;
		
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
		@Override
		public String toString() {
			return "Produto [camisetas=" + camisetas + ", tenis=" + tenis + ", blusas=" + blusas + "]";
		}
		
}
