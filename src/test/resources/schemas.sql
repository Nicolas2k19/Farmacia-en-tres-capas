CREATE TABLE IF NOT EXISTS FARMACIA (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200),
    localidad VARCHAR(200),
    cuil VARCHAR(200),
    calle VARCHAR(200),
    nrocalle VARCHAR(5)
);

CREATE TABLE IF NOT EXISTS PRODUCTO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmacia_id BIGINT,
    nombre VARCHAR(200),
    precio DECIMAL(10,4),
    CONSTRAINT fk_farmacia FOREIGN KEY (farmacia_id) REFERENCES FARMACIA(id)
);





INSERT INTO FARMACIA (nombre, localidad, cuil, calle, nrocalle)
VALUES ('NUEVA FARMACIA', 'San Miguel', '2042370424', 'Zuviria', '2168');