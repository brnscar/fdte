package br.com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.daos.ProdutoDAO;
import br.com.springmvc.models.Produto;
import br.com.springmvc.models.TipoMarca;

@Controller
class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;
	
    @RequestMapping(value="produtos", method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
		
		
		
	}

	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoMarca.values());

		return modelAndView;

	}
    @RequestMapping(value="produtos", method = RequestMethod.POST)
	public String gravar(Produto produto) {
		produtoDao.gravar(produto);
		return "produtos/lista";

	}
}
