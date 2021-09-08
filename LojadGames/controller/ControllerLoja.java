package com.Loja.LojadGames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Loja.LojadGames.model.Loja;
import com.Loja.LojadGames.repository.LojaRepository;

@RestController
@RequestMapping ("/loja")
@CrossOrigin("*")
public class ControllerLoja {
	
	@Autowired
	public LojaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Loja>> getByAll(){
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Loja> getById(@PathVariable long id ){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
@GetMapping("/{genero}")

public ResponseEntity<List<Loja>> getByGenero (@PathVariable String genero) {
	
	return ResponseEntity.ok(repository.findAllByGeneroContainingIgnoreCase(genero));
}
	
@PostMapping
public ResponseEntity<Loja> post (@RequestBody Loja loja) {	
	return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(loja));
}	
	@PutMapping
	public ResponseEntity<Loja> put (@RequestBody Loja loja) {			
	return ResponseEntity.ok(repository.save(loja));
}
	
	@DeleteMapping ("/{id}")
	public void delete (@PathVariable long id) {
		
		repository.deleteById(id);
	}
}
