DELIMITER $$
DROP PROCEDURE IF EXISTS init_script $$
CREATE PROCEDURE init_script()
BEGIN
	START TRANSACTION;


    CREATE TABLE IF NOT EXISTS bands (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        level SMALLINT UNSIGNED NOT NULL
    );

    
	CREATE TABLE IF NOT EXISTS job_roles (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        specification VARCHAR(255) NOT NULL,
        band_id SMALLINT UNSIGNED NOT NULL,
        FOREIGN KEY (band_id) REFERENCES bands(id)
    );

    
    CREATE TABLE IF NOT EXISTS users (
		username varchar(64) NOT NULL,
        password varchar(64) NOT NULL,
        job_role_id SMALLINT UNSIGNED NOT NULL,
        PRIMARY KEY (username),
        FOREIGN KEY (job_role_id) REFERENCES job_roles(id)
    );
    
    CREATE TABLE IF NOT EXISTS tokens (
		username varchar(64) NOT NULL,
        password varchar(64) NOT NULL,
        token varchar(64) NOT NULL,
        expiry DATETIME NOT NULL,
        FOREIGN KEY (username) REFERENCES users(username)
    );



	
	
END $$
DELIMITER ;
CALL init_script();			