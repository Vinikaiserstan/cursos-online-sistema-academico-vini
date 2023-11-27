CREATE TABLE Disciplina (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    curso_id INT REFERENCES Curso(id) NOT NULL
);
