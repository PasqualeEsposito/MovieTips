CREATE TABLE Utente
(
    id_utente INT IDENTITY (1, 1) PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    nome      VARCHAR(255) NOT NULL,
    cognome   VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    genere    VARCHAR(255) NOT NULL,
    ddn       DATE         NOT NULL,
    ruolo     INT          NOT NULL
);

CREATE TABLE Film
(
    id_film       INT IDENTITY (1, 1) PRIMARY KEY,
    titolo        VARCHAR(255) NOT NULL,
    produzione    VARCHAR(255) NOT NULL,
    musiche       VARCHAR(255) NOT NULL,
    fotografia    VARCHAR(255) NOT NULL,
    sceneggiatura VARCHAR(255) NOT NULL,
    distribuzione VARCHAR(255) NOT NULL,
    durata        INT          NOT NULL,
    paese         VARCHAR(255) NOT NULL,
    attori        VARCHAR(255) NOT NULL,
    regia         VARCHAR(255) NOT NULL,
    genere        VARCHAR(255) NOT NULL,
    trama         VARCHAR(255) NOT NULL
);

CREATE TABLE Film_seguiti
(
    id_utente INT NOT NULL,
    id_film   INT NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES Utente (id_utente)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_film) REFERENCES Film (id_film)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Recensione
(
    id_recensione       INT IDENTITY (1, 1) PRIMARY KEY,
    valutazione         INT  NOT NULL,
    testo               text NOT NULL,
    numero_segnalazioni INT  NOT NULL,
    id_utente           INT  NOT NULL,
    id_film             INT  NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES Utente (id_utente)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_film) REFERENCES Film (id_film)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Notizia
(
    id_notizia INT IDENTITY (1, 1) PRIMARY KEY,
    titolo     VARCHAR(255) NOT NULL,
    testo      VARCHAR(255) NOT NULL,
    fonte      VARCHAR(255) NOT NULL,
    id_film    INT          NOT NULL,
    FOREIGN KEY (id_film) REFERENCES Film (id_film)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);