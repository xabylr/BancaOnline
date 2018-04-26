CREATE DATABASE bancaonline;
GRANT ALL PRIVILEGES ON bancaonline.* TO 'bancaonline'@'localhost' IDENTIFIED BY 'pendejo';

USE bancaonline;

CREATE TABLE empleado (
    dni         INT PRIMARY KEY,
    password    VARCHAR(100),
    nombre      VARCHAR(30),
    apellidos   VARCHAR(100)
);



CREATE TABLE cuentacorriente (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    iban            VARCHAR(24),
    saldo           BIGINT,
    decimales       INT,
    divisa          VARCHAR(3),
    fechacreacion   BIGINT
);


CREATE TABLE cliente (
    dni         INT PRIMARY KEY,
    password    VARCHAR(100),
    nombre      VARCHAR(30),
    apellidos   VARCHAR(100),
    cuenta      INT,
    FOREIGN KEY (cuenta) REFERENCES cuentacorriente (id)
);


CREATE TABLE movimientorealizado (
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    concepto                VARCHAR(200),
    fechaCreado             BIGINT,
    fechaRealizado          BIGINT,
    cuantia                 BIGINT,
    decimales               INT,
    divisa                  VARCHAR(3),
    remitente               INT,
    saldoRemitentePrevio    BIGINT,
    receptor                INT,
    saldoReceptorPrevio     BIGINT,
    FOREIGN KEY (remitente) REFERENCES cuentacorriente (id),
    FOREIGN KEY (receptor)  REFERENCES cuentacorriente (id)
);

CREATE TABLE movimientopendiente (
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    concepto                VARCHAR(200),
    fechaCreado             BIGINT,
    cuantia                 BIGINT,
    decimales               INT,
    divisa                  VARCHAR(3),
    remitente               INT,
    saldoRemitentePrevio    BIGINT,
    receptor                INT,
    FOREIGN KEY (remitente) REFERENCES cuentacorriente (id),
    FOREIGN KEY (receptor)  REFERENCES cuentacorriente (id)
);