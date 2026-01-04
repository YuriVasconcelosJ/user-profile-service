User Profile Service

Este projeto é um microserviço Spring Boot responsável pelo gerenciamento de perfis de usuários. Ele utiliza PostgreSQL como banco de dados, JWT para segurança e Docker apenas para subir a infraestrutura do banco.

Arquitetura

Spring Boot rodando localmente (host machine)

PostgreSQL rodando em container Docker

Comunicação via localhost

Configurações sensíveis externalizadas via variáveis de ambiente

Tecnologias Utilizadas

Java 17+

Spring Boot

Spring Data JPA

Spring Security (JWT)

PostgreSQL 16

Docker / Docker Compose

Maven

Estrutura de Configuração

application.yml

O arquivo application.yml não contém valores sensíveis diretamente. Ele apenas referencia variáveis de ambiente:

spring:
  application:
    name: userprofileservice

  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect

api:
  security:
    token:
      secret: ${JWT_SECRET}

Variáveis de Ambiente (.env)

As credenciais e segredos são definidos em um arquivo .env (não versionado):

DB_URL=jdbc:postgresql://localhost:5432/user_profile_db
DB_USERNAME=user_profile_user
DB_PASSWORD=user_profile_pass
JWT_SECRET=b9d1f7c8a3e4f6d2c7a8f1e9b0a4c5d6e7f8a9b1c2d3e4f5a6b7c8d9e0f1a2

📌 Importante:

O .env deve estar no root do projeto

Ele deve estar listado no .gitignore

🐳 Docker (PostgreSQL)

O Docker é utilizado apenas para o banco de dados.

docker-compose.yml

services:
  postgres:
    image: postgres:16
    container_name: user-profile-postgres
    restart: always
    environment:
      POSTGRES_DB: user_profile_db
      POSTGRES_USER: user_profile_user
      POSTGRES_PASSWORD: user_profile_pass
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:

Subir o banco

docker-compose up -d

Executando a Aplicação

Suba o PostgreSQL com Docker:

docker-compose up -d

Configure o arquivo .env

Execute o Spring Boot:

mvn spring-boot:run

Ou diretamente pela IDE (IntelliJ / VS Code).

Segurança

Autenticação baseada em JWT

O segredo do token é carregado via variável de ambiente

