## Technical considerations



## Installation

# Required software:
1. Java 21
2. Apache Maven

From the root of the project, open a console and execute the following commands:

1. Build and package the project:

```sh
mvn clean install
```

2. Run the application:

```sh
java -jar target/utoppia-challenge-0.0.1-SNAPSHOT.jar
```

## URLs

# Application

Once the application has been started, it can be accessed on the following URL:

```sh
http://localhost:8080/
```

The database console can be found here:

```sh
http:localhost:8080/h2-console
```

And for testing purposes, there's an interface with the available endpoints:

```sh
http://localhost:8080/swagger-ui/index.html
```

## Endpoints

# Use case 1: 

Retrieve and display stored stock data from a database to the user.

```
POST /api/stock-quotes/update

Request body: 
{ "symbol": "AAPL" }

```

Expected result:

```
204. No content
```

Just in case the user inputs an unknown stock symbol, an error messge should be shown:

```
{
  "code": "no_information_found",
  "errors": {
    "symbol": "We could not find any information about the specified symbol"
  }
}
```
Becase the symbol is required, an error message should be shown in case the user enters an empty value for it:

```
{
  "code": "field_validation",
  "errors": {
    "symbol": "symbol cannot be empty"
  }
}
```








