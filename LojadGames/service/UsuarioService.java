package com.Loja.LojadGames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Loja.LojadGames.model.UserLogin;
import com.Loja.LojadGames.model.Usuario;
import com.Loja.LojadGames.repository.UsuarioRepository;



@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> CadastrarUsuario (Usuario usuario) { 
	
		Optional<Usuario> user = repository.findByUsuario(usuario.getSenha());
		if (user.isPresent()) {
			
			return Optional.ofNullable(null);
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEnconder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		
		return Optional.of(repository.save(usuario));
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" +user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				return user;
			}
		}		
		
		return null;
	}

}
