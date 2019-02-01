# Swagger Light Java Server


## Installation
```
mvn install
```

## Start server

Run with

```
mvn package exec:exec
```

and then access the URL for info over the services
```
http://localhost:8080/specui.html
```

Endpoint for working with Accounts:

1. `http://localhost:8080/account/{customerId}` - GET method
2. `http://localhost:8080/account/{customerId}` - POST method for creating account

### if port has to be changed then access:
`config/server.yml` and change value of `httpPort`

