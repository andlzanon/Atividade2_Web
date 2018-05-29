# Atividade 2 da Disciplina de Desenvolvimento Web
Sistema para criação de promoções de quarto de hotel em sites de reservas.
Implementação deve usar JSF. Diferentemente da Atividade 1, desenvolvida com JSP.

## Requisitos do sistema:
A aplicação deve conter:

* R1: Cadastro de sites de reservas (requer login de administrador)
    
* R2: Cadastro de hotéis (requer login de administrador)
    
* R3: Listagem de todos os hotéis em uma única página (não requer login)

* R4: Listagem de todos os hotéis por cidade (não requer login)

* R5: Criação de uma promoção de um hotel (requer login do hotel via CNPJ + senha). 
Depois de fazer login, o hotel pode cadastrar uma promoção. Para isso, deve escolher um site de reservas (digitando seu endereço/URL ou escolhendo a partir de uma lista), uma data inicial/final e um preço, e deve ser gravada a promoção na base de dados.

* R6: Listagem de todas as promoções de um hotel (requer login do hotel via CNPJ + senha). 
Depois de fazer login, o hotel pode visualizar todas as suas promoções gravadas.

* R7: O sistema não deve permitir o cadastro de promoções de um mesmo hotel ou de um mesmo site de reservas em uma mesma data.

* R8: Listagem de todas as promoções de um site de reservas (requer login do site de reservas via endereço/URL + senha). 
Depois de fazer login, o site de reservas pode visualizar todas as suas promoções gravadas.

* R9: Tela de login comum para todos os requisitos que precisarem

* R10: Tela de erro pra qualquer erro que possa acontecer

## Configuração do Projeto
* Realizar git pull
* Configurar o Glassfish para trabalhar com o mesmo banco de dados do projeto anterior (Derby)
* Copiar os drivers do derby para a pasta <domínio>/lib do Glassfish
* Criar um novo JDBC Connection Pool
* No Netbeans (aba Serviços), criar uma conexão com o banco com o nome ReservaHotelDB2 
* No projeto, criar novo arquivo (Recurso JDBC - Glassfish)
* Ao criar o recurso, escolher para criar novo pool de conexões
* Nome JNDI: jdbc/HotelDB
* Nome do pool: HotelDBPool
* Extrair de conexão existente (escolher a conexão do passo 3.2.1)
* No Netbeans, exibir o console de administração do domínio Glassfish (aba Serviços)
* Abrir a opção Recursos, e fazer upload do arquivo .xml
* Para testar, abrir a opção Pool de conexões JDBC, escolher a conexão criada e clicar em Ping
* No projeto, criar um novo arquivo do tipo Descritor do Glassfish (glassfish-web.xml)
* Criar um ref para o recurso JNDI criado no passo anterior (ver Hotel.sql)
* Executar comandos para criar tabelas (ver Hotel.sql)
* Inserir dados nas tabelas (ver Hotel.sql)

## Créditos de bibliotecas:
* [PrimeFaces 5.0](https://www.primefaces.org/downloads/)

## Alunos
* André Levi Zanon
* Victor Santos Ferrari
* Victor Hugo Domingos de Abreu

## Professores 
* Daniel Lucrédio