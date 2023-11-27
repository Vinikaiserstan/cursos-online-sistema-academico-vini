CREATE TABLE Curso(
    id SERIAL PRIMARY KEY,
    nome VARCHAR (100) NOT NULL,
    status  VARCHAR (50) NOT NULL,
    carga_horaria INT NOT NULL,
);