# todo-management-spring-boot


# Mini Todo Management Project using Spring Boot + Spring MVC + Hibernate 



Register User:

curl --location --request POST 'http://localhost:7771/api/v1/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName":"Saubia",
"lastName":"Zarnain",
"email":"ss@ss.com",
"password":"pass@123",
"mobileNumber":"+88787989",
"gender":"Female",
"birthday":"1980-04-03"
}’

LoginUser:

curl --location --request POST 'http://localhost:7771/api/v1/user/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"email":"ss@ss.com",
"password":"pass@123",  
}’

LogoutUser:

curl --location --request POST 'http://localhost:7771/api/v1/user/logout' \
--header 'Content-Type: application/json' \
--data-raw '{
"email": "ss@ss.com",
}’


CreateATodo:
curl --location --request POST 'http://localhost:7771/api/v1/todo/users/1/todos/' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Get up early",
"description": "Get up early and do exercise",
"targetDate": "2021-10-08",
"todoStatus": "Initial",
"category": "Morning"
}’

GetAllTodoForAUser
curl --location --request GET 'http://localhost:7771/api/v1/todo/users/1/todos/' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Finish lunch",
"description": "Eat well",
"targetDate": "2021-10-08",
"todoStatus": "Initial",
"category": "Afternoon"
}’


DeleteATodoForUser
curl --location --request DELETE 'http://localhost:7771/api/v1/todo/users/1/todos/1' \
--header 'Content-Type: application/json' \
--data-raw ‘'



GetByCriteria
curl --location --request GET 'http://localhost:7771/api/v1/todo/users/1/todos/getByCriteria' \
--header 'Content-Type: application/json' \
--data-raw '{
"category": "Morning"
}’

curl --location --request GET 'http://localhost:7771/api/v1/todo/users/1/todos/getByCriteria' \
--header 'Content-Type: application/json' \
--data-raw '{
“Status": “Completed"
}'

UpdateATodo
curl --location --request PUT 'http://localhost:7771/api/v1/todo/users/1/todos/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"todoStatus": "Completed",
"description" : "Try to complete soon",
"targetDate" : "2021-09-19"
}'

