CREATE TABLE limite_diario (
    id SERIAL NOT NULL,
    agencia INT NOT NULL,
    conta INT NOT NULL,
    data DATE NOT NULL,
    PRIMARY KEY (id)
);