package com.tripsgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tripsgo.model.Aluguel;


@Repository
public interface AluguelRepository  extends JpaRepository<Aluguel, Long>{
}
