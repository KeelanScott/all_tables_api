DELIMITER $$
DROP PROCEDURE IF EXISTS seeder_script $$
CREATE PROCEDURE seeder_script()
BEGIN
	START TRANSACTION;

    INSERT INTO bands (name, level) VALUES ("Apprentice", "Beginner");
    INSERT INTO bands (name, level) VALUES ("Trainee", "Basic");
    INSERT INTO bands (name, level) VALUES ("Associate", "Foundational");

    INSERT INTO competencies (name) VALUES ("Personal Performance");
    INSERT INTO competencies (name) VALUES ("Working with Others");
    INSERT INTO competencies (name) VALUES ("Setting Direction, Development and Accountability");
    INSERT INTO competencies (name) VALUES ("Supporting and Delivering the Strategy");
    INSERT INTO competencies (name) VALUES ("Commerciality and Risk");
    INSERT INTO competencies (name) VALUES ("Communicating and Influence");

    INSERT INTO bands_competencies VALUES (1, 1, "Understands own strengths and areas of development. Self-aware of own well-being and seeks support where appropriate.");
    INSERT INTO bands_competencies VALUES (1, 2, "Recognises the need to build internal networks within immediate teams and projects.");
    INSERT INTO bands_competencies VALUES (1, 3, "Understands role, tasks and deadlines and work towards these, escalating any issues where appropriate to People/Project Manager.");
    INSERT INTO bands_competencies VALUES (1, 4, "Understands the Kainos Vision, Strategy and Principles.");
    INSERT INTO bands_competencies VALUES (1, 5, "Understands the markets and sectors in which Kainos operates while acknowledging how role links in and has an impact on other teams and the business.");
    INSERT INTO bands_competencies VALUES (1, 6, "Acts in a respectful manner in all forms of communication while being open and honest. Displays a positive approach when interacting with others.");

    INSERT INTO bands_competencies VALUES (2, 1, "Understands others strengths and areas for development. Recognising diversity and its value within self and team. Proactively uses well-being tools to support self-regulation.");
    INSERT INTO bands_competencies VALUES (2, 2, "Builds strong working relationships within team and project teams and start to consider building a wider network.");
    INSERT INTO bands_competencies VALUES (2, 3, "Plans time effectively to ensure deadlines are met, and seen to be honest, escalating in advance any issues with completing tasks within the specified time.");
    INSERT INTO bands_competencies VALUES (2, 4, "Recognises how to contribute to the wider organisational objectives and strategic principles.");
    INSERT INTO bands_competencies VALUES (2, 5, "Understands how the business generates income. Supporting the business by assisting in activities such as Recruitment events, Career fairs and Work experience mentoring. Keeps up to date with current trends relating to work.");
    INSERT INTO bands_competencies VALUES (2, 6, "Communicates own views in a clear and constructive manner, while listening to different views and considers all employees from various backgrounds.");

    INSERT INTO bands_competencies VALUES (3, 1, "Seeks out new challenges and opportunities that may stretch current abilities. Builds on own selfawareness of overall wellbeing.");
    INSERT INTO bands_competencies VALUES (3, 2, "Consistently collaborates within immediate teams and finds opportunities to build rapport and trust while supporting others. Proactively contribute to the work of the whole team whilst building positive colleague relationships ");
    INSERT INTO bands_competencies VALUES (3, 3, "Recognises how much time is required for different tasks and start to prioritise and communicate effectively within teams. Seeking appropriate support while supporting peers and junior team members.");
    INSERT INTO bands_competencies VALUES (3, 4, "Articulates individual contribution to the wider Kainos objectives and uses evidence by including SMART goals that align to the Capability/BU.");
    INSERT INTO bands_competencies VALUES (3, 5, "Comprehends the need for the business to generate additional income and respects that costs need to be managed. Recognises the contribution that role makes to the success of the business, consistently delivering to task deadlines.");
    INSERT INTO bands_competencies VALUES (3, 6, "Involved at meetings, asking questions, listening and develops and presents a well-reasoned point of view. Remaining communicative and clear in own thoughts and ideas when approached by others, giving consideration to the communication needs of other staff and customers.");

    INSERT INTO job_roles (name, specification, band_id) VALUES ('Trainee Software Engineer', 'Develops software', 1);
    INSERT INTO job_roles (name, specification, band_id) VALUES ('Senior Software Engineer', 'Develops software', 2);
    INSERT INTO job_roles (name, specification, band_id) VALUES ('Lead Software Engineer', 'Develops software', 3);

    INSERT INTO training_courses (name, description) VALUES ('Java', 'Java training');
    INSERT INTO training_courses (name, description) VALUES ('C#', 'C# training');
    INSERT INTO training_courses (name, description) VALUES ('Python', 'Python training');
    INSERT INTO training_courses (name, description) VALUES ('Leadership', 'Leadership training');
    INSERT INTO training_courses (name, description) VALUES ('Management', 'Management training');
    INSERT INTO training_courses (name, description) VALUES ('Communication', 'Communication training');
    INSERT INTO training_courses (name, description) VALUES ('Presentation', 'Presentation training');
    INSERT INTO training_courses (name, description) VALUES ('Time Management', 'Time Management training');
    INSERT INTO training_courses (name, description) VALUES ('Problem Solving', 'Problem Solving training');
    INSERT INTO training_courses (name, description) VALUES ('Teamwork', 'Teamwork training');


    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (1, 1);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (1, 2);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (1, 3);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (1, 4);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (2, 1);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (2, 2);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (2, 3);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (3, 4);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (3, 5);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (3, 6);
    INSERT INTO band_training_courses (band_id, training_course_id) VALUES (3, 7);


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