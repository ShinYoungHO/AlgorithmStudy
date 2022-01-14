SELECT SUM(POPULATION)
FROM CITY
WHERE DISTRICT = 'California'

--
SELECT AVG(POPULATION)
FROM CITY
WHERE DISTRICT = 'California'

--
SELECT CAST(AVG(POPULATION) AS SIGNED)
FROM CITY

--
SELECT MAX(POPULATION)-MIN(POPULATION)
FROM CITY

--
SELECT AVG(Salary)-AVG(
    CAST(REPLACE(CAST(Salary AS CHAR),'[0]+','')AS UNSIGNED)
    )
FROM EMPLOYEES

--
SELECT CEIL(AVG(Salary)-AVG(
        CAST(
                REGEXP_REPLACE(CAST(Salary AS CHAR),'[0]+','')AS UNSIGNED
            )))
FROM EMPLOYEES

--
SELECT (salary*months) AS a, COUNT(*)
FROM employee
GROUP BY a
ORDER BY a DESC LIMIT 1

--
SELECT ROUND(SUM(LAT_N),2), ROUND(SUM(LONG_W),2)
FROM STATION

--
SELECT ROUND(SUM(LAT_N), 4)
FROM STATION
WHERE LAT_N >= 38.7880 && LAT_N <= 137.2345

--
SELECT ROUND(LAT_N, 4)
FROM STATION
WHERE LAT_N >= 38.7880 && LAT_N <= 137.2345
ORDER BY LAT_N DESC LIMIT 1

--
SELECT ROUND(LONG_W, 4)
FROM STATION
WHERE LAT_N <= 137.2345
ORDER BY LAT_N DESC LIMIT 1

--
SELECT ROUND(LAT_N, 4)
FROM STATION
WHERE LAT_N >= 38.7780
ORDER BY LAT_N LIMIT 1

--
SELECT ROUND(LONG_W, 4)
FROM STATION
WHERE LAT_N >= 38.7780
ORDER BY LAT_N LIMIT 1

--
SELECT SUM(CITY.POPULATION)
FROM CITY
         JOIN COUNTRY ON CITY.COUNTRYCODE = COUNTRY.CODE
WHERE COUNTRY.CONTINENT = 'Asia'

--
SELECT CITY.NAME
FROM CITY
         JOIN COUNTRY ON CITY.COUNTRYCODE = COUNTRY.CODE
WHERE COUNTRY.CONTINENT = 'Africa'

--
SELECT COUNTRY.CONTINENT, FLOOR(SUM(CITY.POPULATION)/COUNT(CITY.COUNTRYCODE))
FROM CITY
         JOIN COUNTRY ON CITY.COUNTRYCODE = COUNTRY.CODE
GROUP BY COUNTRY.CONTINENT