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


