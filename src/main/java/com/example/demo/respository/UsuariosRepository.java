package com.example.demo.respository;

import com.example.demo.cadastral.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuarios, Integer> {

}
