package br.com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;	

@Controller
class ProdutosController {
		
	@RequestMapping("/produtos/form") 
	
	public String form () {
		return "produtos/form";
		
	}
	
	@RequestMapping("/produtos")
	public String grava(String produto ) {
		System.out.println(produto);
		
		return "produtos/form";

}
}
