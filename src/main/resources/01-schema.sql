-- Crear la tabla Course
CREATE TABLE Course
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(1000)
);

-- Crear la tabla Lesson
CREATE TABLE Lesson
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(255),
    content       VARCHAR(1000),
    course_id     INT,
    lesson_order  INT,
    passing_score INT,
    FOREIGN KEY (course_id) REFERENCES Course (id)
);


-- Crear la tabla Question
CREATE TABLE Question
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    text      VARCHAR(1000),
    type      VARCHAR(255),
    score     INT,
    lesson_id INT,
    FOREIGN KEY (lesson_id) REFERENCES Lesson (id)
);


-- Crear la tabla Answer
CREATE TABLE Answer
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    text        VARCHAR(1000),
    is_correct  BOOLEAN NOT NULL,
    question_id INT,
    FOREIGN KEY (question_id) REFERENCES Question (id)
);


-- Crear la tabla User
CREATE TABLE Users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255), -- Deberías almacenar las contraseñas de forma segura (hashing, salting).
    role     VARCHAR(255)
);


-- Crear la tabla user_course para la relación many-to-many
-- Crear la tabla user_course sin restricciones de clave externa

CREATE TABLE user_course
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id   INT,
    course_id INT,
    status VARCHAR(15),
    learning_score INT,
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (course_id) REFERENCES Course (id)
);


-- Datos de muestra para Course
INSERT INTO Course (name, description)
VALUES ('Introducción a la Programación', 'Curso introductorio sobre programación'),
       ('Desarrollo Web con Java', 'Aprende a desarrollar aplicaciones web con Java'),
       ('Machine Learning con Python', 'Exploración de algoritmos de aprendizaje automático'),
       ('Seguridad Informática', 'Principios fundamentales de seguridad informática');

-- Datos de muestra para Lesson
INSERT INTO Lesson (title, content, course_id, lesson_order, passing_score)
VALUES ('Conceptos Básicos de Programación', 'Introducción a variables, bucles y condicionales', 1, 1, 70),
       ('Fundamentos de POO', 'Temas técnicos sobre POO y estructuras', 1, 2, 75),
       ('CRUD', 'Aprende a crear un CRUD básico', 1, 3, 75),
       ('Desarrollo de Servlets en Java', 'Creación de aplicaciones web con Java Servlets', 2, 1, 70),
       ('Introducción a la Regresión Lineal', 'Primeros pasos en Machine Learning', 3, 1, 70),
       ('Principios de Firewalls y VPN', 'Seguridad en redes y comunicaciones', 4, 1, 70);

-- Datos de muestra para Question
INSERT INTO Question (text, type, score, lesson_id)
VALUES ('¿Cuál es el lenguaje de programación más utilizado en el desarrollo web?', 'Marcar', 15, 1),
       ('¿Qué es un servlet en Java?', 'Marcar', 15, 2),
       ('¿Cuál de los siguientes no es un algoritmo de clasificación?', 'Marcar', 10, 3),
       ('¿Qué es un virus informático?', 'Marcar', 15, 4),
       ('¿Cuál es el paradigma de programación orientada a objetos?', 'Marcar', 10, 1),
       ('¿En qué año se lanzó la primera versión de Java?', 'Marcar', 10, 2),
       ('¿Cuál es el propósito principal de un algoritmo de búsqueda binaria?', 'Marcar', 25, 3),
       ('¿Qué es un cortafuegos?', 'Marcar', 15, 4),

       ('¿Qué es un algoritmo?', 'Marcar', 10, 1),
       ('Cuál es el propósito de un bucle en programación?', 'Marcar', 10, 1),
       ('La declaración "int x = 10;" en Java crea una variable llamada "x" y le asigna el valor 10.',
        'Verdadero o Falso', 10, 1),

       ('Qué significa *debuggear* en programación?', 'Marcar', 15, 1),
       ('¿Qué es la sintaxis en programación?', 'Marcar', 10, 1),

       ('Un bucle *for* se utiliza para ejecutar un bloque de código repetidamente hasta que se cumple una condición específica.',
        'Verdadero o Falso', 10, 1),
       ('¿Cuál es la función de un comentario en código?', 'Marcar', 10, 1);
-- Datos de muestra para Answer
INSERT INTO Answer (text, is_correct, question_id)
VALUES ('JavaScript', true, 1),
       ('Python', false, 1),
       ('Java', false, 1),
       ('HTML', false, 1),
       ('Un componente de interfaz de usuario en Java', true, 2),
       ('Un tipo de base de datos', false, 2),
       ('Una tecnología de red', false, 2),
       ('Un lenguaje de programación', false, 2),
       ('Random Forest', false, 3),
       ('K-Means', false, 3),
       ('Decision Tree', false, 3),
       ('HTML', true, 4),
       ('Malware diseñado para dañar hardware', false, 4),
       ('Software malintencionado que se propaga a través de correos electrónicos', true, 4),
       ('Un dispositivo de almacenamiento', false, 4),
       ('Programación Procedimental', false, 5),
       ('Programación Orientada a Objetos', true, 5),
       ('Programación Funcional', false, 5),
       ('Programación Declarativa', false, 5),
       ('1995', true, 6),
       ('2000', false, 6),
       ('1990', false, 6),
       ('1985', false, 6),
       ('Ordenar elementos en forma ascendente o descendente', true, 7),
       ('Realizar operaciones aritméticas', false, 7),
       ('Buscar elementos en una lista', false, 7),
       ('Generar números aleatorios', false, 7),
       ('Un dispositivo de seguridad que controla el tráfico de red', true, 8),
       ('Un programa antivirus', false, 8),
       ('Una base de datos en la nube', false, 8),
       ('Un software de diseño gráfico', false, 8),

        ('Una instrucción única en un programa.', false, 9), --32
       ('Un conjunto de instrucciones ordenadas para realizar una tarea específica.', true, 9),
       ('Una variable en un lenguaje de programación.', false, 9),

       ('Realizar una operación matemática.', false, 10),
       ('Repetir un conjunto de instrucciones varias veces.', true, 10),
       ('Definir una condición booleana.', false, 10),

       ('Falso', false, 11),
       ('Verdadero', true, 11), --39

       ('Crear código desde cero.', false, 12),
       ('Corregir errores o bugs en el código.', true, 12),
       ('Comentar secciones de código.', false, 12),

       ('El significado o propósito de una línea de código.', false, 13),
       ('El conjunto de reglas que dictan cómo se escribe un programa en un lenguaje específico.', true, 13),
       ('Una herramienta de depuración.', false, 13), -- 45

       ('Falso', false, 14),
       ('Verdadero', true, 14),

       ('Ejecutar una acción específica.', false, 15),
       ('Proporcionar información o explicaciones sobre el código para los programadores.', true, 15),
       ('Evitar que una línea de código se ejecute.', false, 15); --50

-- Datos de muestra para User
INSERT INTO Users (username, password, role)
VALUES ('flavio', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'STUDENT'),
       ('marco', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'PROFESSOR'),
       ('lucas', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'STUDENT');

-- Datos de muestra para user_course (relación many-to-many)
INSERT INTO user_course (user_id, course_id, status, learning_score)
VALUES (1, 1, 'En proceso', 0),
       (1, 2, 'En proceso', 0),
       (2, 2, 'Aprobado',0),
       (3, 3, 'Aprobado', 0);

