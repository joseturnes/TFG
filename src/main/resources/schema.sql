DROP TABLE IF EXISTS "usuario";
DROP TABLE IF EXISTS "imagen";
DROP TABLE IF EXISTS "astro";
DROP TABLE IF EXISTS "related_image";
DROP TABLE IF EXISTS "post";

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    login VARCHAR(60) NOT NULL,
    password VARCHAR(60) NOT NULL,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    role SMALLINT NOT NULL,
    email VARCHAR(60) NOT NULL
);

CREATE TABLE astro (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    map_name VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE imagen (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    datos BYTEA NOT NULL
);

CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    description TEXT,
    name VARCHAR(255),
    post_type VARCHAR(40)
);

CREATE TABLE related_image (
   id SERIAL PRIMARY KEY,
   post_id SMALLINT,
   image BYTEA,
   FOREIGN KEY (post_id) REFERENCES post(id)
);
