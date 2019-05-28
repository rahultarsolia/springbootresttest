# API Document

This is a simple API to handle customer information

## Interface

### **POST** /customer
Submit a new customer.


#### Request Parameters

None

#### Request Body

Content type: application/json

Parameters | Type | Notes
---------- | ---- | -----
firstName | string | 
lastName | string |
dob | string | Date formated as YYYY-MM-DD e.g 5th Feb 2019 is 2019-02-05


#### Response Body

Content type: application/json

Parameters | Type | Notes
---------- | ---- | -----
id | string | 
firstName | string | 
lastName | string |
dob | string | Date formated as YYYY-MM-DD


### **GET** /customer/greet
Submit a new customer.


#### Request Parameters

id - The id assigned to the customer.

#### Request Body

None


#### Response Body

Content type: application/json

Parameters | Type | Notes
---------- | ---- | -----
id | string | Id of response to user
content | string |  The greeting. Will be either 1. Hello <firstName


### **GET** /customer/{id}/age
Get the age of a customer either on todays date or a specified date.


#### Request Parameters

onDate - Optional - The date on which the customers age should be calculated. This date can be in the past or future. If not provided the current date is used

#### Request Body

None


#### Response Body

Content type: application/json

Parameters | Type | Notes
---------- | ---- | -----
age | number | The age of a customer in years.



## Security

N/A


## Examples

### Get version number
GET localhost:8080/version

{
    "version": "DEV-BUILD"
}


### Add customer

POST localhost:8080/customer

Request body:
{
    "firstName": "Larry",
    "lastName": "Hopper",
    "dob": "2000-10-05"
}

Response Body:
{
    "id": 1,
    "firstName": "Larry",
    "lastName": "Hopper",
    "dob": "2000-10-05"
}

### Get greeting - No customer

GET localhost:8080/customer/greet?id=10

{
    "id": 1,
    "content": "Hello, Guest!"
}

### Get greeting - Valid customer
GET localhost:8080/customer/greet?id=1

{
    "id": 2,
    "content": "Hello, Larry Hopper!"
}


### Get age - Specific date

localhost:8080/customer/1/age?onDate=2008-02-01
{
    "age": 7
}


### Get age - For current date

localhost:8080/customer/1/age
{
    "age": 18
}




### API Documentation
