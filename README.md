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

```bash
 grunt dist
```

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
## Testes

## Testes unitários

> A API possue os testes unitários de seus modelos no pacote src/test; Ele pode facilmente ser executado com o apoio de sua IDE de desenvolvimento;

> Para testar a API executando, você precisará do POSTMAN > https://www.getpostman.com/

> Importe o arquivo ecommerce-lite.postman_colletion.json que se encontra na pasta src/test/api;
> Adicione uma variável de ambiente chamada **ecommerce_lite** com o contexto de acesso à API rodando (ex: http://byivo.rocks/ecommerce-lite/)
> Dentro do Runner do Postman, execute os testes importados selecionando o ambiente acima configurado (Obs: Execute com a base de dados zerada);

## API

A api do sistema criado possui duas rotas mapeadas:

* /items/:id -> Gerencia os Itens do sistema;
* /buys/:id -> Gerencia as compras do sistema;
 
### ITEM API

* GET : /items -> Retorna uma lista completa de todos os items cadastrados;
* GET : /items/id -> Retorna um item, especificado pelo id na URL. Também pode gerar um erro 404 de não encontrado;
* GET : /items/sellable -> Retorna uma lista dos itens cujo preço de compra seja maior que 0, ou seja, estão aptos à serem comprados;

* POST : /items -> Cria um novo Item com base em um JSON passado no corpo da chamda. Retorna 201 caso criado com sucesso e 422 caso haja problemas de validação.

* PUT : /items/id -> Altera um item, especificado pelo id na URL. O conteúdo da troca será especificado por um JSON contendo as propriedades da troca (vide testes Postman). Pode gerar um erro 404;

* DELETE : /items/id -> Deleta um item, especificado pelo id na URL. Também pode gerar um erro 404 de não encontrado;

#### Estrutura do Item

``` {"id": 1, "name": "Produto 01", "boughtPrice": 200.0, "Description": "", "image": ""}```

### BUY API

* GET : /buys -> Retorna uma lista completa de todas as compras do sistema.
* GET : /buys/id -> Retorna uma compra determinada pelo ID na URL; Pode gerar um erro 404

* POST : /buys -> Cria uma nova compra por base no JSON informado no corpo da chamada. Retorna 201 caso criado com sucesso!

#### Estrutura da compra

``` {
"id": 1,
"buyDate": "2016-09-22",
"profitRate": 0.1,
"totalExpenses": 400.0,
"boughtItems": [  
 {"id": 1, "item": {ESTRUTURA DO ITEM}, "itemQnt": 1}
]
}```
