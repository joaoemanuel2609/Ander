CREATE DATABASE cadastro;

USE cadastro;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(30) NOT NULL,
    senha VARCHAR(30),
    nome VARCHAR(50)
);

CREATE TABLE servicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    barbeiro VARCHAR(30) NOT NULL,
    servico VARCHAR(100) NOT NULL,
    nomeCliente VARCHAR(50) NOT NULL,
    horaInicio VARCHAR(20) NOT NULL,
    horaTermina VARCHAR(20) NOT NULL,
    formaPagamento VARCHAR(30) NOT NULL,
    valorServico INT NOT NULL,
    FOREIGN KEY (barbeiro) REFERENCES usuarios(usuario)
);
							

    
     
							 SELECT servicos.*, usuarios.* 
							 FROM servicos 
							 JOIN usuarios ON servicos.barbeiro = usuarios.usuario 
							 WHERE servicos.barbeiro = 'ander' ;
	ALTER TABLE servicos ADD COLUMN data_servico DATE;
    SELECT DATE_FORMAT(data_servico, '%d/%m/%Y') AS data_formatada FROM servicos;