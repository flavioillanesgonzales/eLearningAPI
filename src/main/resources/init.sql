
CREATE TABLE Course
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(1000)
);


INSERT INTO Course (name, description)
VALUES ('Java Programming', 'Advanced Java programming concepts');

CREATE TABLE Lesson
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    title     VARCHAR(255) NOT NULL,
    content   TEXT,
    course_id INT,
    FOREIGN KEY (course_id) REFERENCES Course (id)
);

INSERT INTO Lesson (title, content, course_id)
VALUES ('Object-Oriented Programming', 'Introduction to OOP in Java', 1),
       ('Exception Handling', 'Handling exceptions in Java', 1);

CREATE TABLE Question
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    text      VARCHAR(1000) NOT NULL,
    type      VARCHAR(50)   NOT NULL,
    score     INT           NOT NULL,
    lesson_id INT,
    FOREIGN KEY (lesson_id) REFERENCES Lesson (id)
);

INSERT INTO Question (text, type, score, lesson_id)
VALUES ('What is polymorphism in Java?', 'Multiple Choice', 5, 1),
       ('How do you define a custom exception in Java?', 'Boolean', 3, 2),
       ('What is the purpose of the "finally" block in Java?', 'Multiple Choice', 5, 2),
       ('Which keyword is used to implement encapsulation in Java?', 'Multiple Choice', 5, 1),
       ('Can a Java interface extend multiple interfaces?', 'Boolean', 3, 1);

CREATE TABLE Answer
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    text        VARCHAR(1000) NOT NULL,
    is_correct  BOOLEAN       NOT NULL,
    question_id INT,
    FOREIGN KEY (question_id) REFERENCES Question (id)
);

INSERT INTO Answer (text, is_correct, question_id)
VALUES ('A concept that allows a class to have multiple methods with the same name.', true,
        1),
       ('A design pattern in Java.', false, 1),
       ('True', true, 2),
       ('False', false, 2),
       ('To ensure that a block of code is always executed, regardless of exceptions.', true,
        3),
       ('To declare a custom exception class in Java.', false, 3),
       ('private', true, 4),
       ('public', false, 4),
       ('Yes', true, 5),
       ('No', false, 5);
