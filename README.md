
# 👤 User Profile Service

Este é um microserviço robusto desenvolvido em **Spring Boot** para o gerenciamento de perfis de usuários. A aplicação foca em segurança, escalabilidade e facilidade de configuração local.

## 🚀 Arquitetura

A aplicação foi desenhada para rodar de forma híbrida durante o desenvolvimento:

* **Aplicação:** Spring Boot rodando na máquina host para facilitar o debug.
* **Banco de Dados:** PostgreSQL isolado em um container Docker.
* **Segurança:** Autenticação Stateless via JWT (JSON Web Tokens).

---

## 🛠 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3** (Data JPA, Security, Web)
* **JWT** (JSON Web Token)
* **PostgreSQL 16**
* **Docker & Docker Compose**
* **Maven**

---

## ⚙️ Configuração e Instalação

### 1. Pré-requisitos

* Java 17 ou superior instalado.
* Docker e Docker Compose instalados.
* Maven instalado (ou utilize o `mvnw` incluso).

### 2. Variáveis de Ambiente

A aplicação utiliza variáveis de ambiente para proteger dados sensíveis. Crie um arquivo **`.env`** na raiz do projeto (este arquivo está no `.gitignore`):

```env
DB_URL=jdbc:postgresql://localhost:5432/user_profile_db
DB_USERNAME=user_profile_user
DB_PASSWORD=user_profile_pass
JWT_SECRET=seu_segredo_jwt_aqui_muito_longo_e_seguro

```

### 3. Subindo o Banco de Dados

O projeto utiliza Docker para instanciar o PostgreSQL rapidamente:

```bash
docker-compose up -d

```

### 4. Executando a Aplicação

Com o banco rodando, inicie o serviço Spring Boot:

```bash
mvn spring-boot:run

```

---

## 🔒 Segurança

A segurança é implementada via **Spring Security** com foco em:

* **JWT:** Tokens gerados no login e validados em cada requisição protegida.
* **Externalização:** O segredo do token (`JWT_SECRET`) nunca é exposto no código fonte, sendo lido diretamente do ambiente.

---

## 📂 Estrutura de Arquivos Chave

* `src/main/resources/application.yml`: Configurações gerais que referenciam as variáveis de ambiente.
* `docker-compose.yml`: Definição do serviço de banco de dados.
* `.env`: (Não versionado) Contém as credenciais locais.
