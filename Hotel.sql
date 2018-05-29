create table Hotel(
	cnpj varchar(25) not null,
	nome varchar(256) not null,
	cidade varchar(256) not null,
	senha varchar(50) not null,
	CONSTRAINT hotel_pk PRIMARY KEY(cnpj)
);

create table Site(
	url varchar(256) not null,
	nome varchar(256) not null,
	telefone varchar(50) null,
	senha varchar(50) not null 
	CONSTRAINT site_pk PRIMARY KEY(url)
);

create table Promocoes(
	id integer not null generated always as identity (start with 1, increment by 1),
	url_site varchar(256) not null references Site(url),
	cnpj_hotel varchar(25) not null references Hotel(cnpj),
	preco double not null,
	data_inicial date not null,
	data_final date not null,
	CONSTRAINT promocao_pk PRIMARY KEY(id)
);

create table Administrador(
	usuario varchar(256) not null,
	senha varchar(50) not null,
	CONSTRAINT promocao_pk PRIMARY KEY(usuario)
);


INSERT INTO HOTEL(cnpj, nome, cidade, senha)
VALUES('1233456788','ABC','Sao Carlos','1234');

INSERT INTO SITE(url, nome, telefone, senha)
values('www.hotelABC.com.br', 'Site Hotel ABC', '1232243', '9876');

INSERT INTO ADMINISTRADOR(usuario, senha)
values('admin', 'admin')

//recurso do glassfish-web.xml
<jdbc-resource enabled="true" jndi-name="jdbc/HotelDB" object-type="user" pool-name="HotelDBPool">
    <description/>
</jdbc-resource>

