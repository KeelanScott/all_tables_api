DELIMITER $$
DROP PROCEDURE IF EXISTS seeder_script $$
CREATE PROCEDURE seeder_script()
BEGIN
	START TRANSACTION;
    


    INSERT INTO bands (name, level) VALUES ('Band 1', 1);
    INSERT INTO bands (name, level) VALUES ('Band 2', 2);
    INSERT INTO bands (name, level) VALUES ('Band 3', 3);

    INSERT INTO levels (name) VALUES ("Beginner");
    INSERT INTO levels (name) VALUES ("Basic");
    INSERT INTO levels (name) VALUES ("Foundational");
    INSERT INTO levels (name) VALUES ("Developing");
    INSERT INTO levels (name) VALUES ("Intermediate");
    INSERT INTO levels (name) VALUES ("Effective");
    INSERT INTO levels (name) VALUES ("Emerging");
    INSERT INTO levels (name) VALUES ("Proficient");
    INSERT INTO levels (name) VALUES ("Advanced");
    INSERT INTO levels (name) VALUES ("Expert");

    INSERT INTO competencies (name) VALUES ("Personal Performance");
    INSERT INTO competency_elements (competency_id, name) VALUES (1, "Developing self-awareness");
    INSERT INTO bands_competency_elements VALUES (1, 1, "Understands own strengths and areas of development. Self-aware of own well-being and seeks support where appropriate.");
    INSERT INTO competency_elements (competency_id, name) VALUES (1, "Managing yourself");
    INSERT INTO bands_competency_elements VALUES (1, 2, "Works with People Manager to sets and achieve goals by monitoring progress regularly against performance.");
    INSERT INTO competency_elements (competency_id, name) VALUES (1, "Continuing personal development");
    INSERT INTO bands_competency_elements VALUES (1, 3, "Flexible and willingness to learn on the job while proactively keeping up to date with the knowledge and skills needed.");
    INSERT INTO competency_elements (competency_id, name) VALUES (1, "Acting with integrity");
    INSERT INTO bands_competency_elements VALUES (1, 4, "Understands the company values and applies this to own principles. Is open and honest and acts respectfully with others and customers.");


    INSERT INTO competencies (name) VALUES ("Working with Others");
    INSERT INTO competency_elements (competency_id, name) VALUES (2, "Mobilises self and others to drive self-improvement");
    INSERT INTO bands_competency_elements VALUES (1, 5, "Understand how to respond constructively to developmental feedback from a diverse range of people and implement changes as a result.");
    INSERT INTO competency_elements (competency_id, name) VALUES (2, "Champions continuous improvement");
    INSERT INTO bands_competency_elements VALUES (1, 6, "Displays high levels of enthusiasm, energy and pace to achieve performance and results.");
    INSERT INTO competency_elements (competency_id, name) VALUES (2, "Developing networks and building and maintaining relationships");
    INSERT INTO bands_competency_elements VALUES (1, 7, "Recognises the need to build internal networks within immediate teams and projects.");
    INSERT INTO competency_elements (competency_id, name) VALUES (2, "Working within teams");
    INSERT INTO bands_competency_elements VALUES (1, 8, "Respects others by attending meetings on time and contributing where appropriate. Recognising how current role relates to others within Capability and project.");

    INSERT INTO competencies (name) VALUES ("Setting Direction, Development and Accountability");
    INSERT INTO competency_elements (competency_id, name) VALUES (3, "Effective time management");
    INSERT INTO bands_competency_elements VALUES (1, 9, "Understands role, tasks and deadlines and work towards these, escalating any issues where appropriate to People/Project Manager.");
    INSERT INTO competency_elements (competency_id, name) VALUES (3, "Promoting accountability");
    INSERT INTO bands_competency_elements VALUES (1, 10, "Accepts personal responsibility for quality and timelines of work set.");
    INSERT INTO competency_elements (competency_id, name) VALUES (3, "Effective meetings");
    INSERT INTO bands_competency_elements VALUES (1, 11, "Works to manage diary, commitments and ready to attend meetings on time.");
    INSERT INTO competency_elements (competency_id, name) VALUES (3, "Problem solving");
    INSERT INTO bands_competency_elements VALUES (1, 12, "Actively supports new initiatives and tries different ways of doing things, learning from others’ experiences.");

    INSERT INTO competencies (name) VALUES ("Supporting and Delivering the Strategy");
    INSERT INTO competency_elements (competency_id, name) VALUES (4, "Supporting the strategy and vision");
    INSERT INTO bands_competency_elements VALUES (1, 13, "Understands the Kainos Vision, Strategy and Principles.");
    INSERT INTO competency_elements (competency_id, name) VALUES (4, "Organisational awareness");
    INSERT INTO bands_competency_elements VALUES (1, 14, "Understands the organisation structure and the purpose of Kainos.");
    INSERT INTO competency_elements (competency_id, name) VALUES (4, "Cultural and ethical awareness");
    INSERT INTO bands_competency_elements VALUES (1, 15, "Possess general knowledge of local cultural differences and familiar with the Kainos policy towards Diversity and Inclusion.");

    INSERT INTO competencies (name) VALUES ("Commerciality and Risk");
    INSERT INTO competency_elements (competency_id, name) VALUES (5, "Demonstrates commercial awareness and behaviours");
    INSERT INTO bands_competency_elements VALUES (1, 16, "Understands the markets and sectors in which Kainos operates while acknowledging how role links in and has an impact on other teams and the business.");
    INSERT INTO competency_elements (competency_id, name) VALUES (5, "Improves efficiency and effectiveness to drive profitability");
    INSERT INTO bands_competency_elements VALUES (1, 17, "Wiling to learn, and embrace new ideas, to improve processes and procedures.");
    INSERT INTO competency_elements (competency_id, name) VALUES (5, "Promotes and adheres to Kainos process and policies");
    INSERT INTO bands_competency_elements VALUES (1, 18, "Cooperates with business processes completing accurately and honestly e.g., timesheets/EOY Review and travel requests.");

    INSERT INTO competencies (name) VALUES ("Communicating and Influence");
    INSERT INTO competency_elements (competency_id, name) VALUES (6, "Communicates effectively");
    INSERT INTO bands_competency_elements VALUES (1, 19, "Acts in a respectful manner in all forms of communication while being open and honest. Displays a positive approach when interacting with others.");
    INSERT INTO competency_elements (competency_id, name) VALUES (6, "Influencing others");
    INSERT INTO bands_competency_elements VALUES (1, 20, "Recognises influencing as a key skill required to work effectively with internal and external customers.");
    INSERT INTO competency_elements (competency_id, name) VALUES (6, "Customer Focus and Stakeholder management");
    INSERT INTO bands_competency_elements VALUES (1, 21, "Acts in accordance with the Kainos values demonstrating through own behaviours and interactions with colleagues and customers.");

    INSERT INTO job_roles (name, specification, band_id) VALUES ('Trainee Software Engineer', 'Develops software', 1);
    INSERT INTO job_roles (name, specification, band_id) VALUES ('Senior Software Engineer', 'Develops software', 2);
    INSERT INTO job_roles (name, specification, band_id) VALUES ('Lead Software Engineer', 'Develops software', 3);

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