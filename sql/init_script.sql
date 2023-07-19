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


	
	
END $$
DELIMITER ;
CALL init_script();			