package com.lampasw.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lampasw.algafood.di.modelo.Cliente;
import com.lampasw.algafood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {

	private AtivacaoClienteService ativacaoClienteService;
			
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {		
		this.ativacaoClienteService = ativacaoClienteService;
		
		System.out.println("MeuPrimeiroController: " + ativacaoClienteService);
	}
	

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		Cliente joao = new Cliente("Joao", "joao@teste.com", "16 32423242");
		
		ativacaoClienteService.ativar(joao);
		
		return "Hello World. I'm programming with Java, using Framework and Ecossystem Spring!!!!!!!<br \\> So interesting!!";
	}
}
