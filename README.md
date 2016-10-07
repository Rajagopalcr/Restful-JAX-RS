# Restful-JAX-RS
Developing Restful APIs with JAX-RS

Created using: jersey and JAX-RS
Maven : jersey 2.16 version

Messenger is a simple Restful API which covers fundamentals for the developing of Restful APIs.

Testing URLs: 

Create Message:-

	URI: http://localhost:8080/messender/webapi/messages
	METHOD: POST
	Headers: ContentType = application/json
	Data: {
		"author": "Rakshith",
		"date": "2016-10-07T16:59:24.929",
		"message": "Hello World!"
	}


Read all meesage:-

	URI: http://localhost:8080/messender/webapi/messages
	METHOD: GET
	Headers: ContentType = application/json
	
Read message:-

	URI: http://localhost:8080/messender/webapi/messages/1
	METHOD: GET
	Headers: ContentType = application/json
	
Update message:-

	URI: http://localhost:8080/messender/webapi/messages
	METHOD: PUT
	Headers: ContentType = application/json
	Data: {
		"author": "Rakshith",
		"date": "2016-10-07T16:59:24.929",
		"message": "Hello World!",
		"id":1
	}
	
Delete message:-

	URI: http://localhost:8080/messender/webapi/messages/1
	METHOD: DELETE
	Headers: ContentType = application/json	