CREATE DATABASE BD_SUPERMERCADO;
USE BD_SUPERMERCADO;

CREATE TABLE VENDEDOR(
ID_VENDEDOR INT PRIMARY KEY NOT NULL,
NOMBRE_VENDEDOR VARCHAR(50) NOT NULL,
APELIDO_VENDEDOR VARCHAR(50) NOT NULL,
CEDULA_VENDEDOR BIGINT NOT NULL,
ID_SUPERMERCADO INT NOT NULL);

CREATE TABLE SUPERMERCADO(
ID_SUPERMERCADO INT PRIMARY KEY NOT NULL,
NOMBRE_SUPERMERCADO VARCHAR(50) NOT NULL,
DIRECCION_SUPERMERCADO VARCHAR(70) NOT NULL,
ID_CIUDAD_SUPERMERCADO INT NOT NULL);

CREATE TABLE CIUDAD_SUPERMERCADO(
ID_CIUDAD_SUPERMERCADO INT PRIMARY KEY NOT NULL,
CIUDAD VARCHAR(50) NOT NULL);

CREATE TABLE FACTURA(
NUMERO_FACTURA INT PRIMARY KEY NOT NULL,
ID_PRODUCTO INT NOT NULL,
ID_VENDEDOR INT NOT NULL,
ID_CLIENTE INT NOT NULL,
CANTIDAD INT NOT NULL,
VALOR_VENTA BIGINT NOT NULL,
FECHA_VENTE DATE NOT NULL);

CREATE TABLE CLIENTE(
ID_CLIENTE INT PRIMARY KEY NOT NULL,
NOMBRE_CLIENTE VARCHAR(50) NOT NULL,
APELLIDO_CLIENTE VARCHAR(50) NOT NULL,
CEDULA_CLIENTE BIGINT NOT NULL,
TELEFONO_CLIENTE BIGINT NOT NULL,
DIRECCION VARCHAR(70) NOT NULL);

CREATE TABLE PRODUCTO(
ID_PRODUCTO INT PRIMARY KEY NOT NULL,
ID_TIPO_PRODUCTO INT NOT NULL,
DESCRIPCION_PRODUCTO VARCHAR(200) NOT NULL,
VALOR_VENTA_PRODUCTO BIGINT NOT NULL,
VALOR_COMPRA_PRODUCTO BIGINT NOT NULL);

CREATE TABLE TIPO_PRODUCTO(
ID_TIPO_PRODUCTO INT PRIMARY KEY NOT NULL,
DESCRIPCION_TIPO VARCHAR(200) NOT NULL);

CREATE TABLE INVENTARIO(
ID_PRODUCTO INT NOT NULL,
CANTIDAD INT NOT NULL);

/*RELACIONES*/

ALTER TABLE VENDEDOR ADD FOREIGN KEY (ID_SUPERMERCADO) REFERENCES SUPERMERCADO(ID_SUPERMERCADO);
ALTER TABLE FACTURA ADD FOREIGN KEY (ID_VENDEDOR) REFERENCES VENDEDOR(ID_VENDEDOR);
ALTER TABLE FACTURA ADD FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE);
ALTER TABLE FACTURA ADD FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO); 
ALTER TABLE PRODUCTO ADD FOREIGN KEY (ID_TIPO_PRODUCTO) REFERENCES TIPO_PRODUCTO(ID_TIPO_PRODUCTO);
ALTER TABLE INVENTARIO ADD FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO);
ALTER TABLE SUPERMERCADO ADD FOREIGN KEY (ID_CIUDAD_SUPERMERCADO) REFERENCES CIUDAD_SUPERMERCADO(ID_CIUDAD_SUPERMERCADO);

--CONSULTAS SQL--

--CONSULTA #1

SELECT 
    S.NOMBRE_SUPERMERCADO, 
    SUM(F.CANTIDAD) AS CANTIDAD_TOTAL_VENDIDA,
    P.DESCRIPCION_PRODUCTO,
    F.FECHA_VENTE
FROM 
    FACTURA F
JOIN 
    PRODUCTO P ON F.ID_PRODUCTO = P.ID_PRODUCTO
JOIN 
    TIPO_PRODUCTO TP ON P.ID_TIPO_PRODUCTO = TP.ID_TIPO_PRODUCTO
JOIN 
    SUPERMERCADO S ON F.ID_VENDEDOR = S.ID_SUPERMERCADO
WHERE 
    TP.DESCRIPCION_TIPO = 'CARNICOS' 
    AND F.FECHA_VENTE <= '2013-12-31'
GROUP BY 
    S.NOMBRE_SUPERMERCADO, P.DESCRIPCION_PRODUCTO, F.FECHA_VENTE
ORDER BY 
    CANTIDAD_TOTAL_VENDIDA DESC
LIMIT 1;


--CONSULTA #2

SELECT 
    C.ID_CLIENTE,
    C.NOMBRE_CLIENTE,
    C.APELLIDO_CLIENTE,
    C.CEDULA_CLIENTE,
    C.TELEFONO_CLIENTE,
    C.DIRECCION,
    S.NOMBRE_SUPERMERCADO,
    F.VALOR_VENTA
FROM 
    FACTURA F
JOIN 
    CLIENTE C ON F.ID_CLIENTE = C.ID_CLIENTE
JOIN 
    VENDEDOR V ON F.ID_VENDEDOR = V.ID_VENDEDOR
JOIN 
    SUPERMERCADO S ON V.ID_SUPERMERCADO = S.ID_SUPERMERCADO
WHERE 
    S.NOMBRE_SUPERMERCADO = 'Exito' 
    AND F.VALOR_VENTA > 150000
ORDER BY 
    F.VALOR_VENTA DESC;