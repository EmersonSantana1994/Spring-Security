package com.Loja.LojadGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Loja.LojadGames.model.Loja;


public interface LojaRepository extends JpaRepository <Loja, Long> {

	public List <Loja> findAllByGeneroContainingIgnoreCase (String genero );
		
	
}
