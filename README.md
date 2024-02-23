## Overview

This project implements an Order Management System use case using a microservices architecture. This project implements various patterns that are prevelant and lay the foundation stone in the microservices development world. Project uses Spring Boot for the development and Docker for contatinerization.

## Features

- **Microservices Architecture**: The system is built on a modular architecture to enhance scalability, maintainability, and flexibility.

- **Spring Cloud Netflix Eureka**: Service registration for automatic registration and dynamic discovery.

- **Spring Cloud Open Feign**: To make REST API calls (Synchronous communication) between multiple microservices.

- **Spring Cloud Gateway**: API Gateway implementation for centralized API management, providing a single entry point to the clients for accessing various functionalities of the application.

- **Resilience4j Circuit Breaker**: Implemented to prevent cascading failures and enhance system stability and fault-tolerance.

- **Docker Compose**: Comprehensive Docker Compose file provided for easy setup of the entire application and its dependencies.

## Services

### Product Service

Product service provides the client endpoints to create products in the database and get products from the database. Product service maintains its own seperate database. Product service internally communicates to the Inventory Service via REST API call to update the available quantity for the product at time of product creation in database. 

### Order Service

Order service provides the client with a create order endpoint to place the order for which client needs to supply the list of product codes for which the order service internally calls the Inventory service tp fetch the available quantities of the requested products, after which the demanded quantities are compared against available quantities for each product. If demanded quantities can be provided, the order service internally calls Inventory service to update the available quantities in database and the order is successfully placed. Order service also provides another endpoint to get the created orders.

Order service implements the circuit breaker pattern where if the inventory service is down, fallback method is executed providing proper fallback response to client.

### Inventory Service

Inventory service maintains the available quantity of products in the database.

## How to use

To run the project follow the given steps:

1. Create a directory named `test`.
2. Downlaod the `compose.yaml` file in `test` directory.
3. In the `test` directory, make a new directory `data`.
4. Go into `data` directory.
5. Create three new directories named `product`, `order`, `inventory`.
6. Come back to `test` directory and run command `docker compose up -d`.

## Endpoints

**Note:** Access Eureka at `http://localhost:8761/`

| Method | Path | Description |
|----------|----------|----------|
| GET | http://localhost:8080/products | Get all products |
| POST | http://localhost:8080/products | Create Product |
| GET | http://localhost:8080/orders | Get all orders |
| POST | http://localhost:8080/orders | Create Order |

### Example body for Create Product endpoint

```
{
    "productName":"Samsung Galaxy S24 Ultra",
    "productDescription":"Embrace the Galaxy A.I.",
    "price":168000,
    "quantity":100
}
```

### Example body for Create Order endpoint

```
{
    {
    "billingAddress":"House No. 123, xyz street",
    "deliveryAddress":"House No. 123, xyz street",
    "orderItemRequests":[
        {
            "productId":"product id obtained from Get all products endpoint",
            "quantity":1
        }
    ]
}
}
```
