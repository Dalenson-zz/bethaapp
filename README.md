# Bethaapp
> Tecnologias utilizadas

### Importante
> Java version "17.0.2" 2022-01-18 LTS
> 
> Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
> 
> Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)

* Angular JS
* Spring Boot
* Banco de Dados Mysql ou Postgres

> Foi utilizado para o banco de dados a hospedagem no site https://dashboard.heroku.com

> Não foi utilizado o OAuth 2.0. Foi criado apenas uma tela de login que verifica no banco se já existe usuário e senha cadastrados e redireciona para uma outra pagina.

> Regras do cadastro de clientes
* Não pode haver CPF ou CNPJ duplicado
* Deverá ser possivel registrar quantos endereços forem necessários, tendo que definir apenas um como principal
* Deve permitir registrar quantos telefones forem necessários

# Preparando projeto para utilização
Executar o .jar que se encontra na pasta ```/out/artifacts/demo_jar``` conforme exemplo abaixo:

![Alt Text](https://user-images.githubusercontent.com/22826432/154704963-e62d3a15-7580-4c28-b141-e27551e2455b.png)

Executar também o arquivo .js dentro da pasta ```app.js``` conforme exemplo abaixo:

![Alt Text](https://user-images.githubusercontent.com/22826432/154592763-3420b7f1-5d30-4248-82c0-b0b8923a2d11.png)


Desta forma o sistema está em execução no link ```localhost:8082```



# Sistema

Tela inicial do sistema é representada pela janela abaixo

![Alt Text](https://user-images.githubusercontent.com/22826432/154593050-387cf2c8-ad03-48a3-b5bd-fc736310b851.png)

Para acessa-lo é preciso cadastrar um usuário, caso aconteça algum problema no sistema sempre será emitido um alerta na parte superior a direita conforme imagem abaixo

![Alt Text](https://user-images.githubusercontent.com/22826432/154593133-631d7ee9-1be3-44be-a4a5-5442480550c8.png)

Após a tela de login e acessar o sistema é redirecionado para a seguinte pagina:

![Alt Text](https://user-images.githubusercontent.com/22826432/154593369-c47c48ca-ed6d-4ca2-8426-74a4b51d87da.png)

Ao realizar o cadastro de pesssoas é possivel adicionar quantos telefones forem necessarios da mesma forma que o endereço. O endereço sempre o primeiro ficará como principal.

![Alt Text](https://user-images.githubusercontent.com/22826432/154593452-7e37e1e1-5a9c-481c-981b-428cb4ae8c38.png)




# Criação das tabelas do banco de dados

```
CREATE TABLE pessoas (
  	idpessoa int(11) auto_increment NOT NULL,
	nome varchar(100) NOT NULL,
	cpfcnpj varchar(14) NOT NULL,
	PRIMARY KEY (idpessoa)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

CREATE TABLE pessoas_enderecos  (
    id int(11) auto_increment NOT NULL,
	endereco varchar(100) NOT NULL,
	principal varchar(100) NOT NULL,
	idpessoa int(11) not null,
	PRIMARY KEY (id),
	CONSTRAINT idpessoa FOREIGN KEY (idpessoa) REFERENCES pessoas (idpessoa) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

CREATE TABLE pessoas_telefones  (
    id int(11) auto_increment NOT NULL,
	telefone varchar(100) NOT NULL,
	idp int(11) not null,
	PRIMARY KEY (id),
	CONSTRAINT idp FOREIGN KEY (idp) REFERENCES pessoas (idpessoa) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

CREATE TABLE usuarios (
    idusuario int(11) auto_increment NOT NULL,
	usuario varchar(100) NOT NULL,
	senha varchar(100) NOT NULL,
	PRIMARY KEY (idusuario)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8
```
