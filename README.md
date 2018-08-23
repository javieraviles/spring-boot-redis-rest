# spring-boot-redis-rest

API REST boilerplate with Spring Boot and Redis as database.

AVAILABLE ENDPOINTS

| method            | resource          | description                                                                                   |
|:------------------|:------------------|:----------------------------------------------------------------------------------------------|
| `GET`			| `/users`		| get the collection of users																	|
| `GET`			| `/users/:id`	| get a user by Id																				|
| `POST`			| `/users`		| creates a user in the DB (object user to be includued in request's body)						|
| `PUT`			| `/users/:id`	| updates an already created user in the DB (object user to be includued in request's body)		|
| `DELETE`		| `/users/:id`	| deletes a user from the DB																	|