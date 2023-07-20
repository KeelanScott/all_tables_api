DELIMITER $$
DROP PROCEDURE IF EXISTS init_script $$
CREATE PROCEDURE init_script()
BEGIN
	START TRANSACTION;


    CREATE TABLE IF NOT EXISTS bands (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        level SMALLINT UNSIGNED NOT NULL,
        responsibilities VARCHAR(255)
    );

    
	CREATE TABLE IF NOT EXISTS job_roles (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        specification VARCHAR(255) NOT NULL,
        band_id SMALLINT UNSIGNED NOT NULL,
        FOREIGN KEY (band_id) REFERENCES bands(id)
    );

    CREATE TABLE IF NOT EXISTS competencies (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS bands_competencies (
        band_id SMALLINT UNSIGNED NOT NULL,
        competency_id SMALLINT UNSIGNED NOT NULL,
        description VARCHAR(512) NOT NULL,
        PRIMARY KEY (band_id, competency_id),
        FOREIGN KEY (band_id) REFERENCES bands(id),
        FOREIGN KEY (competency_id) REFERENCES competencies(id)
    );

    CREATE TABLE IF NOT EXISTS training_courses (
       id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(50) NOT NULL,
       description VARCHAR(255) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS band_training_courses (
         band_id SMALLINT UNSIGNED NOT NULL,
         training_course_id SMALLINT UNSIGNED NOT NULL,
         FOREIGN KEY (band_id) REFERENCES bands(id),
         FOREIGN KEY (training_course_id) REFERENCES training_courses(id)
    );

	
	
END $$
DELIMITER ;
CALL init_script();			