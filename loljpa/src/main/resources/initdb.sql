drop table if exists CLIENTE cascade;
drop table if exists ESTOQUE cascade;
drop table if exists ITEM_PEDIDO cascade;
drop table if exists pedido cascade;

CREATE TABLE IF NOT EXISTS  pedido (
		COD_PEDIDO SERIAL NOT NULL,
		DATA_PEDIDO DATE NOT NULL,
		STATUS VARCHAR(50) NOT NULL,
		PAGAMENTO VARCHAR(20) NOT NULL,
		TOTAL DOUBLE PRECISION NOT NULL,
		COD_CLIENTE INTEGER NOT NULL,
		PRIMARY KEY (COD_PEDIDO));
		
CREATE TABLE IF NOT EXISTS  item_pedido (
		COD_ITEM SERIAL NOT NULL,
		QTD int NOT NULL,
		COD_LIVRO INTEGER NOT NULL,
		COD_PEDIDO INTEGER NOT NULL,
		PRIMARY KEY (COD_ITEM));		

CREATE TABLE IF NOT EXISTS  cliente (
		COD_CLIENTE SERIAL NOT NULL,
		NOME VARCHAR(50) NOT NULL,
		LOGIN VARCHAR(10) NOT NULL,
		SENHA VARCHAR(10) NOT NULL,
		ENDERECO VARCHAR(32) NOT NULL,
		CIDADE VARCHAR(32) NOT NULL,
		BAIRRO VARCHAR(32) NOT NULL,
		ESTADO VARCHAR(32) NOT NULL,
		CEP VARCHAR(10) NOT NULL,
		PRIMARY KEY (COD_CLIENTE));


CREATE TABLE IF NOT EXISTS  estoque (
		COD_LIVRO SERIAL NOT NULL,
		TITULO VARCHAR(30) NOT NULL,
		AUTOR VARCHAR(20) NOT NULL,
		PRECO DOUBLE PRECISION NOT NULL,
		IMAGEM VARCHAR(80) NOT NULL,
		DESCRICAO VARCHAR(80),
		PRIMARY KEY (COD_LIVRO));

alter table ITEM_PEDIDO add constraint fk_item_pedido_livro foreign key (COD_LIVRO) references ESTOQUE;
alter table ITEM_PEDIDO add constraint fk_pedido_itens foreign key (COD_PEDIDO) references pedido;
alter table pedido add constraint fk_pedido_cliente foreign key (COD_CLIENTE) references CLIENTE;


INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('GRANDE SERTAO - VEREDAS', 'ROSA, JOAO GUIMARAES', 165, 'imagens/veredas.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('QUANDO NIETZSCHE CHOROU', 'YALOM, IRVIN D.', 49.9, 'imagens/chorou.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('CASSINO ROYALE - JAMES BOND 00', 'Fleming, Ian', 29.9, 'imagens/james.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('FILOSOFIA DO TEDIO', 'Svendsen, Lars', 29.9, 'imagens/tedio.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('O CASAMENTO', 'Rodrigues, Nelson', 39.9, 'imagens/casamento.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('NEVE', 'PAMUK, ORHAN', 54, 'imagens/neve.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('VOLTA AO MUNDO EM OITENTA DIAS', 'VERNE, JULIO', 16.5, 'imagens/volta_mundo.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('CRISTOVAO COLOMBO', 'VERNE, JULIO', 16.5, 'imagens/cristovao_colombo.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('VINTE MIL LEGUAS SUBMARINAS', 'VERNE, JULIO', 14.9, 'imagens/submarinas.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('O SENHOR DOS ANEIS', 'TOLKIEN, J.R.R.', 169.9, 'imagens/senhor.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('HARRY POTTER', 'ROWLING, J.K.', 89.7, 'imagens/harry.png');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('A AVENTURAS DE PI', 'MARTEL, YANN', 23.5, 'imagens/lifeofpi.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('PARA ONDE ELA FOI?', 'FORMAN, GAYLE', 20.0, 'imagens/onde.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('O LIVRO DO CEMITERIO', 'GAILMAN, NEIL', 20.0, 'imagens/cemiterio.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('SANDMAN VOL 1', 'GAILMAN, NEIL', 489.0, 'imagens/sandman.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('WATCHMEN', 'MOORE, ALAN', 37.4, 'imagens/watchmen.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('JUSTICEIRO NOIR', 'TIIER, FRANK', 12.5, 'imagens/justiceiro.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('SUPERMAN', 'TOMASI, PETER', 5.9, 'imagens/superman.jpg');
INSERT INTO ESTOQUE (TITULO,AUTOR,PRECO,IMAGEM) VALUES ('BATMAN', 'SNYDER, SCOTT', 5.9, 'imagens/batman.jpeg');