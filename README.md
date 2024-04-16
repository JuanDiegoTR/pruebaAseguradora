# pruebaAseguradora

#Link de la documentacion de swagger

http://localhost:8080/swagger-ui/index.html#/

#Colección de postman 

curl --location 'http://localhost:8080/api/v1/secured/calculateLiquidation' \
--header 'Content-Type: application/json' \
--data '{
    "identType": 1,
    "identNumber": "51000002",
    "insuredAmount" : 100000,
    "dateOfBirth": "2003-03-22"
}'