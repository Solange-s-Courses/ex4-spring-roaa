-- Insert department
INSERT INTO department (name, total_credits, description)
VALUES ('BSc Computer Science', 140, 'Complete 140 credits to graduate.');

-- Insert lecturer user
INSERT INTO users (username, password, full_name, role)
VALUES ('lecturer', '$2a$10$wNae3Rt8kQ1ftG6vaCT1JuTw9MKMrF6zuDsaQrQLsa4mBESuJSeQG', 'Dr. Smith', 'LECTURER');

-- Insert student user
INSERT INTO users (username, password, full_name, role)
VALUES ('student', '$2a$10$CTZVGiQPJ6G8xIzkSqlqKeWojdIVL25AO8xCNMu.r1qhS3aUq4zGa', 'John Student', 'STUDENT'),
       ('Markme', '$2a$10$CTZVGiQPJ6G8xIzkSqlqKeWojdIVL25AO8xCNMu.r1qhS3aUq4zGa', 'Mark Mealy', 'STUDENT');

-- Insert course
-- נניח ש־department_id = 1
INSERT INTO course (title, credits, syllabus, mandatory, department_id)
VALUES ('Intro to Programming', 5, 'Basics of Java', true, 1),
       ('Algorithms', 4, 'Design and analysis of algorithms', true, 1),
       ('Operating Systems', 4, 'Processes, memory, and files', true, 1);

-- Insert course instance
-- נניח ש־course_id = 1 ו־lecturer_id = 2
INSERT INTO course_instance (year, semester, capacity, course_id, lecturer_id)
VALUES (1, 1, 30, 1, 2),
       (1, 1, 40, 2, 2),
       (2, 2, 1, 3, 2);

-- Insert enrollment
-- נניח ש־student_id = 3 ו־course_instance_id = 1
INSERT INTO enrollment (student_id, course_instance_id, enrollment_date)
VALUES (3, 1, CURRENT_DATE);
