CREATE DATABASE rkis;

\connect rkis

CREATE TABLE Dishes (
    id       SERIAL PRIMARY KEY,
    name     VARCHAR,
    color    VARCHAR,
    material VARCHAR,
    width   NUMERIC,
    depth    NUMERIC,
    price    NUMERIC,
    quantity INTEGER
);