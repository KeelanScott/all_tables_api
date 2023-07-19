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

    CREATE TABLE IF NOT EXISTS levels (
        id TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(20) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS competencies (
        id TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS competency_elements (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        competency_id TINYINT UNSIGNED NOT NULL,
        name VARCHAR(100) NOT NULL,
        FOREIGN KEY (competency_id) REFERENCES competencies(id)
    );

    CREATE TABLE IF NOT EXISTS bands_competency_elements (
        level_id TINYINT UNSIGNED NOT NULL,
        competency_element_id SMALLINT UNSIGNED NOT NULL,
        description VARCHAR(255) NOT NULL,
        FOREIGN KEY (level_id) REFERENCES levels(id),
        FOREIGN KEY (competency_element_id) REFERENCES competency_elements(id)
    );

	
	
END $$
DELIMITER ;
CALL init_script();			