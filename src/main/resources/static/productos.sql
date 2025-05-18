CREATE TABLE Productos (
    id INT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion TEXT,
    stock INT,
    precio FLOAT,
    id_categoria INT,
    id_proveedor INT
);
