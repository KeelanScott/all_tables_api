DELIMITER $$
DROP PROCEDURE IF EXISTS seeder_script $$
CREATE PROCEDURE seeder_script()
BEGIN
	START TRANSACTION;
    


    INSERT INTO bands (name, level) VALUES ('Band 1', 1);
    INSERT INTO bands (name, level) VALUES ('Band 2', 2);
    INSERT INTO bands (name, level) VALUES ('Band 3', 3);

    INSERT INTO job_roles (name, specification, band) VALUES ('Trainee Software Engineer', 'Develops software', 1);
    INSERT INTO job_roles (name, specification, band) VALUES ('Senior Software Engineer', 'Develops software', 2);
    INSERT INTO job_roles (name, specification, band) VALUES ('Lead Software Engineer', 'Develops software', 3);

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