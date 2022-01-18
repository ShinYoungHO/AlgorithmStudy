SET @idx := -1;
SELECT ROUND(LAT_N, 4)
FROM(
    SELECT @idx := @idx+1 AS rowIdx, LAT_N
    FROM STATION
    ORDER BY LAT_N
    ) AS idLat
WHERE rowIdx = FLOOR(@idx/2)

--
SELECT IF(Grades.Grade < 8, NULL, Students.Name), Grades.Grade, Students.Marks
FROM Students
         JOIN Grades
WHERE Students.Marks BETWEEN Grades.Min_Mark AND Grades.Max_Mark
ORDER BY Grades.grade DESC, Students.Name

--

