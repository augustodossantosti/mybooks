
Esta aplicação atua como um gerenciador de itens para leitura, permitindo upload e download dos aquivos.
É utilizado REST como estilo arquitetural e seu processo de autenticação de usuários é realizado com JWT(Json Web Token).
Ela se comunica com o front-end (ou qualquer outra aplicação que consuma seus serviços) através do envio e recebimento 
de json (possui camada de serialização e deserialização). O projeto é desenvolvido sobre o framework Spring, mais especificamente Spring Boot.
O projeto possuí duas configurações de SGBD. MySQL para execução do sistema e HSQLDB para os testes de unidade e testes de
integração partindo do Controller (Incluíndo filtros do Spring Security e processo de autenticação).

## Demonstração da Arquitetura

![arquiterura](https://cloud.githubusercontent.com/assets/14075325/22071278/c8f96f0a-dd85-11e6-948c-ff895f2f268b.jpg)

## Informações sobre execução e pré-requisitos:

 - O sistema possue dois profiles, dev e test, com as configurações do MySQL e HSQLDB respectivamente. Em "application.properties"
 é possível definir o usuário ativo e algumas configurações de execução.
 - É necessário ter o MySQL instalado e um schema chamado mybooks caso o profile de dev seja definido como ativo. Para que
 as tabelas sejam criadas no MySQL basta definir - spring.jpa.hibernate.ddl-auto = create - no propertie de dev na primeira execução
 do sistema para que o arquivo data.sql seja executado. Depois basta retornar para - spring.jpa.hibernate.ddl-auto = none - para que
 a estrutura das tabelas não seja mais alterada.
 - Java 1.8 
