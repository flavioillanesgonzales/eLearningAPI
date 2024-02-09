# eLearningAPI
## Tabla de Contenido

- User

- Course

  
- Lesson

  
- Question

  
- Answer
The Answer Controller handles requests related to answers in the e-learning system.
## Get Answer by ID
### Enpoint
```http
GET /answers/{answerId}
```
#### Description
Retrieve details for a specific answer by providing its unique identifier.

#### Parameters
answerId (PathVariable): The unique identifier for the answer.

#### Response
200 OK: Successful retrieval of the answer.
```http
{
    "id": 1,
    "text": "JavaScript",
    "correct": true
}
```
404 Not Found: If the answer with the provided ID does not exist.

---

## Get All Answers for a Question
### Enpoint
```http
GET /answers/question/{questionId}
```
#### Description
Retrieve all answers associated with a specific question.

#### Parameters
questionId (PathVariable): The unique identifier for the question.

#### Response
200 OK: Successful retrieval of the answer.
```http
[
    {
        "id": 1,
        "text": "JavaScript",
        "correct": true
    },
    {
        "id": 2,
        "text": "Python",
        "correct": false
    }
]

```
404 Not Found: If the question with the provided ID does not exist.


---

- Login User
### Enpoint
```http
POST /login/user
```
#### Description
Authenticate a user by providing their username and password.

#### Parameters
username (RequestBody): The username of the user.
password (RequestBody): The password of the user.

#### Response
200 OK: Successful retrieval of the answer.
```http
{
  "id": 1,
  "username": "frits",
  "password": "******"
}

```
```http
401 Unauthorized: If the provided username or password is incorrect.
{
  "error": "Usuario o contraseña incorrectos"
}
```

- Question
## Get All Questions
### Enpoint
```http
GET /questions
```
#### Description
Retrieve a list of all questions.

#### Response
200 OK: Successful retrieval of questions.
```http
[
    {
        "id": 1,
        "text": "¿Cuál es el lenguaje de programación más utilizado en el desarrollo web?",
        "type": "Marcar",
        "score": 15,
        "answers": [
            {
                "id": 1,
                "text": "JavaScript",
                "correct": true
            },
            {
                "id": 2,
                "text": "Python",
                "correct": false
            },
            {
                "id": 3,
                "text": "Java",
                "correct": false
            },
            {
                "id": 4,
                "text": "HTML",
                "correct": false
            }
        ]
    },
    {
        "id": 2,
        "text": "¿Qué es un servlet en Java?",
        "type": "Marcar",
        "score": 15,
        "answers": [
            {
                "id": 5,
                "text": "Un componente de interfaz de usuario en Java",
                "correct": true
            },
            {
                "id": 6,
                "text": "Un tipo de base de datos",
                "correct": false
            },
            {
                "id": 7,
                "text": "Una tecnología de red",
                "correct": false
            },
            {
                "id": 8,
                "text": "Un lenguaje de programación",
                "correct": false
            }
        ]
    }
]

```
### Get Question by ID
### Enpoint
```http
GET /questions/{questionId}
```
#### Description
Retrieve a specific question by its ID.

#### Parameters
questionId (PathVariable): The ID of the question.

#### Response
200 OK: Successful retrieval of the question.
```http
{
        "id": 2,
        "text": "¿Qué es un servlet en Java?",
        "type": "Marcar",
        "score": 15,
        "answers": [
            {
                "id": 5,
                "text": "Un componente de interfaz de usuario en Java",
                "correct": true
            },
            {
                "id": 6,
                "text": "Un tipo de base de datos",
                "correct": false
            },
            {
                "id": 7,
                "text": "Una tecnología de red",
                "correct": false
            },
            {
                "id": 8,
                "text": "Un lenguaje de programación",
                "correct": false
            }
        ]
}

```
```http
404 Not Found: If the question with the specified ID is not found.


--- 
### Create Question
### Enpoint
```http
POST /questions
```
#### Description
Create a new question.

Request
text (RequestBody): The text of the question.
type (RequestBody): The type of the question (e.g., "MULTIPLE_CHOICE", "TRUE_FALSE").
answers (RequestBody): A list of answers for the question.

#### Response
200 Created: Successful creation of the question.

--- 
### Update Question
### Enpoint
```http
PUT /questions/{questionId}
```
#### Description
Update an existing question.

Request
text (RequestBody): The updated text of the question.
type (RequestBody): The updated type of the question (e.g., "MULTIPLE_CHOICE", "TRUE_FALSE").
answers (RequestBody): The updated list of answers for the question.

#### Response
200 OK: Successful update of the question.

---

### Delete Question
### Enpoint
```http
DELETE /questions/{questionId}
```
#### Description
Delete a specific question by its ID.

Parameters
questionId (PathVariable): The ID of the question.

#### Response
204 No Content: Successful deletion of the question.

---

### Get All Questions for Lesson
### Enpoint
```http
GET /questions/lesson/{lessonId}
```
#### Description
Retrieve a list of all questions for a specific lesson.

Parameters
lessonId (PathVariable): The ID of the lesson.

#### Response
200 OK: Successful retrieval of questions.

---

### Get All Questions for Lesson
### Enpoint
```http
GET /questions/lesson/{lessonId}
```
#### Description
Retrieve a list of all questions for a specific lesson.

Parameters
lessonId (PathVariable): The ID of the lesson.

#### Response
200 OK: Successful retrieval of questions.
---
- Course
### Get All Courses
### Enpoint
```http
GET /courses
```
#### Description
Retrieve a list of all courses.

#### Response
200 OK: Successful retrieval of courses.
```http
  {
    "id": 1,
    "name": "Introduction to Programming",
    "description": "Learn the basics of programming.",
    "enrolled": true
  },
  {
    "id": 2,
    "name": "Web Development Fundamentals",
    "description": "Explore the fundamentals of web development.",
    "enrolled": false
  }
```

---
#### Get Course by ID
### Enpoint
```http
GET /courses/{courseId}
```
#### Description
Retrieve a specific course by its ID.

### Parameters
courseId (PathVariable): The ID of the course.

### Response
200 OK: Successful retrieval of the course.

---
#### Create Course
### Enpoint
```http
POST /courses
```
#### Description
Create a new course.

### Request
ame (RequestBody): The name of the course.
description (RequestBody): The description of the course.

### Response
201 Created: Successful creation of the course.

---
#### Update Course
### Enpoint
```http
PUT /courses/{courseId}
```
#### Description
Update an existing course.

### Parameters
courseId (PathVariable): The ID of the course.

### Request
name (RequestBody): The updated name of the course.
description (RequestBody): The updated description of the course.

### Response
200 OK: Successful update of the course.



