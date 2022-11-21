INSERT INTO
    Students(firstName,secondName,email,age,specialization)
VALUES
    ('Anastazja', 'Kaczmarek','anakacz@gmail.com',20,'Cybersecurity'),
    ('Radoslaw','Zielinski','radziel@gmail.com',23,'Artificial Intelligence and Machine Learning'),
    ('Pola','Wisniewska','polwis@gmail.com',20,'Cybersecurity'),
    ('Rafal','Witkowski','rafwit@gmail.com',21,'Robotic Process Automation'),
    ('Sonia','Makowska','sonmak@gmail.com',22,'Robotic Process Automation'),
    ('Inga','Sokolowska','ingsok@gmail.com',21,'Cybersecurity'),
    ('Antoni','Sadowski','antsad@gmail.com',21,'Artificial Intelligence and Machine Learning');

INSERT INTO
    Teachers(firstName,secondName,email,age,subject)
VALUES
    ('Olaf', 'Sikora','olasik@gmail.com',34,'Networking'),
    ('Szymon', 'Czerwinski','szyczer@gmail.com',54,'English'),
    ('Hubert', 'Jasinski','hubjas@gmail.com',30,'Scripting'),
    ('Lidia', 'Bak','lidbak@gmail.com',35,'ProgramingOOP'),
    ('Fabian', 'Wilk','fabwil@gmail.com',31,'Microprocessors'),
    ('Kornel', 'Piotrowski','korpio@gmail.com',42,'Math'),
    ('Inga', 'Kubiak','ingkub@gmail.com',29,'Physics');

INSERT INTO
    student_teacher(student_id, teacher_id)
VALUES
    (1,2),
    (1,1),
    (1,6),
    (2,1),
    (2,3),
    (2,6),
    (3,4),
    (3,6),
    (3,7),
    (4,3),
    (4,5),
    (4,7),
    (5,1),
    (5,4),
    (5,6),
    (6,2),
    (6,3),
    (6,7),
    (7,1),
    (7,2),
    (7,6);