var app = angular.module("aplicacao", []);

app.controller("Rest", function ($scope, $http) {
  $scope.btninfo = "Salvar";

  $scope.busca = function () {
    $http.get("http://localhost:8080/pessoas/buscatodos").then(function (data) {
      $scope.usuarios = data.data;
      console.log(data.data)
      $scope.btninfo = "Salvar";
    });
  };

  $scope.deleta = function (p) {
    Metro.notify.create("Enviado para exclusão...", "", {});
    $http.delete("http://localhost:8080/pessoas/deleta/" + p).then(function () {
      $scope.busca();
      Metro.notify.create("Excluido com sucesso", "", {});
    }, function errorCallback(response){
      Metro.notify.create("Falha ao excluir registro!", "", {});
    });
    console.log(p);
  };

  $scope.salvar = function () {
    Metro.notify.create("Adicionando...!", "", {});
    spinnerSalvar();
    $scope.btninfo = "Salvando...";
    listaEnderecos = document.getElementById("enderecos").value.split("_");
    listaTelefones = document.getElementById("telefones").value.split("_");
    var telefone = [];
    for(var t =0; t < listaTelefones.length; t++){
      telefone.push({telefone: listaTelefones[t]})
    }
    var endereco = [];
    for (var i = 0; i < listaEnderecos.length; i++) {
      if (i == 0) {
        endereco.push({ endereco: listaEnderecos[i], principal: "S" });
      } else {
        endereco.push({ endereco: listaEnderecos[i], principal: "N" });
      }
    }
    data = {
      nome: nome.value,
      telefone: telefone.value,
      cpfcnpj: cpfcnpj.value,
      enderecos: endereco,
      telefones:telefone
    };
    var req = {
      method: "POST",
      url: "http://localhost:8080/pessoas/cadastro",
      data: data,
    };

    $http(req).then(function (data) {
        $scope.busca();
        $scope.btninfo = "Salvar";
        
        limpaForm();
        Metro.notify.create("Adicionado com sucesso!", "", {});
        
    }, function errorCallback(response){
      Metro.notify.create("Não foi possivel adicionar!", "", {});
      limpaForm();
    });
  };

  $scope.salvaruser = function () {
    var notify = Metro.notify;
        notify.setup({
            width: 300,
            duration: 1000,
            animation: 'easeOutBounce'
        });
        notify.create("Gravando Usuário...");
        notify.reset();
    if(usuario.value == '' || senha.value == ''){
      var notify = Metro.notify;
        notify.create("Usuário ou senha não preenchidos", "Alerta !", {
      cls: "alert"
  });
    }else{
      data = {
        usuario: usuario.value,
        senha: senha.value,
      };
      var req = {
        method: "POST",
        url: "http://localhost:8080/pessoas/useradd",
        data: data,
      };
      $http(req).then(function (data) {
        document.getElementById("usuario").value = "";
        document.getElementById("senha").value = "";
        var notify = Metro.notify;
        notify.setup({
            width: 300,
            duration: 1000,
            animation: 'easeOutBounce'
        });
        notify.create("Usuário adicionado com sucesso!");
        notify.reset();
      });
  }
  };
  $scope.getlogin = function () {
    $http.get("http://localhost:8080/pessoas/getlogin").then(function (data) {
      for(i=0; i<data.data.length; i++){
        if(data.data[i].usuario == document.getElementById('user').value && data.data[i].senha == document.getElementById('senhauser').value){
          window.location.href = "http://localhost:8082/cadastro"
        }
      }
    })
  };

});

function spinnerSalvar() {
  document
    .getElementById("diva")
    .insertAdjacentHTML(
      "beforeend",
      '<div id="spinner" class="spinner-border text-warning"></div>'
    );
}

function limpaForm() {
  document.getElementById("spinner").remove();
  document.getElementById("nome").value = "";
  document.getElementById("cpfcnpj").value = "";
  document.getElementById("tele").innerHTML = '<div class="form-floating mb-3" id="tele">        <input type="text" id="telefones" name="telefone" data-role="taginput" data-tag-trigger="Enter" data-tag-separator="_" class="form-control">        <label for="floatingInput" >Telefone</label>      </div>';
  document.getElementById("ende").innerHTML = '<div class="form-floating mb-3" id="ende">        <input type="text" id="enderecos" name="endereco" data-role="taginput" data-tag-trigger="Enter" data-tag-separator="_" class="form-control">        <label for="floatingInput" >Endereço</label>      </div>';
}