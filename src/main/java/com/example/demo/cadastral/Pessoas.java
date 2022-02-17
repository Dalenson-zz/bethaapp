package com.example.demo.cadastral;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "pessoas")
@Table(name = "pessoas")
public class Pessoas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpessoa")
    public Integer idpessoa;

    @Column(nullable = false, length = 100)
    public String nome;

    @Column(nullable = false, length = 14)
    public String cpfcnpj;

    @JsonManagedReference
    @OneToMany(mappedBy = "pessoas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<PessoasEnderecos> enderecos = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "pessoas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<PessoasTelefones> telefones = new ArrayList<>();

    public void setEnderecos(List<PessoasEnderecos> enderecos) {
        this.enderecos = enderecos;
    }

    public List<PessoasEnderecos> getEnderecos() {
        return enderecos;
    }

    public Integer getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(Integer idpessoa) {
        this.idpessoa = idpessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }
}
