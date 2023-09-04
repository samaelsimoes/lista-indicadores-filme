# lista-indicadores-filme

API desenvolvida como uma demonstração de conhecimento, destinada a ilustrar conceitos fundamentais relacionados a APIs RESTFUL. 
Essa API tem como propósito principal a leitura de dados de filmes a partir de um arquivo no formato CSV e a criação de uma base de dados em memória para uso temporário. 
Os dados dos filmes carregados do arquivo são armazenados na base de dados temporária e podem ser consultados enquanto a API estiver em execução no servidor.

Principais ferramentas e tecnologias utilizadas no desenvolvimento da API incluem:

- Spring Boot Framework: Um framework Java que simplifica o desenvolvimento de aplicativos, incluindo serviços RESTful.
- H2 Database: Um banco de dados em memória utilizado para armazenar temporariamente os dados dos filmes.
- Java Persistence API (JPA): Uma API Java que fornece um meio de mapeamento objeto-relacional para a manipulação dos dados na base de dados.

Open CSV: Uma biblioteca que facilita a leitura de dados de arquivos CSV.
Hibernate: Um framework de mapeamento objeto-relacional que funciona em conjunto com o JPA para simplificar o acesso aos dados da base de dados.

Principais funcionalidades da MovieAPI incluem:
- Criação de base de dados ao iniciar a API: Quando a API é iniciada, ela cria uma base de dados em memória para armazenar os dados dos filmes.
- Leitura de arquivo CSV: A API lê um arquivo CSV localizado na pasta "Resources/files/movielist.csv" e carrega os dados desse arquivo na base de dados.
- Endpoint para consulta de produtores: Através do endpoint com o endereço "movie/producers/interval", 
os usuários podem obter informações sobre o(s) produtor(es) com o maior intervalo entre dois prêmios consecutivos, 
bem como o(s) produtor(es) que conquistaram dois prêmios mais rapidamente.

Para executar a aplicação:
- Você precisa ter o Java 8 instalado e uma IDE configurada com o Maven. Após configurar o Maven, você pode clonar o repositório do projeto no 
GitHub https://github.com/samaelsimoes/lista-indicadores-filme

usando o comando "git clone endereco", onde "endereco" deve ser substituído pela URL do repositório. 
Em seguida, você pode importar o projeto Maven em sua IDE, selecionando a pasta onde o projeto foi baixado. 
O Maven cuidará do download automático das dependências necessárias e, quando o download estiver concluído, a aplicação estará pronta para ser compilada e executada.

Para testar a API, você pode utilizar um navegador ou ferramentas como o Postman ou Insonia para montar e enviar requisições aos endpoints da API, 
incluindo o endpoint para obter informações sobre os produtores de filmes com intervalos de prêmios específicos.

Toda vez que você subir o projeto ele deve criar o banco H2 conforme o print, ira apresentar no terminal do intellij
![image](https://github.com/samaelsimoes/lista-indicadores-filme/assets/29442511/7c03b81a-6f86-4e1c-be92-be9a2ce0be94)
![image](https://github.com/samaelsimoes/lista-indicadores-filme/assets/29442511/7643a4c9-e481-412f-afba-afd819ce36f2)


Após você poderá consultar em http://localhost:8080/h2-console se as tabelas foram criadas e se os dados foram inseridos!
![image](https://github.com/samaelsimoes/lista-indicadores-filme/assets/29442511/e3a7ac42-0fee-4ee4-ad7f-9e8f90245dea)

Realizando Testes na API:

Para avaliar e testar a funcionalidade da API, você tem à sua disposição duas opções: a utilização direta do seu navegador ou a utilização de ferramentas especializadas que simplificam a criação e envio de requisições. Um exemplo notável dessas ferramentas é o Postman.

Você pode utilizar essas opções para testar o endpoint responsável por fornecer informações sobre os produtores com os intervalos mínimos e máximos entre os prêmios dos filmes.

![image](https://github.com/samaelsimoes/lista-indicadores-filme/assets/29442511/193a5c14-8425-443f-b3c7-a86bd0128f52)

