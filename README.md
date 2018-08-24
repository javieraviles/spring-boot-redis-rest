# spring-boot-redis-rest

API REST boilerplate with Spring Boot and Redis as database.

AVAILABLE ENDPOINTS

| method            | resource          | description                                                                                   |
|:------------------|:------------------|:----------------------------------------------------------------------------------------------|
| `GET`			| `/users`		| get the collection of users -> 200(OK)														|
| `GET`			| `/users/:id`	| get a user by Id -> 200(OK), 400(wrong id format), 404(no user with such id)					|
| `POST`			| `/users`		| creates a user in the DB -> 201(created)														|
| `PUT`			| `/users/:id`	| updates a user in the DB -> 204(updated), 400(wrong id format), 404(no user with such id)		|
| `DELETE`		| `/users/:id`	| deletes a user from the DB -> 204(deleted), 400(wrong id format), 404(no user with such id)	|