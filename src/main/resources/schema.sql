DROP TABLE IF EXISTS "usuario";
DROP TABLE IF EXISTS "imagen";

CREATE TABLE usuario (
                        id SERIAL PRIMARY KEY,
                        login VARCHAR(60) NOT NULL,
                        password VARCHAR(60) NOT NULL,
                        first_name VARCHAR(60) NOT NULL,
                        last_name VARCHAR(60) NOT NULL,
                        role SMALLINT NOT NULL,
                        email VARCHAR(60) NOT NULL
);



CREATE TABLE imagen (
                        id SERIAL PRIMARY KEY,
                        nombre VARCHAR(255) NOT NULL,
                        datos BYTEA NOT NULL
);
