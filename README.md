# KiranaTrackr

## Introduction
KiranaTrackr is a robust backend service designed to empower Kirana stores in efficiently managing their daily credit and debit transactions. The service provides comprehensive solutions for effective financial record-keeping, leveraging the power of Java and SpringBoot.

## Features
1. **User Management**
    - Create a new user with name, email, password, and phone number.
    - Retrieve user details by user ID.

2. **Store Management**
    - Create a new store with name, description, address, owner ID, and local currency.
    - Retrieve store details by store ID.

3. **Transaction Management**
    - Record transactions with details such as amount, payment type, currency type, customer ID, and store ID.
    - Retrieve transaction details by transaction ID or store ID.
    - Group transactions for daily reports.

4. **Daily Reports**
    - Generate daily reports based on a specific date, showcasing transaction details and balances.

## Technology Stack
- Java
- Spring Boot
- MongoDB

## KiranaTrackr API Documentation

#### 1. Create User
Endpoint : POST
```dtd
http://localhost:8080/api/users
```
Request Body
```dtd
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "secretpassword",
  "phoneNumber": "1234567890"
}
```
Response: Status: 201 Created

#### 2. Get User (By User Id)
Endpoint : GET
```dtd
http://localhost:8080/api/users/{userId}
```
Response Body
```dtd
{
    "id": "658cefc33d3d682ba28ecb53",
    "name": "Arjav",
    "email": "arjav@example.com",
    "phoneNumber": "1234567890",
    "createdAt": "2023-12-28T09:17:15.494"
}
```
Response: Status: 200 OK

#### 3. Create Store
Endpoint : POST
```dtd
http://localhost:8080/api/stores
```
Request Body
```dtd
{
    "name": "Rohan Bakery",
    "description": "A local Bakery store",
    "address": "141 Main Block",
    "storeOwnerId": "658cefc33d3d682ba28ecb53",
    "localCurrency": "INR"
}
```
Response: Status: 201 Created

#### 4. Get Store (By Store Id)
Endpoint : GET
```dtd
http://localhost:8080/api/stores/{storeId}
```
Response Body
```dtd
{
    "id": "658cefe43d3d682ba28ecb54",
    "name": "Arjav Bakery",
    "description": "A local Bakery store",
    "address": "141 Main Block",
    "storeOwnerId": "658cefc33d3d682ba28ecb53",
    "localCurrency": "INR",
    "createdAt": "2023-12-28T09:17:48.359"
}
```
Response: Status: 200 OK

#### 5. Create Transaction
Endpoint : POST
```dtd
http://localhost:8080/api/transactions
```
Request Body
```dtd
{
    "amount": 1000,
    "paymentType": "CASH",
    "currencyType": "INR",
    "customerId": "658ce867b23309137525863b",
    "storeId": "658cefe43d3d682ba28ecb54"
}
```
Response: Status: 201 Created


#### 6. Get Transaction (By Transaction Id)
Endpoint : GET
```dtd
http://localhost:8080/api/transactions/{transactionId}
```
Response Body
```dtd
{
    "id": "658cf0133d3d682ba28ecb55",
    "amount": 499527.9922122000,
    "paymentType": "CARD",
    "currencyType": "USD",
    "customerId": "658c09368f175a1b67b121da",
    "storeId": "658cefe43d3d682ba28ecb54",
    "createdAt": "2023-12-28T09:18:35.311"
}
```
Response: Status: 200 OK

#### 7. Get Transaction (By Store Id)
Endpoint : GET
```dtd
http://localhost:8080/api/transactions/store/{storeId}
```
Response Body
```dtd
[
    {
    "id": "658cf0133d3d682ba28ecb55",
    "amount": 499527.9922122000,
    "paymentType": "CARD",
    "currencyType": "USD",
    "customerId": "658c09368f175a1b67b121da",
    "storeId": "658cefe43d3d682ba28ecb54",
    "createdAt": "2023-12-28T09:18:35.311"
    },
    {
    "id": "658cf0323d3d682ba28ecb56",
    "amount": 200,
    "paymentType": "CASH",
    "currencyType": "INR",
    "customerId": "658c38d86cba9b3f459f7867",
    "storeId": "658cefe43d3d682ba28ecb54",
    "createdAt": "2023-12-28T09:19:06.171"
    }
]
```
Response: Status: 200 OK


#### 8. Get Daily Report 
Endpoint : GET
```dtd
http://localhost:8080/api/reports/DAILY?date={date}
```
Response Body
```dtd
[
    {
    "transactions": [
    {
    "id": "658ce004e26813519c1e9283",
    "amount": 6000,
    "paymentType": "CARD",
    "currencyType": "INR",
    "customerId": "658c09368f175a1b67b121da",
    "storeId": "658c1e873e9de45972c4a87d",
    "createdAt": "2023-12-28T08:10:04.94"
    },
    {
    "id": "658ce49d5009c50bf50d918f",
    "amount": 6000,
    "paymentType": "CARD",
    "currencyType": "INR",
    "customerId": "658c09368f175a1b67b121da",
    "storeId": "658c1e873e9de45972c4a87d",
    "createdAt": "2023-12-28T08:29:41.117"
    }
]
```
Response: Status: 200 OK