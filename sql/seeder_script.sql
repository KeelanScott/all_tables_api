DELIMITER $$
DROP PROCEDURE IF EXISTS seeder_script $$
CREATE PROCEDURE seeder_script()
BEGIN
	START TRANSACTION;
    
    INSERT INTO bands (name, level) VALUES ('Band 1', 1);
    INSERT INTO bands (name, level) VALUES ('Band 2', 2);
    INSERT INTO bands (name, level) VALUES ('Band 3', 3);

    INSERT INTO job_roles (name, specification, band_id) VALUES ('Trainee Software Engineer', 'Develops software', 1);
    INSERT INTO job_roles (name, specification, band_id) VALUES ('Senior Software Engineer', 'Develops software', 2);
    INSERT INTO job_roles (name, specification, band_id) VALUES ('Lead Software Engineer', 'Develops software', 3);
    
    INSERT INTO users (email, password, job_role_id, is_admin) VALUES ('keelan@gmail.com', 'Scott', 1, 0);
    INSERT INTO users (email, password, job_role_id, is_admin) VALUES ('joshua@gmail.com', 'Young', 1, 0);
    INSERT INTO users (email, password, job_role_id, is_admin) VALUES ('jimmys@gmail.com', 'Edgar', 1, 0);
    INSERT INTO users (email, password, job_role_id, is_admin) VALUES ('darragh@gmail.com', 'Melarkey', 1, 0);
    INSERT INTO users (email, password, job_role_id, is_admin) VALUES ('james@gmail.com', 'McKee', 1, 0);
    INSERT INTO users (email, password, job_role_id, is_admin) VALUES ('shaun@gmail.com', 'Ganley', 3, 1);
    

-- check the number of affected rows
	GET DIAGNOSTICS @rows = ROW_COUNT;
	IF @rows = 0 THEN
		-- Rollback if error
		ROLLBACK;
		SELECT 'Transaction rolled back due to an error.';
	ELSE
		-- If error free
		COMMIT;
		SELECT 'Transaction committed successfully.';
	END IF; 
	
END $$
DELIMITER ;
CALL seeder_script();		