package com.app.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.data.NoticiaRepository;
import com.app.model.Noticia;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/noticia")
public class NoticiaController {
	
	@Autowired
	private NoticiaRepository noticiaRepo;
	
	@GetMapping
	public String showNoticiaList(Model model) {				
		model.addAttribute("noticias", noticiaRepo.findAll());		
		log.info("Noticia Listado: "+noticiaRepo.findAll());
		return "noticiaList";
	}
	
	@GetMapping("/new")
	public String newNoticia(Noticia noticia) {
		return "noticiaForm";
	}
	
	@GetMapping("/edit/{id}")
	public String editNoticia(@PathVariable("id") long id, Model model) {
		model.addAttribute("noticia", noticiaRepo.findById(id));
		log.info("Noticia para Editar: "+ noticiaRepo.findById(id));
		return "noticiaForm";
	}
	
	@PostMapping
	public String saveNoticia(@Valid Noticia noticia, Errors errors, RedirectAttributes atributos) {
		log.info("Noticia enviada: "+ noticia);
		log.info("Errores:" + errors.getAllErrors());		
		
		if(errors.hasErrors()) {
			
			return "noticiaForm";
		}
		
		noticiaRepo.save(noticia);
		atributos.addFlashAttribute("msg", "Elemento guardado con exito.");
		return "redirect:/noticia";
	}
	
	@GetMapping("/destroy/{id}")
	public String destroyNoticia(@PathVariable("id") long id, RedirectAttributes atributos) {
		log.info("Noticia enviada: "+ id);
		noticiaRepo.deleteById(id);
		atributos.addFlashAttribute("msg", "Elemento eliminado con exito.");
		return "redirect:/noticia";
	}

}
