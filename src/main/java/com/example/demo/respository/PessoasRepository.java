package com.example.demo.respository;

import com.example.demo.cadastral.Pessoas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends CrudRepository<Pessoas, Integer> {

}
