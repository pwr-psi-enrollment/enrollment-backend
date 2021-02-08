-- +----------------+--------------+------+-----+---------+----------------+
-- | Field          | Type         | Null | Key | Default | Extra          |
-- +----------------+--------------+------+-----+---------+----------------+
-- | registrationID | bigint       | NO   | PRI | NULL    | auto_increment |
-- | adminID        | bigint       | NO   |     | NULL    |                |
-- | destination    | int          | NO   |     | NULL    |                |
-- | endDate        | datetime     | NO   |     | NULL    |                |
-- | fieldOfStudy   | bigint       | NO   |     | NULL    |                |
-- | kind           | int          | NO   |     | NULL    |                |
-- | name           | varchar(255) | NO   |     | NULL    |                |
-- | programmeID    | bigint       | NO   |     | NULL    |                |
-- | semesterID     | bigint       | NO   |     | NULL    |                |
-- | startDate      | datetime     | NO   |     | NULL    |                |
-- | status         | int          | NO   |     | NULL    |                |
-- +----------------+--------------+------+-----+---------+----------------+

INSERT INTO Registrations VALUES
    (1, 1, 0, '2021-03-01 12:00:00', 1, 1, 'W08 Zapisy zima 2020/2021', 1, 11, '2021-02-01 12:00:00', 0),
    (2, 1, 0, '2021-03-01 12:00:00', 1, 0, 'W08 Zapisy zima 2020/2021', 1, 11, '2021-02-01 12:00:00', 1),
    (3, 1, 1, '2021-03-01 12:00:00', 1, 0, 'W08 Zapisy zima 2020/2021', 1, 11, '2021-02-01 12:00:00', 1);

-- +-----------------------+----------+------+-----+---------+----------------+
-- | Field                 | Type     | Null | Key | Default | Extra          |
-- +-----------------------+----------+------+-----+---------+----------------+
-- | studentRegistrationID | bigint   | NO   | PRI | NULL    | auto_increment |
-- | registrationEnd       | datetime | NO   |     | NULL    |                |
-- | registeredID          | bigint   | NO   |     | NULL    |                |
-- | registrationStart     | datetime | NO   |     | NULL    |                |
-- | registrationID        | bigint   | NO   | MUL | NULL    |                |
-- +-----------------------+----------+------+-----+---------+----------------+

INSERT INTO StudentRegistrations VALUES
    (NULL, '2021-03-01 12:00:00', 10, '2021-02-07 10:25:36', 1),
    (NULL, '2021-03-01 12:00:00', 10, '2021-02-06 12:13:10', 2),
    (NULL, '2021-03-01 12:00:00', 10, '2021-02-05 08:54:24', 3);
