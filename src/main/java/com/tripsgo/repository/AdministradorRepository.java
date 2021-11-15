package com.tripsgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tripsgo.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	@Query("select i from Administrador i where i.email = :email")
	public Administrador findByEmail(String email);
	
	@Query("select u from Administrador u where u.user = :user and u.senha = :senha")
	public Administrador findByLogin(String user, String senha);
}
