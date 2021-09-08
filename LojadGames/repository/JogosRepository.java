package com.Loja.LojadGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Loja.LojadGames.model.Jogos;

@Repository
public interface JogosRepository extends JpaRepository<Jogos, Long>{
	
	public List <Jogos> findAllByNomeContainingIgnoreCase (String nome);	

}
