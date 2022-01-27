SELECT s.Name
FROM (
         SELECT s.Name Name, s.ID ID, f.Friend_ID Friend_ID, p.Salary Salary
         FROM Students s
                  JOIN Friends f ON f.ID = s.ID
                  JOIN Packages p ON p.ID = s.ID
     ) s
         JOIN Packages p ON p.ID = s.Friend_ID
WHERE s.Salary < p.Salary
ORDER BY p.Salary

--



