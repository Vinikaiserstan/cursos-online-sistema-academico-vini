CREATE TABLE Nota (
    id SERIAL PRIMARY KEY,
    aluno_id INT REFERENCES Aluno(id) NOT NULL,
    disciplina_id INT REFERENCES Disciplina(id) NOT NULL,
    valor INT NOT NULL
);

