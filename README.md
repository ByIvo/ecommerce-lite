# ecommerce-lite

## Rodando o projeto
> Após importar o projeto para sua stack de desenvolvimento, é possível, através do maven, dar o build no APP que consome os serviços; Para isso funcionar corretamente, você precisará dos seguintes itens instalados

* node ~4.5.0
* npm ~2.15
* Bower ~1.7
* Grunt ~1.2

### Passos

> Primeiro, na raiz do pasta do app (src/main/app), execute:

```bash
 sudo npm install
```
> Caso a execução do bower falhe, execute-a manualmente para baixar os projetos requisitados.

```bash
 bower install
```

> Caso queira desenvolver o app sem apoio do maven, pode executar o comando que ficará verificando alterações nos arquivos e fazendo as mudanças necessárias
```bash
 grunt watch
```

> Caso queira gerar uma build do APP manualmente, execute


## Configurando JNDI para acesso ao Banco de dados MySQL

> No seu arquivo context.xml (do servidor utilizado), adicione as seguintes linhas:
```xml
 <Resource name="jdbc/ecommerceliteApiDb" 
              auth="Container" 
              type="javax.sql.DataSource"
              maxActive="100" 
              maxIdle="30" 
              maxWait="10000"
              username="DB_USER" 
              password="DB_PASS" 
              driverClassName="com.mysql.jdbc.Driver"
              url="jdbc:mysql://DB_HOST:BD_PORT/ecommerce_lite"/>
```

> Substitua os parâmetros iniciados por **'DB_'** de acordo com o sugerido.

## Criando a base de dados

O sistema cria as tabelas automaticamente segundo seu Mapeamento Relacional, então somente cria uma database chamada **ecommerce_lite**

> Acesse o mysql
```bash
 mysql -u USER -p
 ****
```

> execute o comando abaixo para criar a database

```bash
 create database ecommerce_lite;
```
