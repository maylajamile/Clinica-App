CREATE TABLE usuarios (
    id UUID DEFAULT UUID() PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE enfermeiros (
    id UUID DEFAULT UUID() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE medicos (
    id UUID DEFAULT UUID() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (login, senha, role) VALUES
('fulano', '$2a$12$y6knI5PUagMk7MpvgX0r7O.cnOvwHS8CovoR0TP4rrRoCSOqsPWwS', 'MEDICO'),
('ciclano', '$2a$12$y6knI5PUagMk7MpvgX0r7O.cnOvwHS8CovoR0TP4rrRoCSOqsPWwS', 'ENFERMEIRO');

INSERT INTO medicos (nome, cpf, senha) VALUES
('fulano', '$2a$12$CDsee5iRLFTpggoxfL/1UO8aO2O95.OA37BANRRhZeERyq.99hCGm', '$2a$12$y6knI5PUagMk7MpvgX0r7O.cnOvwHS8CovoR0TP4rrRoCSOqsPWwS');

INSERT INTO enfermeiros (nome, cpf, senha) VALUES
('ciclano', '$2a$12$L.tnQQL0CN0DqppdF/WHuuGAISZXmbv6/gNT.jnale.CePHS77ITy', '$2a$12$y6knI5PUagMk7MpvgX0r7O.cnOvwHS8CovoR0TP4rrRoCSOqsPWwS');


