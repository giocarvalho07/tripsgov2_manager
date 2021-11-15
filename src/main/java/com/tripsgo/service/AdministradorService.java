package com.tripsgo.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tripsgo.exception.AdministradorServiceException;
import com.tripsgo.exception.CriptoExistException;
import com.tripsgo.exception.EmailExistException;
import com.tripsgo.model.Administrador;
import com.tripsgo.repository.AdministradorRepository;
import com.tripsgo.util.Util;

@Service
public class AdministradorService {
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	public void cadastrarAdministrador(Administrador administrador) throws Exception {
		
		try {
			if(administradorRepository.findByEmail(administrador.getEmail()) !=null) {
				throw new EmailExistException("Já existe um email cadastro em nossa "
						+ "base de dados" + administrador.getEmail());
				} 
			administrador.setSenha(Util.md5(administrador.getSenha()));
			}
		
			catch (NoSuchAlgorithmException e) {
				
				throw new CriptoExistException("Erro na criptografia da senha");
			}	
			
			administradorRepository.save(administrador);
	}
	
	public void deletarAdministrador(Administrador administrador) {
		administradorRepository.delete(administrador);
	}
	
	public List<Administrador> listarAdministrador(){
		return administradorRepository.findAll();
	}
	
	public Administrador idAdministrador(Long id) {
		return administradorRepository.findById(id).get();
	}
	
	public Administrador loginAdministrador(String administrador, String senha) throws AdministradorServiceException{
		Administrador administradorLogin = administradorRepository.findByLogin(administrador, senha);
		return administradorLogin;
	}

}
