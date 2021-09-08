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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Loja.LojadGames.model.Jogos;
import com.Loja.LojadGames.repository.JogosRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/jogos")
public class Controller {
	
	@Autowired
	private JogosRepository repository;
	
	@GetMapping
	public ResponseEntity <List<Jogos>> getByAll() {
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity <Jogos> getById (@PathVariable long id) {
		
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<Jogos>> getByNome (@PathVariable String nome){
		
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Jogos> post(@RequestBody Jogos jogos){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(jogos));
	}
	
	@PutMapping
	public ResponseEntity<Jogos> put (@RequestBody Jogos jogos) {
		
		return ResponseEntity.ok(repository.save(jogos));
	}
	
	@DeleteMapping ("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);	
	}
}
