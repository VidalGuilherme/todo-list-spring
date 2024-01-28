# Simple To Do List API RESTful         
This repository contains a simple API RESTful project built using Java Spring. The aim of this repository is to practice and share how you can build a API using Java Spring.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Database](#database)

## Installation

1. Clone the repository:

```bash
$ git clone git@github.com:VidalGuilherme/todo-list-spring.git
```

2. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080/api/


## API Endpoints
The API provides the following endpoints:

### TODO LISTS
```markdown
GET /todo_lists - Retrieve a list of all todo lists.

POST /todo_lists - Register a new todo list.

PUT /todo_lists/{id} - Alter an existing todo list.

DELETE /todo_lists/{id} - Delete an existing todo list.
```

### TODO ITEMS
```markdown
GET /todo_items - Retrieve a list of all todo items.

POST /todo_items - Register a new todo item.

PUT /todo_items/{id} - Alter an existing todo item.

DELETE /todo_items/{id} - Delete an existing todo item.
```

## Database
The project uses PostgresSQL as the database.
Spring boot to automatically load my database schema when I start it up
```markdown
spring.jpa.generate-ddl=true
```

## Docker

You can run a database for this project with Docker by running the following command:

```bash
$ docker-compose up -d
```

To install Docker locally you can [click here](https://www.docker.com/products/docker-desktop/).