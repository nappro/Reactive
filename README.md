# Reactive Java 8 Lambdas Play

Simple project which provides an API which accepts a list of json 'customer' objects in the body of a POST request.
The API takes a list of objects, sorts them by due time, and then returns them back as a sorted json array.
The API services the POST request using non-blocking/reactive facilities provided by Java 8 and the Play Framework.

A Json customer object array:
*[ { "id": 10000000, "name": "Ulysses Leon", "duetime": "2014-06-18T06:26:56-07:00", "jointime": "2015-04-08T12:47:16-07:00" }, { "id": 10000001, "name": "Bruce Cardenas", "duetime": "2013-12-28T14:11:12-08:00", "jointime": "2014-07-03T21:53:42-07:00" }, { "id": 10000099, "name": "Neville Mcdowell", "duetime": "2015-07-27T18:52:03-07:00", "jointime": "2014-07-22T23:43:23-07:00" } ] 

The POST request url is:
/sortCustomers

The Json data must be contained within the request body data.

## Installation

Clone the repo and import the project using Intellj.

## Usage

Use JMeter to load test the Http POST json request

## Credits

Project was developed using:

* Play 2.3.7 (Activator 1.2.12)
* Java - jdk1.8.0_20
* Intellj 13.1.5
