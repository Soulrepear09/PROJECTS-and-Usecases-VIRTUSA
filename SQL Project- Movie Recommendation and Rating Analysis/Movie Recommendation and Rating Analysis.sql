CREATE DATABASE IF NOT EXISTS Chitralahari_db;

USE Chitralahari_db;

DROP TABLE IF EXISTS Watch_History;
DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Movies;

CREATE TABLE Users(
 user_id INT PRIMARY KEY AUTO_INCREMENT,
 user_name VARCHAR(50),
 age INT
);

INSERT INTO Users(user_name, age) VALUES
('Moksha', 25),
('Harsha Vardhan', 25),
('Hemanth Jupally', 26),
('Sai Manoj', 30),
('Priya', 28),
('Sagar', 33),
('Ramakrishna Reddy', 46),
('Meera Jasmine', 32),
('Nandhani', 25),
('Ashok', 33),
('Pooja', 27),
('Ankith Verma', 30),
('Samiksha', 26),
('Saketh', 29),
('Ananya', 35);

SELECT * FROM Users;

CREATE TABLE Movies(
movie_id INT PRIMARY KEY AUTO_INCREMENT,
title    VARCHAR(50),
genre    VARCHAR(20)
);

INSERT INTO Movies(title, genre) VALUES
('F1', 'Sports Drama'),
('Interstellar', 'Sci-Fi'),
('Salaar', 'Action'),
('Ee Nagaraniki Eamindi', 'Comedy'),
('Dune Part 1','Sci-Fi/Adventure'),
('Goodachari', 'Thriller'),
('The Paradise','Action'),
('Dhurandhar', 'Action'),
('Zodiac', 'Thriller'),
('Tare Zameen Par', 'Drama'),
('The Dark Knight', 'Sci-Fi'),
('Jaathi Rathnalu', 'Comedy'),
('Manjummel Boys', 'Adventure'),
('Fight Club', 'Crime'),
('MAD MAX: Fury Road', 'Distopian-Adventure');

SELECT * FROM Movies;

CREATE TABLE Ratings (
user_id INT,
movie_id INT,
rating Decimal(2,1),
FOREIGN KEY (user_id) REFERENCES Users(user_id),
FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);

INSERT INTO Ratings VALUES
(1, 1, 4.5),(1, 3, 4.0),(1,5,4.6),
(2, 3, 3.9),(2,4,4.0),(2, 6, 4.2),
(3, 7, 4.0),(3,3,3.5),(3,8,4.1),
(4, 9, 4.7),(4,7,4.1),(4,10,3.8),
(5, 2, 4.8),(5,9,4.1),(5,11,4.9),
(6, 6, 4.5),(6,12,2.5),(6,7,3.9),
(7, 5, 4.6),(7,1,4.5),(7,15,5.0),
(8, 4, 4.2),(8,14,4.0),(8,13,4.1),
(9, 10, 4.3),(9,5,4.8),(9,3,4.8),
(10, 8, 3.9),(10,1,4.2),(10,2,4.8),
(11, 11, 4.9),(11,15,4.2),(11,14,4.8),
(12, 12, 3.4),(12,3,4.0),(12,6,4.0),
(13, 13, 4.4),(13,1,4.5),(13,9,4.8),
(14, 14, 4.5),(14,11,5.0),(10,15,4.5),
(15, 15, 4.2),(15,14,4.8),(15,4,4.5);

SELECT * FROM Ratings;

CREATE TABLE Watch_History(
user_id  INT,
movie_id INT,
watch_date DATE,
FOREIGN KEY (user_id) REFERENCES Users(user_id),
FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);

INSERT INTO Watch_History VALUES
(1, 2, '2025-01-15'),(1, 3, '2025-02-12'),(1, 5, '2025-02-18'),
(2, 3, '2025-01-17'),(2, 4, '2025-01-19'),(1, 1, '2025-02-20'),
(3, 7, '2025-02-18'),(3, 8, '2025-03-12'),(1, 14, '2025-04-01'),
(4, 9, '2025-02-20'),(4, 8, '2025-03-25'),(4, 2, '2025-03-31'),
(5, 2, '2025-03-01'),(5, 10, '2025-03-22'),(5, 1, '2025-04-10'),
(6, 6, '2025-03-21'),(6, 15, '2025-04-02'),(6, 13, '2025-05-10'),
(7, 5, '2025-03-25'),(7, 6, '2025-04-12'),(7, 6, '2025-06-18'),
(8, 4, '2025-04-01'),(8, 2, '2025-05-30'),(8, 1, '2025-06-02'),
(9, 10, '2025-04-16'),(9, 15, '2025-05-01'),(9, 6, '2025-05-05'),
(10, 8, '2025-05-03'),(10, 10, '2025-05-04'),(10, 5, '2025-06-16'),
(11, 11, '2025-05-20'),(11, 15, '2025-05-22'),(11, 2, '2025-05-29'),
(12, 12, '2025-06-01'),(12, 15, '2025-06-12'),(12, 9, '2025-06-22'),
(13, 13, '2025-06-18'),(13, 7, '2025-06-19'),(13, 3, '2025-07-23'),
(14, 14, '2025-07-04'),(14, 9, '2025-07-15'),(1, 14, '2025-08-01'),
(15, 15, '2025-07-20'),(15, 13, '2025-08-05'),(15, 9, '2025-08-21');

SELECT * FROM Watch_History;


SELECT m.title AS "TOP 10 Rated Movies", ROUND(AVG(r.rating),1) AS avg_rating
FROM Movies m
JOIN Ratings r ON m.movie_id = r.movie_id
GROUP BY m.title
ORDER BY avg_rating DESC
LIMIT 10;

SELECT m.genre,  AVG(r.rating) AS avg_rating
FROM Movies m
JOIN Ratings r ON m.movie_id = r.movie_id
GROUP BY m.genre
ORDER BY avg_rating DESC;

SELECT m.genre AS "Popular Genre" , COUNT(*) AS total_watches
FROM Movies m
JOIN Watch_History wh ON m.movie_id = wh.movie_id
GROUP BY m.genre
ORDER BY total_watches DESC;

SELECT title FROM Movies
WHERE movie_id NOT IN(
	SELECT movie_id FROM Watch_History
    WHERE user_id=3
);

SELECT u.user_name, COUNT(*) AS movies_watched
FROM Users u
JOIN Watch_History wh ON u.user_id = wh.user_id
GROUP BY u.user_name
ORDER BY movies_watched DESC;

SELECT m.title AS "Movies Trending", COUNT(*) AS watch_count
FROM Movies m
JOIN Watch_History wh ON m.movie_id = wh.movie_id
WHERE wh.watch_date >= '2025-01-01'
GROUP BY m.title
ORDER BY watch_count DESC;