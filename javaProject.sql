DROP TABLE movieInventory;
DROP TABLE gameInventory;
/
CREATE TABLE movieInventory(
  movieId CHAR(1) PRIMARY KEY NOT NULL,
  price NUMBER (6,2),
  title VARCHAR2(50),
  genre VARCHAR2(50),
  serialNumber VARCHAR2(50),
  movieDuration NUMBER(7,3),
  isAvailable NUMBER(1),
  discount NUMBER(4,2),
  quantity Number (2),
  director VARCHAR2(50),
  studio VARCHAR2(50)
);
/
CREATE TABLE gameInventory(
  movieId CHAR(1) PRIMARY KEY NOT NULL,
  price NUMBER (6,2),
  title VARCHAR2(50),
  genre VARCHAR2(50),
  serialNumber VARCHAR2(50),
  movieDuration NUMBER(7,3),
  isAvailable NUMBER(1),
  discount NUMBER(4,2),
  quantity Number (2),
  platform VARCHAR2(50),
  publisher VARCHAR2(50)
);
/
INSERT INTO movieInventory (movieId, price, title, genre, serialNumber, movieDuration, isAvailable, discount, quantity, director, studio)
VALUES (1, 20.99, 'One piece', 'ADVENTURE', '220001',3000, 1, 2.99, 10, 'Konosuke Uda', 'Toei Animation');

INSERT INTO movieInventory (movieId, price, title, genre, serialNumber, movieDuration, isAvailable, discount, quantity, director, studio)
VALUES (2, 14.99, 'Titanic', 'ROMANCE', '220002', 240, 0, 0, 12, 'James Cameron', 'Paramount Pictures 20th Century Studios');

INSERT INTO movieInventory (movieId, price, title, genre, serialNumber, movieDuration, isAvailable, discount, quantity, director, studio)
VALUES (3, 10, 'Cars', 'ANIMATION', '220003',172, 1,1, 3,'John Lasseter', 'Pixar Animation Studios');

CREATE OR REPLACE PACKAGE javaFinalProject AS 
    PROCEDURE GetMovies (movie_cursor OUT SYS_REFCURSOR);
    PROCEDURE GetGames (game_cursor OUT SYS_REFCURSOR);
  
End javaFinalProject;
/
CREATE OR REPLACE PACKAGE BODY javaFinalProject AS
    PROCEDURE GetMovies (movie_cursor OUT SYS_REFCURSOR) AS
        BEGIN
            OPEN movie_cursor FOR
            SELECT * FROM movieInventory;
        END GetMovies;
        
    PROCEDURE GetGames (game_cursor OUT SYS_REFCURSOR) AS
        BEGIN
            OPEN game_cursor FOR
            SELECT * FROM gameInventory;
        END GetGames;
END javaFinalProject;
/
