
# SchoolRegistration

A simple system for registrate students to courses.

# How to setup project

1. Clone

	git clone https://github.com/Rafaelbf84/schoolRegistration.git

2. Run MySQL

	docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=studentdb -e MYSQL_USER=sa -e MYSQL_PASSWORD=1234 mysql:5.7

3. Build
	
	cd schoolRegistration
	mvn clean install

4. Run

	docker-composer up


# Endpoints and payloads

The REST API is described below.

## Get all Students

### Request

`GET /api/v1/students`

    curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/students

### Response

	 HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 18:57:20 GMT

	[{"id":1,"firstName":"Albert","lastName":"Einstein"},{"id":2,"firstName":"Nikola","lastName":"Tesla"},{"id":3,"firstName":"Marie","lastName":"Curie"}]

## Create a new Student

### Request

`POST /api/v1/students`

	curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/students -d '{"firstName":"Rafael","lastName":"Ferreira"}'

### Response

	HTTP/1.1 201
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:06:25 GMT

	{"id":6,"firstName":"Rafael","lastName":"Ferreira"}


## Get a Student by ID

### Request

`GET /api/v1/students/{id}`

	curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/students/1

### Response

    HTTP/1.1 200
    Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:17:56 GMT

	{"id":1,"firstName":"Albert","lastName":"Einstein"}
	
## Edit a Student

### Request

`PUT /api/v1/students/{id}`

	curl -i -H 'Content-Type: application/json' -X PUT http://localhost:8080/api/v1/students/3 -d '{"firstName": "Marieee","lastName":"Curiee"}'

### Response

    HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:24:38 GMT
	
	{"id":3,"firstName":"Marieee","lastName":"Curiee"}

## Delete a Student

### Request

`DELETE /api/v1/students/{id}`

	curl -i -H 'Content-Type: application/json' -X DELETE http://localhost:8080/api/v1/students/3

### Response

    HTTP/1.1 200
	Content-Length: 0
	Date: Sat, 23 Apr 2022 19:26:42 GMT

## Get all unregistered Students

### Request

`GET /students/unregistered`

	curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/students/unregistered

### Response

    HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:15:09 GMT

	[{"id":4,"firstName":"Rafael","lastName":"Borges"}]

## Get the list of Courses by Student

### Request

`GET api/v1/students/{id}/courses`

	curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/students/1/courses

### Response

	HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:28:34 GMT

	[{"id":1,"name":"Quantum Pysics"}]

## Register a Student in a list of Courses

### Request

`POST api/v1/students/{id}/courses`

	curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/students/2/courses -d '[1,2]'

### Response

	HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:30:50 GMT

	[{"id":1,"name":"Quantum Pysics"},{"id":2,"name":"Chemistry"}]



## Get all Courses

### Request

`GET /api/v1/courses`

    curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/courses

### Response

	HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 18:57:20 GMT

	[{"id":1,"name":"Quantum Pysics"},{"id":2,"name":"Chemistry"}

## Create a new Course

### Request

`POST /api/v1/courses`

	curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/courses -d '{"name":"Chess"}'

### Response

	HTTP/1.1 201
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:06:25 GMT

	{"id":3,"name":"Chess"}

## Get a Course by ID

### Request

`GET /api/v1/courses/{id}`

	curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/courses/1

### Response

    HTTP/1.1 200
    Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:17:56 GMT

	{"id":1,"name":"Quantum Pysics"}
	
## Edit a Course

### Request

`PUT /api/v1/courses/{id}`

	curl -i -H 'Content-Type: application/json' -X PUT http://localhost:8080/api/v1/courses/3 -d '{"name":"Chessaaaa"}'

### Response

    HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:24:38 GMT
	
	{"id":3,"name":"Chessaaaa"}
	
## Delete a Course

### Request

`DELETE /api/v1/courses/{id}`

	curl -i -H 'Content-Type: application/json' -X DELETE http://localhost:8080/api/v1/courses/3

### Response

    HTTP/1.1 200
	Content-Length: 0
	Date: Sat, 23 Apr 2022 19:41:21 GMT

## Get a list of Students of a Course 

### Request

`GET /api/v1/courses/{id}/students`

	curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/courses/1/students

### Response

    HTTP/1.1 200
    Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Sat, 23 Apr 2022 19:42:59 GMT

	[{"id":1,"firstName":"Albert","lastName":"Einstein"},{"id":2,"firstName":"Nikola","lastName":"Tesla"}
	
