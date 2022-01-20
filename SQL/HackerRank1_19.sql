/*
print the hacker_id, name, and the total number of challenges created by each student.
챌린지 문제 생성 갯수가 겹치는 경우 최대 문제 생성갯수가 아닌 경우만 배제하고 출력
*/
--
SET @max := (
                SELECT COUNT(c.challenge_id) k
                FROM Challenges c
                GROUP BY c.hacker_id
                ORDER BY k DESC
                LIMIT 1
              );

SELECT h.hacker_id i, h.name n, COUNT(c.hacker_id) c
FROM Hackers h
         JOIN Challenges c ON c.hacker_id = h.hacker_id
GROUP BY h.hacker_id, h.name
HAVING c = @max OR c IN (
    SELECT t.cnt
    FROM (SELECT COUNT(*) cnt
          FROM Challenges
          GROUP BY hacker_id) t
    GROUP BY t.cnt
    HAVING COUNT(t.cnt) = 1
)
ORDER BY c DESC, i

--
SELECT t.hid id, h.name, SUM(t.sc) score
FROM (
         SELECT s.hacker_id hid, s.challenge_id cid, MAX(s.score) sc
         FROM Submissions s
         GROUP BY s.hacker_id, s.challenge_id
     ) t
         JOIN Hackers h ON h.hacker_id = t.hid
GROUP BY t.hid, h.name
HAVING score != 0
ORDER BY score DESC, id

