package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.business.LojaBusiness;
import br.com.fiap.model.LojaModel;
import br.com.fiap.repository.LojaRepository;
import br.com.fiap.exception.ResponseBusinessException;

@RestController
@RequestMapping("/loja")
public class LojaController {

	@Autowired
	public LojaRepository lojaRepository;
	
	@Autowired
	public LojaBusiness lojaBusiness;
	
	@GetMapping()
	public ResponseEntity<List<LojaModel>> findAll(Model model) {
			
		List<LojaModel> lojas = lojaRepository.findAll();
		return ResponseEntity.ok(lojas);
			
	}

	@GetMapping("/{id}")
	public ResponseEntity<LojaModel> findById(@PathVariable("id") long id) {
		
		LojaModel loja = lojaRepository.findById(id).get();
		return ResponseEntity.ok(loja);
	}
	
	@PostMapping()
	public ResponseEntity save(@RequestBody @Valid LojaModel lojaModel) throws ResponseBusinessException {
		
		LojaModel loja = lojaBusiness.applyBusiness(lojaModel);
		
		lojaRepository.save(loja);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(loja.getIdLoja()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody @Valid LojaModel lojaModel) throws ResponseBusinessException {
		
		LojaModel loja = lojaBusiness.applyBusiness(lojaModel);
		
		loja.setIdLoja(id);
		lojaRepository.save(loja);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteById(@PathVariable("id") long id) {
		
		lojaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
