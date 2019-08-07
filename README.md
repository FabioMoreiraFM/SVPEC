# SVEPC - Sistema de Votação de Entidades de Previdência Complementar

## Configuração do ambiente

A configuração do ambiente descrita abaixo foi executada e testada no Windows 10.

#### Pré-requisito

1. Fazer o download, instalação e configuração do MySQL
2. Fazer o download e instalação do TomCat v9.0
3. Fazer o download e instalação do Eclipse for Java EE Developers

#### Configurando a base de dados

O script que configura a base de dados está localizado em:

    website/src/main/resources/scripts.sql

No terminal, executar:

    mysql -u <username> -p < scripts.sql

### Configurando no Eclipse

#### Passos inicias

1. Importe o projeto via Git. (File > Import... > Projects from Git)
2. Espere o Eclipse carregar o projeto.
3. Atualize o projeto via Maven. (Botão direito no projeto > Maven > Update Project..)
4. Adicione um novo servidor TomCat.
  1. Na aba "Servers", próxima a aba do console. Clique em "No servers are ...."
  2. Adicione o Tomcat v9.0 Server (Perceba que o eclipse criou um diretório Servers)

#### Configurando o servidor TomCat

No servidor TomCat recém criado (Aba Servers):

1. Botão direito > Add and Remove... > Adicione o projeto ao servidor (Mude o projeto da opção "Available" para "Configured")

No arquivo Servers/Tomcat v9.0.../server.xml do servidor TomCat criado, adicionar o comando abaixo, logo após o \<Context\> existente nesse arquivo. (Cuidado para não adicionar depois da tag \</Host>)

    <Context docBase="website" path="/website" reloadable="true" source="org.eclipse.jst.jee.server:website"/> // Já existe no arquivo server.xml
    <Context docBase="caminho/para/imagens" path="/teste" /> // Adicionar esse comando

O caminho em docBase deve ser substituido pelo caminho que leva até a pasta imagens, que deve estar, **necessariamente**, no mesmo diretório do projeto. (Se você importou corretamente pelo GIT é só adicionar o caminho daquela pasta que se encontra no repositório SVEPC)

Antes de executar o código, talvez seja necessário modificar o diretório de trabalho, para isso faça:

   1. Na aba Servers, botão direito em Tomcat V9.0 > Start e depois Tomcat V9.0 > Stop. Esse passo é necessário para que esse servidor apareça nos próximos passos.
   2. Menu Run > Run Configurations...
   3. Selecione o servidor TomCat v9.0, vá para a aba Arguments.
   4. Selecione a opção "Other" em "Working directory" e defina como diretório
   de trabalho o diretório website.
   5. Clique no botão Apply e feche.

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

2. Faça a estimativa em horas para cada atividade, considerando a tecnologia que você
pretende utilizar para desenvolver.

    1. Criação das telas: **5 horas** ou **30 min/tela** (em média). Seguem o mesmo padrão, com labels, forms e variáveis. Algumas telas demandam mais tempo, pois utilizam alguns comandos da JSTL. Considero que serão criadas o dobro de páginas em relação aos itens enumerados no escopo do desafio. Isso por que, além das 5 telas que serão criadas a partir dos cincos itens descritos, são necessários uma tela de boas-vindas, de exibição do relatório, de finalização do voto, entre outras.

    2. Implementação da base de dados: **3 horas**. Envolve a criação do esquema da base (modelo conceitual), criação do script para execução automática dos comandos e testes.

    3. Validadores: **1 hora**. Simples de se implementar, envolve o uso de regex e as bibliotecas do Java que realizam a validação. A estimativa também considera a integração dos validadores ao sistema.

    4. Processamento das imagens: **2 horas**. Existem muitas questões a se considerar. Onde a imagem deve ser armazenada, como salvar/recuperar essa imagem, como configurar o projeto para suportar essa operação (talvez a etapa mais demorada, pois envolve pesquisa e testes), como carregar a imagem assim que o usuário entrar com o arquivo.

    5. Gerador de protocolo: **2 horas**. A primeira ideia é procurar alguma biblioteca que gera esse protocolo de forma eficiente e segura, caso não existir, deve-se implementar uma versão simples.

    6. Comunicação com a base de dados: **3 horas**. Configurar a comunicação entre aplicações costuma ser trabalhoso, deve-se aprender como fazer, qual versão é compatível com a sua aplicação/IDE, podem existir bugs e outros problemas cuja solução só é encontrada em fóruns.

    7. Implementação dos Servlets: **20 horas** ou **2 horas/servlet** (em média). Parte mais complexa do trabalho. O foco principal dessas implementações está no método doPost, onde as requisições são processadas. Basicamente, aqui serão realizadas as validações, acessos a base de dados, entre outras atividades de integração. Além disso, deve-se considerar como os servlets vão se comunicar e passar informações entre si. Considerando que para cada página JSP haverá um servlet, serão criados em torno de 10 servlets.   

    8. Implementação das classes de persistência: **1 hora**. A implementação é simples, envolve apenas a declaração de variáveis e alguns getters e setters.  

    9. Implementação de métodos que recuperam/armazenam informações da base de dados: **2 horas**. A maioria dos métodos deve envolver o armazenamento e recuperação de classes, o que é rápido e simples de implementar. Talvez existam alguns métodos mais complexos relacionados à geração do relatório.

    10. Testes: **2 horas**. Realização de testes para a homologação do sistema.

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

    R: Considerando 8 horas de trabalho por dia e as estimativas das atividades, exibidas na questão 2, o trabalho pode ser realizado em 5 dias, adicionando 1 dia de trabalho a mais para eventuais atrasos (resolução de bugs ou atividades subestimadas), ou seja, o prazo de entrega é de **6 dias**.

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

    R: O desafio seguiu os pontos acima. A comprovação pode ser realizada ao analisar o código fonte entregue. Observação: para simular uma eleição finalizada, foi alterada a data de fim da eleição na base de dados. Com isso, foi possível avaliar o relatório final e outras funcionalidades que utilizam esse estado da base de dados.
