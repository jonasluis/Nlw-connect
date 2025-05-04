# Sistema de Inscrições em Eventos

Um sistema moderno para gerenciamento de inscrições em eventos com recursos de indicação e rastreamento.

## Visão Geral

Este Sistema de Inscrições permite que organizadores criem eventos e usuários se inscrevam neles. Cada usuário registrado recebe um link único de indicação que pode compartilhar para convidar outros. O sistema rastreia essas indicações e fornece um ranking dos indicadores mais bem-sucedidos.

## Funcionalidades

- **Gestão de Eventos**: Criar, listar e visualizar detalhes dos eventos
- **Inscrição de Usuários**: Processo simples requerendo apenas nome e email
- **Sistema de Indicação**: Geração automática de links únicos de indicação
- **Rastreamento de Indicações**: Monitore quantas pessoas se registram através do seu link
- **Sistema de Ranking**: Veja quem tem mais indicações bem-sucedidas

## Endpoints da API

### Gestão de Eventos

#### Criar Novo Evento
```
POST /events

Requisição:
{
  "name": "CodeCraft Summit 2025",
  "location": "Online",
  "price": 0.0,
  "startDate": "2025-03-16",
  "endDate": "2025-03-18",
  "startTime": "19:00:00",
  "endTime": "21:00:00"
}

Resposta:
{
  "id": 1,
  "name": "CodeCraft Summit 2025",
  "prettyName": "codecraft-summit-2025",
  "location": "Online",
  "price": 0.0,
  "startDate": "2025-03-16",
  "endDate": "2025-03-18",
  "startTime": "19:00:00",
  "endTime": "21:00:00"
}
```

#### Listar Todos os Eventos
```
GET /events

Resposta:
[
  {
    "id": 1,
    "name": "CodeCraft Summit 2025",
    "prettyName": "codecraft-summit-2025",
    "location": "Online",
    "price": 0.0,
    "startDate": "2025-03-16",
    "endDate": "2025-03-18",
    "startTime": "19:00:00",
    "endTime": "21:00:00"
  },
  ...
]
```

#### Buscar Evento por Pretty Name
```
GET /events/{PRETTY_NAME}

Exemplo:
GET /events/codecraft-summit-2025

Resposta:
{
  "id": 1,
  "name": "CodeCraft Summit 2025",
  "prettyName": "codecraft-summit-2025",
  "location": "Online",
  "price": 0.0,
  "startDate": "2025-03-16",
  "endDate": "2025-03-18",
  "startTime": "19:00:00",
  "endTime": "21:00:00"
}
```

### Inscrições

#### Inscrever-se em um Evento
```
POST /subscription/{PRETTY_NAME}

Requisição:
{
  "userName": "João Silva",
  "email": "joao@email.com"
}

Resposta:
{
  "subscriptionNumber": 1,
  "designation": "http://codecraft.com/subscription/codecraft-summit-2025/123"
}
```

### Ranking

#### Ver Top Indicadores
```
GET /subscription/{PRETTY_NAME}/ranking

Exemplo:
GET /subscription/codecraft-summit-2025/ranking

Resposta:
[
  {
    "userName": "João Silva",
    "subscribers": 1000
  },
  {
    "userName": "Maria Santos",
    "subscribers": 873
  },
  {
    "userName": "Francisco Lima",
    "subscribers": 690
  }
]
```

#### Ver Estatísticas Pessoais de Indicação
```
GET /subscription/{PRETTY_NAME}/ranking/{USERID}

Exemplo:
GET /subscription/codecraft-summit-2025/ranking/123

Resposta:
{
  "rankingPosition": 3,
  "userId": 123,
  "name": "João Silva",
  "count": 600
}
```

## Casos de Uso

### Criação de Eventos
- Organizadores podem criar novos eventos
- Sistema gera automaticamente um pretty name para navegação amigável
- Eventos duplicados (com mesmo pretty name) são rejeitados

### Registro de Usuários
- Novos usuários são adicionados ao banco durante o registro
- Usuários existentes podem se registrar em novos eventos sem reinserir informações
- Usuários não podem se registrar duas vezes no mesmo evento
- Usuários podem se registrar via links de indicação

### Sistema de Indicação
- Cada registro gera um link único de indicação
- Quando alguém se registra através de um link de indicação, o usuário que indicou recebe crédito
- O sistema mantém um ranking dos indicadores mais bem-sucedidos

## Stack Tecnológica

- Backend: Java Spring Boot
- Banco de Dados: MySQL

## Como Começar

### Pré-requisitos
- Java 17 ou superior
- Maven
- PostgreSQL

### Instalação

1. Clone o repositório
```bash
git clone https://github.com/jonasluis/Nlw-connect.git
cd Nlw-connect
```
2. Rode a Querry do arquivo db_events.sql no seu MySQL Workbench

2. Configure o banco de dados no arquivo `application.properties`

3. Execute o projeto
```bash
mvn spring-boot:run
```
