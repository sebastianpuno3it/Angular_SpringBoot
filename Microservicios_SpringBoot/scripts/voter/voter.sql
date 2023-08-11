CREATE TABLE `voter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `option` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insertando registros en la tabla "voter"
INSERT INTO voter (email, option) VALUES ('math@survey.com', 'Rock');
INSERT INTO voter (email, option) VALUES ('spuno.c@gmail.com', 'Jazz');

