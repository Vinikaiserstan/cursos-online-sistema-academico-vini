CREATE TABLE Curso_Aluno (
    curso_id INT REFERENCES Curso(id) NOT NULL,
    aluno_id INT REFERENCES Aluno(id) NOT NULL
);
