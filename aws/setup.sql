# setup table
CREATE DATABASE ares;

CREATE TABLE coordinates(id INT NOT NULL AUTO_INCREMENT, d1 INT NOT NULL, d2 INT NOT NULL, d3 INT NOT NULL, isPainting BOOLEAN NOT NULL, PRIMARY KEY (id) ) ENGINE=MyISAM;

# access table
USE ares;

# insert test coordinates
INSERT INTO coordinates (d1, d2, d3, isPainting) VALUES (100, 100, 190, true);

# show coordinates
SELECT * FROM coordinates;