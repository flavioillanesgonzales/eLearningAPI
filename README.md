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
404 Not Found: If the answer with the provided ID does not exist.

