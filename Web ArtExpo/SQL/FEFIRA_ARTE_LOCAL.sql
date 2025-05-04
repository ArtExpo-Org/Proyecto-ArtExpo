-- Crear la base de datos si no existe
CREATE DATABASE EXPO_DB
go
USE EXPO_DB
go

CREATE TABLE Usuario (
    usuario_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('ARTISTA', 'VISITANTE', 'ORGANIZADOR') NOT NULL
);

CREATE TABLE Artista (
    artista_id INT PRIMARY KEY,
    biografia TEXT NOT NULL,
    FOREIGN KEY (artista_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Visitante (
    visitante_id INT PRIMARY KEY,
    tipo_entrada ENUM('GENERAL', 'VIP', 'FAMILIAR', 'GRUPO') NULL,
    precio_ticket DECIMAL(6,2) NULL,
    FOREIGN KEY (visitante_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Organizador (
    organizador_id INT PRIMARY KEY,
    rol VARCHAR(50) NOT NULL,
    FOREIGN KEY (organizador_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Feria (
    feria_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    responsable_id INT NULL,
    FOREIGN KEY (responsable_id) REFERENCES Organizador(organizador_id)
);

CREATE TABLE Stand (
    stand_id INT PRIMARY KEY AUTO_INCREMENT,
    numero INT NOT NULL,
    estado ENUM('DISPONIBLE', 'OCUPADO') DEFAULT 'DISPONIBLE',
    feria_id INT NOT NULL,
    artista_id INT NULL,
    FOREIGN KEY (feria_id) REFERENCES Feria(feria_id),
    FOREIGN KEY (artista_id) REFERENCES Artista(artista_id),
    UNIQUE KEY (feria_id, numero)
);

CREATE TABLE Obra (
    obra_id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    anio_creacion INT NOT NULL,
    tecnica VARCHAR(100) NOT NULL,
    precio DECIMAL(6,2) NOT NULL,
    artista_id INT NOT NULL,
    FOREIGN KEY (artista_id) REFERENCES Artista(artista_id)
);

CREATE TABLE Venta (
    venta_id INT PRIMARY KEY AUTO_INCREMENT,
    fecha_venta DATE NOT NULL,
    obra_id INT NOT NULL,
    comprador_id INT NOT NULL,
    FOREIGN KEY (obra_id) REFERENCES Obra(obra_id),
    FOREIGN KEY (comprador_id) REFERENCES Visitante(visitante_id)
);

CREATE TABLE Evento (
    evento_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    tipo_evento ENUM('CHARLA', 'TALLER', 'DEMOSTRACION', 'NETWORKING', 'SUBASTA', 'FIRMAS') NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    feria_id INT NOT NULL,
    FOREIGN KEY (feria_id) REFERENCES Feria(feria_id)
);

-- Tablas de relación
CREATE TABLE Evento_Participante (
    evento_id INT,
    usuario_id INT,
    PRIMARY KEY (evento_id, usuario_id),
    FOREIGN KEY (evento_id) REFERENCES Evento(evento_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id)
);

CREATE TABLE Asistencia_Feria (
    visitante_id INT,
    feria_id INT,
    PRIMARY KEY (visitante_id, feria_id),
    FOREIGN KEY (visitante_id) REFERENCES Visitante(visitante_id),
    FOREIGN KEY (feria_id) REFERENCES Feria(feria_id)
);

CREATE TABLE Organizador_Feria (
    organizador_id INT,
    feria_id INT,
    PRIMARY KEY (organizador_id, feria_id),
    FOREIGN KEY (organizador_id) REFERENCES Organizador(organizador_id),
    FOREIGN KEY (feria_id) REFERENCES Feria(feria_id)
);