DROP DATABASE bancaonline;
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
    entidad         INT,
    oficina         INT,
    id              INT PRIMARY KEY AUTO_INCREMENT,
    saldo           BIGINT,
    decimales       INT,
    divisa          VARCHAR(3),
    fechacreacion   BIGINT,
    CONSTRAINT UC_CC UNIQUE (entidad, oficina, id)
);


CREATE TABLE cliente (
    dni         INT PRIMARY KEY,
    password    VARCHAR(100),
    nombre      VARCHAR(30),
    apellidos   VARCHAR(100),
    cuenta      INT,
    FOREIGN KEY (cuenta) REFERENCES cuentacorriente (id)
);


CREATE TABLE movimiento(
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    remitente               INT,
    receptor                INT,
    concepto                VARCHAR(200),
    fecha                   BIGINT,
    cuantia                 BIGINT,
    decimales               INT,
    divisa                  VARCHAR(3),
    saldoRemitentePrevio    BIGINT,
    saldoReceptorPrevio     BIGINT,
    FOREIGN KEY (remitente) REFERENCES cuentacorriente (id),
    FOREIGN KEY (receptor)  REFERENCES cuentacorriente (id)
);
