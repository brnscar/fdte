package br.com.fdte.spring.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import br.com.fdte.spring.models.TipoPreco;

	@Embeddable
	public class Preco {

		private BigDecimal preco;
		private TipoPreco tipo;

		public BigDecimal getMarca () {
			return preco;
		}
		public void setPreco (BigDecimal preco) {
			this.preco = preco;
		}

		public TipoPreco getTipo() {
			return tipo;
		}
		public void setTipo(TipoPreco tipo) {
			this.tipo = tipo;
		}
	

}
