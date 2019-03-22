## Luana Müller

Projeto DoNotFightForTheFood
================

## Considerações Gerais

Sistema para apoio à escolha dos restaurantes para almoço com os colegas. O sistma permite o cadastro de funcionários, restaurantes, e a votação.


## Detalhes do projeto

O projeto foi contruído utilizando a linguagem Java, e sua interface gráfica foi construída com uso da biblioteca Swing. 
O sistema persiste os dados em arquivos de textos, possibilitanto aos usuários, realizar os registros à qualquer momento.

## Requisitos do sistema

Java Runtime Environment 8

Java Development Kit 8

## Como compilar o projeto

A compilação do projeto pode ser feita através da IDE Netbeans, usando a opção (Limpar e Construir). O arquivo jar será gerado na pasta /dist do projeto.

## Como rodar o software

Execute o arquivo DoNotFightForTheFood.jar utilizando o comando: java -jar DoNotFightForTheFood.jar

## Funcionalidades do sistema

### Votar em um restaurante (Vote for a new restaurant): 

Permite ao funcionário votar no restaurante onde deseja almoçar.
O votante deverá informar o seu "Secret ID", e selecionar em uma lista o restaurante desejado. 
A lista apresentará somente restaurantes que não tenham sido eleitos nos na semana (a cada 5 dias os registros de restaurantes eleitos são zerados, e um restaurante estará apto a ser votado novamente).

### Adicionar um novo funcionário (Add a new employee):

Permite a adição de um novo funcionário. Deverá ser informado o nome do funcionário e um "Secret ID", o qual será usado como identificador no momento da votação.

### Adicionar um novo restaurante (Add a new restaurant):

Permite a adição de um novo restaurante. Deverá ser informado o nome do restaurante (que será usado como ID do restaurante), o preço médio e o tipo de comida servida no local.

### Verificar o vencedor da votação (Check today's winner):

Permite a apuração dos votos. Após a apuração, os registros de votos serão zerados, permitindo o início de uma nova votação. O restaurante vencedor da eleição ficará temporariamente indisponível para votação.

## Destaques do código

O destaque da aplicação vai para os arquivos texto que funcionam como uma persistência simples dos dados das votações, permitindo que os cadastros e votos efetuados não sejam perdidos ao encerrar a aplicação.

## Como melhorar o sistema

A adição de um banco de dados in-memory pode constribuir com a performance do sistema.
