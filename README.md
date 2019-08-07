# SVEPC - Sistema de Votação de Entidades de Previdência Complementar

---

## Configuração do ambiente

#### Pré-requisito

1. Fazer o download, instalação e configuração do MySQL
2. Fazer o download e instalação do TomCat v9.0
3. Fazer o download e instalação do Eclipse for Java EE Developers

#### Configurando a base de dados

O script que configura a base de dados está localizado em:

    website/src/main/resources/scripts.sql

No terminal, executar:

    mysql -u <username> -p < scripts.sql

#### Configurando o servidor TomCat

No arquivo Servers/server.xml do servidor TomCat criado, adicionar o comando abaixo, logo após o \<Context\> existente nesse arquivo.

    <Context docBase="website" path="/website" reloadable="true" source="org.eclipse.jst.jee.server:website"/> // Já existe no arquivo server.xml
    <Context docBase="caminho/para/imagens" path="/teste" /> // Adicionar esse comando

#### Acessando o SVEPC

O ambiente está configurado. Agora, para abrir o sistema, inicie o servidor TomCat relacionado ao projeto "Run > Tomcat v9.0...".

Para abrir a página inicial, abra um navegador e entre com a URL:

    localhost:8080/website

## Itens da missão

1. Com base na descrição detalhada do projeto, avalie se o escopo está claro e coerente
para você. Caso julgue necessário esclarecer algum ponto, fique à vontade para
questionar.

  R: O escopo estava claro, coerente e seu tamanho estava compatível com o tempo dado
  para realizar o desafio.

2. Texto

3. Defina quais tecnologias você pretende usar para o desenvolvimento. Isso envolve todas
mesmo. Seja bem detalhista.

  R:

  1. Java

    1. Orientação a Objetos
    2. Coleções: List/Map
    3. Especificadores de acesso: private/public/protected
    4. Exceções
    5. Generics
    6. Pacotes
    7. Entrada e Saída de dados (File IO)
    8. Coletor de lixo (Garbage Collection)
    9. Arquivo de propriedades (Properties)
    10. JavaDoc
  2. TomCat
  3. JPA/Hibernate
  4. JSTL
  5. JSP
  6. HTML
  7. MySQL
  8. Git
  9. Servlet
  10. XML
  11. Maven
  12. Expressões Regulares

4. Estime um prazo de entrega para o trabalho completo em dias úteis.

5. Seguem alguns pontos importantes para a entrega de sua solução final:

  1. Tecnologia: a parte de servidor deve ser feita em Java, porém você está livre para
escolher frameworks, bibliotecas, etc, que julgar mais adequado para desenvolver a
atividade escolhida, mas lembre-se, se escolher tecnologias que não domina, a
qualidade da sua entrega pode ser afetada.
  2. Design e layout: você está livre para utilizar elementos básicos de interface, sem a
preocupação com design. O importante é estar funcional.
  3. Dados simulados: você está livre para simular o ambiente ao redor das atividades
escolhidas, para que elas funcionem isoladamente.
  4. Entrega funcional: deve ser gerado um ambiente de desenvolvimento para rodar o
seu código. Ou seja, deve-se enviar o link para acesso e teste online.
  5. Código-fonte: deve ser entregue o pacote com todo o código-fonte gerado.
  6. O código e comentários devem ser em português.

  R: O desafio seguiu os pontos acima. A comprovação pode ser realizada ao analisar o código fonte entregue. Observação: para simular uma eleição finalizada, foi alterada
  a data de fim da eleição na base de dados. Com isso, foi possível avaliar o relatório final e outras funcionalidades que utilizam esse estado da base de dados.
