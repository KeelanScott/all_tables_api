DELIMITER $$
DROP PROCEDURE IF EXISTS init_script $$
CREATE PROCEDURE init_script()
BEGIN
	START TRANSACTION;


    DROP TABLE IF EXISTS bands;
    DROP TABLE IF EXISTS job_roles;
    DROP TABLE IF EXISTS capabilities;

    CREATE TABLE IF NOT EXISTS bands (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        level SMALLINT UNSIGNED NOT NULL
    );

    CREATE TABLE IF NOT EXISTS capabilities (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        description VARCHAR(255) NOT NULL
    );

	CREATE TABLE IF NOT EXISTS job_roles (
        id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        specification VARCHAR(255) NOT NULL,
        band_id SMALLINT UNSIGNED NOT NULL,
        capability_id SMALLINT UNSIGNED NOT NULL,
        FOREIGN KEY (band_id) REFERENCES bands(id),
        FOREIGN KEY (capability_id) REFERENCES capabilities(id)
    );
    
    CREATE TABLE IF NOT EXISTS users (
		email VARCHAR(64) NOT NULL,
        password VARCHAR(64) NOT NULL,
        is_admin BOOLEAN NOT NULL,
        PRIMARY KEY (email)
    );
    
    CREATE TABLE IF NOT EXISTS tokens (
		email VARCHAR(64) NOT NULL,
        token VARCHAR(64) NOT NULL,
        expiry DATETIME NOT NULL,
        FOREIGN KEY (email) REFERENCES users(email)
    );
	
END $$
DELIMITER ;
CALL init_script();			