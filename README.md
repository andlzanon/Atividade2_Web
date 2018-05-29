# Atividade 2 da Disciplina de Desenvolvimento Web
Sistema para cria��o de promo��es de quarto de hotel em sites de reservas.
Implementa��o deve usar JSF. Diferentemente da Atividade 1, desenvolvida com JSP.

## Requisitos do sistema:
A aplica��o deve conter:

* R1: Cadastro de sites de reservas (requer login de administrador)
    
* R2: Cadastro de hot�is (requer login de administrador)
    
* R3: Listagem de todos os hot�is em uma �nica p�gina (n�o requer login)

* R4: Listagem de todos os hot�is por cidade (n�o requer login)

* R5: Cria��o de uma promo��o de um hotel (requer login do hotel via CNPJ + senha). 
Depois de fazer login, o hotel pode cadastrar uma promo��o. Para isso, deve escolher um site de reservas (digitando seu endere�o/URL ou escolhendo a partir de uma lista), uma data inicial/final e um pre�o, e deve ser gravada a promo��o na base de dados.

* R6: Listagem de todas as promo��es de um hotel (requer login do hotel via CNPJ + senha). 
Depois de fazer login, o hotel pode visualizar todas as suas promo��es gravadas.

* R7: O sistema n�o deve permitir o cadastro de promo��es de um mesmo hotel ou de um mesmo site de reservas em uma mesma data.

* R8: Listagem de todas as promo��es de um site de reservas (requer login do site de reservas via endere�o/URL + senha). 
Depois de fazer login, o site de reservas pode visualizar todas as suas promo��es gravadas.

* R9: Tela de login comum para todos os requisitos que precisarem

* R10: Tela de erro pra qualquer erro que possa acontecer

## Configura��o do Projeto
* Realizar git pull
* Configurar o Glassfish para trabalhar com o mesmo banco de dados do projeto anterior (Derby)
* Copiar os drivers do derby para a pasta <dom�nio>/lib do Glassfish
* Criar um novo JDBC Connection Pool
* No Netbeans (aba Servi�os), criar uma conex�o com o banco com o nome ReservaHotelDB2 
* No projeto, criar novo arquivo (Recurso JDBC - Glassfish)
* Ao criar o recurso, escolher para criar novo pool de conex�es
* Nome JNDI: jdbc/HotelDB
* Nome do pool: HotelDBPool
* Extrair de conex�o existente (escolher a conex�o do passo 3.2.1)
* No Netbeans, exibir o console de administra��o do dom�nio Glassfish (aba Servi�os)
* Abrir a op��o Recursos, e fazer upload do arquivo .xml
* Para testar, abrir a op��o Pool de conex�es JDBC, escolher a conex�o criada e clicar em Ping
* No projeto, criar um novo arquivo do tipo Descritor do Glassfish (glassfish-web.xml)
* Criar um ref para o recurso JNDI criado no passo anterior (ver Hotel.sql)
* Executar comandos para criar tabelas (ver Hotel.sql)
* Inserir dados nas tabelas (ver Hotel.sql)

## Cr�ditos de bibliotecas:
* [PrimeFaces 5.0](https://www.primefaces.org/downloads/)

## Alunos
* Andr� Levi Zanon
* Victor Santos Ferrari
* Victor Hugo Domingos de Abreu

## Professores 
* Daniel Lucr�dio