# Kirana Trackr

## Problem Statement 
Develop a backend service designed to empower Kirana stores in managing their
transaction registers. This service aims to facilitate the daily tracking of all credit and debit
transactions, providing a comprehensive solution for effective financial record-keeping.

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
Response 
```dtd
{
  "id": "658c09368f175a1b67b121da",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890"
}
```