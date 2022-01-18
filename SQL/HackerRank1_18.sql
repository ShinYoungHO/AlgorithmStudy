SELECT DISTINCT h.hacker_id, h.name
FROM Submissions s
         JOIN Challenges  c ON s.challenge_id = c.challenge_id
         JOIN Difficulty d ON c.difficulty_level = d.difficulty_level
         JOIN Hackers h ON s.hacker_id = h.hacker_id
WHERE s.score = d.score
GROUP BY h.hacker_id, h.name
HAVING COUNT(*) > 1 -- more than 1...
ORDER BY COUNT(*) DESC, h.hacker_id

--

-- 코드가 같고 파워가 같은 지팡이 중 최소비용을 고름

    -- case 1 : 집계를 유지하며 정렬됨에 의한 OUTPUT MISS
SELECT www.id, w.code, wp.age, w.coins_needed, w.power
FROM Wands_Property wp
         JOIN (
    SELECT ww.code AS code, ww.power AS power, MIN(ww.coins_needed) AS coins_needed
    FROM Wands ww
    GROUP BY ww.code, ww.power
) w ON w.code = wp.code
         JOIN Wands www ON www.code = w.code && www.power = w.power
WHERE wp.is_evil = 0
ORDER BY w.power DESC, wp.age DESC

    -- case 2 : 하나의 테이블에서 집계테이블에 맞는 데이터 추출? IN 의 SubQuery 컬럼갯수 최대 1000
SELECT w.id, wp.age, w.coins_needed, w.power
FROM Wands w
         JOIN Wands_Property wp  ON w.code = wp.code
WHERE (wp.age, w.power, w.coins_needed) IN (
    SELECT wpp.age, ww.power, MIN(ww.coins_needed)
    FROM Wands ww
             JOIN Wands_Property wpp ON ww.code = wpp.code
    WHERE wpp.is_Evil = 0 -- && wp.age = wpp.age && ww.power = w.power
    GROUP BY wpp.age, ww.power
)
ORDER BY w.power DESC, wp.age DESC