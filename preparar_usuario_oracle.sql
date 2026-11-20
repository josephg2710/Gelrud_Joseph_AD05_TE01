-- AD05 - Preparacion de un usuario de practicas (Oracle XE 21c)
-- Ejecutar UNA VEZ en SQL Developer, conectado a XEPDB1 con cuenta administradora.
-- No ejecutar en CDB$ROOT. Si AD05_APP ya existe, no repetir CREATE USER.
-- Sustituir CAMBIA_ESTA_CLAVE antes de ejecutar. No publicar claves reales.
CREATE USER AD05_APP IDENTIFIED BY "CAMBIA_ESTA_CLAVE"
  DEFAULT TABLESPACE USERS TEMPORARY TABLESPACE TEMP
  QUOTA 50M ON USERS;
GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE TO AD05_APP;
-- Hibernate crea las tablas desde las entidades.
