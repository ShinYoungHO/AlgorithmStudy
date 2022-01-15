SELECT ROUND(MAX(LAT_N)+MAX(LONG_W)-MIN(LAT_N)-MIN(LONG_W), 4)
FROM STATION

--
SELECT ROUND(SQRT(POWER(MAX(LAT_N)-MIN(LAT_N),2)+POWER(MAX(LONG_W)-MIN(LONG_W),2)), 4)
FROM STATION

--
SELECT CONCAT(NAME, '(',LEFT(OCCUPATION, 1),')') AS A
FROM OCCUPATIONS
UNION
SELECT CONCAT('There are a total of ', COUNT(NAME),' ', LOWER(OCCUPATION)
--               CASE
--                   WHEN COUNT(NAME) = 1 THEN ''
--                   ELSE 's'
--               END,
           ,'s.')
FROM OCCUPATIONS
GROUP BY OCCUPATION
ORDER BY A;

--
SELECT e.company_code,
       cm.founder,
       COUNT(DISTINCT e.lead_manager_code),
       COUNT(DISTINCT e.senior_manager_code),
       COUNT(DISTINCT e.manager_code),
       COUNT(DISTINCT e.employee_code)
FROM Company AS cm
         JOIN Employee AS e
              ON cm.company_code = e.company_code
GROUP BY cm.company_code, cm.founder
ORDER BY cm.company_code
