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
