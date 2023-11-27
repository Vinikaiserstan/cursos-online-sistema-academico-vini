CREATE TABLE Curso_Professor (
    curso_id INT REFERENCES Curso(id) NOT NULL,
    professor_id INT REFERENCES Professor(id) NOT NULL
);
