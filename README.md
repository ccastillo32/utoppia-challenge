## Installation

### Required software:
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

Once the application has been started, if everything is ok, the following URL should be working:

```sh
http://localhost:8080/
```

The database console can be found here:

```sh
http:localhost:8080/h2-console
users: sa
password: <empty>
```

And for testing purposes, there s an interface with 2 available endpoints:

```sh
http://localhost:8080/swagger-ui/index.html
```

## Testing Endpoints

### Use case 1: 

Refresh and update the database with the latest stock data from the Finnhub API

#### Request:

```
POST /api/stock-quotes/update

Request body: 
{ "symbol": "AAPL" }

```

#### Response:

```
204. No content
```

Just in case the user inputs an unknown stock symbol, an error message should be shown:

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

### Use case 2: 

Retrieve and display stored stock data from a database to the user.

#### Request:

```
GET http://localhost:8080/api/stock-quotes?&symbol=AAPL&startDate=2024-04-28&endDate=2024-04-29&pageNumber=0&pageSize=10
```

None of the parameters are required. If none of them are indicated, the application will try to apply default values in order to make a valid request.


#### Response:

```
Http Status: 200 OK

{
    "results": [
        {
            "id": "a0730e8e-280e-4912-9463-a562fb3dd530",
            "symbol": "AAPL",
            "currentPrice": 169.30,
            "change": -0.3473,
            "percentChange": -0.3473,
            "highPrice": 171.34,
            "lowPrice": 169.19,
            "openPrice": 169.87,
            "previousClosePrice": 169.89,
            "updatedAt": "2024-04-26T20:00:01"
        }
    ],
    "totalElements": 1,
    "totalPages": 1,
    "offset": 0,
    "limit": 5
}
```

If there are no results for the filtering values applied, you will receive a 200 OK response with an empty value on the "results" tag.

## Unit testing

* FinnhunApiClientTests: Connects to the Finnhub API and checks that we receive a response for APPL Symbol. We cannot assert the attributes received because they change every time.

* FinnhubQuoteResponseTests: Transform the response received from the API, and give us the domain object (StockQuote) to use in our application.

* StockPersistenceAdapterTests: Connects to the database and save/update corresponding records.

* UpdateStockQuoteUseCaseTests: It tests the first use case (Refresh and update the database with the latest stock data from the Finnhub API)

* GetAllStockQuotesUseCaseTests: It tests the second use case (Retrieve and display stored stock data from a database to the user)

* UpdateStockQuoteControllerTests: It tests one REST controller (Tests methods for the second controller were not added).



