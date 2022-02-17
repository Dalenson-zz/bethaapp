package com.example.demo;

import com.example.demo.cadastral.Pessoas;
import com.example.demo.cadastral.Usuarios;
import com.example.demo.respository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.respository.PessoasRepository;

import java.util.*;

@Controller
@RequestMapping("/pessoas")
public class controle {

    @Autowired
    final private PessoasRepository pessoasRepository;
    final private UsuariosRepository usuariosRepository;

    public controle(PessoasRepository pessoasRepository, UsuariosRepository usuariosRepository) {
        this.pessoasRepository = pessoasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @CrossOrigin
    @PostMapping(path = "/cadastro")
    public ResponseEntity salvar(@RequestBody Pessoas pessoas) {
        List<Pessoas> p = (List<Pessoas>) pessoasRepository.findAll();
        for (Pessoas pessoa : p) {
            if (pessoa.cpfcnpj.equals(pessoas.cpfcnpj)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("CPF/CNPJ Igual!");
            }
        }
        return ResponseEntity.ok().body(pessoasRepository.save(pessoas));
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/buscatodos")
    public ResponseEntity busca(){
        List<Pessoas> lista = new ArrayList<Pessoas>();
        for (Pessoas pessoas :pessoasRepository.findAll()){
            lista.add(pessoas);
            System.out.println("Buscou Todos");
        }
        return ResponseEntity.ok().body(lista);
    }
    @CrossOrigin
    @GetMapping(path = "/busca/{id}")
    public ResponseEntity buscaporid(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(pessoasRepository.findById(id));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, path = "/deleta/{id}")
    public ResponseEntity deletaid(@PathVariable("id") Integer id){
        Pessoas pessoa = pessoasRepository.findById(id).orElseThrow();
        pessoasRepository.delete(pessoa);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/")
    public String index(){
        System.out.println("asd");
        return "index";
    }


    @CrossOrigin
    @PostMapping(path = "/useradd")
    public ResponseEntity salvarusuario(@RequestBody Usuarios user){
        return ResponseEntity.ok().body(usuariosRepository.save(user));
    }
}
