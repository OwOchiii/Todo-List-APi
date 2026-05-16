# Todo List API

A robust RESTful API for a Todo List application built with **Spring Boot** and secured using **Spring Security & JSON Web Tokens (JWT)**.

## 🛠️ Tech Stack & Architecture
- **Language:** Java 17
- **Framework:** Spring Boot (Spring MVC, Spring Data JPA, Spring Security)
- **Security:** Stateless Authentication via JWT (jjwt)
- **Database:** PostgreSQL
- **Build Tool:** Gradle
- **Boilerplate Reduction:** Lombok

## 🚀 Setup & Installation

### 1. Prerequisites
- **Java 17** installed.
- **PostgreSQL** installed and running on your local machine.
- Your favorite API testing tool (e.g., [Postman](https://www.postman.com/) or [cURL](https://curl.se/)).

### 2. Configure Database
Update your `src/main/resources/application.properties` with your PostgreSQL credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```
*(Make sure to create a database named `todo_db` in PostgreSQL before starting.)*

### 3. Build and Run
Open your terminal in the project root directory and run:
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun
```
The server will start on `http://localhost:8080`.

---

## 📖 Tutorial: How to Use the API

Because this API uses **JWT (JSON Web Tokens)** for stateless authentication, you must first register or log in to receive a token. Then, include that token in the header of all subsequent requests to the `/api/todos` endpoints.

### Step 1: Register a New User
You need an account to create Todos.
- **Endpoint:** `POST /api/auth/register`
- **No Token Required**
- **Body (JSON):**
  ```json
  {
      "username": "johndoe",
      "email": "johndoe@example.com",
      "password": "secretpassword"
  }
  ```
- **Response:** You will receive a long string called a JWT token. Copy this!

### Step 2: Login (If you already have an account)
- **Endpoint:** `POST /api/auth/login`
- **No Token Required**
- **Body (JSON):**
  ```json
  {
      "username": "johndoe",
      "password": "secretpassword"
  }
  ```
- **Response:** Returns your active JWT token.

### Step 3: Add Your Token to the Header
For all the Todo endpoints below, you **MUST** include your JWT token in the HTTP Headers. If you don't, you will receive a `401 Unauthorized` error.
- **Header Key:** `Authorization`
- **Header Value:** `Bearer YOUR_COPIED_TOKEN_HERE` 
*(Note the space between "Bearer" and your token!)*

### Step 4: Manage Your Todos

**1. Create a new Todo**
- **Endpoint:** `POST /api/todos`
- **Headers:** `Authorization: Bearer <token>`
- **Body (JSON):**
  ```json
  {
      "title": "Buy Groceries",
      "description": "Milk, Eggs, Bread"
  }
  ```

**2. View all your Todos**
- **Endpoint:** `GET /api/todos`
- **Headers:** `Authorization: Bearer <token>`
- **Response:** Returns an array of your created Todos.

**3. Update a Todo**
- **Endpoint:** `PUT /api/todos/{id}` *(Replace {id} with the Todo ID from the GET response)*
- **Headers:** `Authorization: Bearer <token>`
- **Body (JSON):**
  ```json
  {
      "title": "Buy Groceries (Updated)",
      "description": "Milk, Eggs, Bread, and Coffee"
  }
  ```

**4. Delete a Todo**
- **Endpoint:** `DELETE /api/todos/{id}`
- **Headers:** `Authorization: Bearer <token>`
- **Response:** "Todo deleted successfully"

---

## 🛑 Common Errors
- `401 Unauthorized`: Your token is missing, misspelled (did you forget `Bearer `?), or has expired. Try logging in again to get a fresh token.
- `403 Forbidden`: Your user account might not have the correct permissions.
- `500 Internal Server Error`: Usually means the database isn't connected or configured correctly. Check your `application.properties` and Postgres server.

Project link: https://roadmap.sh/projects/todo-list-api
