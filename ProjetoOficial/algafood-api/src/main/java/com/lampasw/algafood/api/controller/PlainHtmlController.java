package com.lampasw.algafood.api.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@Controller
public class PlainHtmlController {

	
	@RequestMapping("/interface-visual")
    public String ola() {
        return "client-js/index.html";
    }
}
