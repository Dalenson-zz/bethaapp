const express = require("express");

const app = express();

app.use(express.static(__dirname))

app.get("/", function(req, res){
  res.sendFile(__dirname+'/login.html');
});

app.get("/cadastro", function(req, res){
  res.sendFile(__dirname+'/cadastro.html');
  
});

app.listen(8082);
