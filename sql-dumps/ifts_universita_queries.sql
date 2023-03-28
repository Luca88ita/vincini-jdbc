SELECT COUNT(*) AS "Totale studenti di Modena", COUNT(*)-COUNT(citta) AS "Studenti con la citta nulla"
FROM s;

SELECT COUNT(*) AS "Studenti con la citta nulla"
FROM s
WHERE citta IS NULL;

SELECT COUNT(DISTINCT acorso) AS "diversi anni di corso studenti"
FROM s;

SELECT COUNT(*) AS "totale esami sostenuti", 
	MIN(voto) AS "voto piu basso", 
    MAX(voto) AS "voto massimo", 
    AVG(voto) AS "voto medio", 
    SUM(voto)/COUNT(*)  AS "voto medio 2"
FROM e,s
WHERE e.matr = s.matr
AND s.snome = "Lucia Quaranta";

/* quanti esami verbalizzati da docente Paolo Rossi, voti min max avg */
SELECT COUNT(*) AS "totale esami verbalizzati", 
	MIN(voto) AS "voto piu basso", 
    MAX(voto) AS "voto massimo", 
    AVG(voto) AS "voto medio"
FROM e, d, c
WHERE c.cd = d.cd
AND e.cc = c.cc
AND d.dnome = "Paolo Rossi";

/* numero di studenti che hanno sostenuto almeno un esame*/
SELECT COUNT(DISTINCT matr) AS "Totale studenti che hanno sostenuto almeno un esame", 
	COUNT(*)/COUNT(DISTINCT matr) AS "Media esami sostenuti da chi ha fatto almeno un esame"
FROM e;

/* studenti il cui anno di corso è minore di quello massimo presente */
SELECT snome
FROM s
WHERE s.acorso < (SELECT MAX(acorso)
					FROM s);
                    
/* avere il voto massimo preso da ogni matricola , biù basso e media */
SELECT s.snome AS "Numero matricola", MAX(e.voto) AS "Voto più alto", MIN(e.voto) AS "Voto più basso", AVG(e.voto) AS "Voto medio", COUNT(e.voto) AS "Totale esami sostenuti"
FROM e, s
WHERE e.matr = s.matr
GROUP BY e.matr, s.snome;

/* per i corsi minima media massima*/
SELECT c.cc AS "Nome Corso", c.cnome AS "Nome corso", MAX(e.voto) AS "Voto più alto", MIN(e.voto) AS "Voto più basso", AVG(e.voto) AS "Voto medio", COUNT(e.voto) AS "Totale studenti che hanno sostenuto l'esame"
FROM e, c
WHERE e.cc = c.cc
GROUP BY c.cnome, e.cc;

/* per ogni docente quanti esami ha fatto */
SELECT d.dnome AS "Nome docente",c.cnome AS "Nome Corso", MAX(e.voto) AS "Voto più alto assegnato", MIN(e.voto) AS "Voto più basso assegnato", AVG(e.voto) AS "Voto medio", COUNT(e.voto) AS "Totale voti registrati"
FROM e, d, c
WHERE e.cc = c.cc 
AND c.cd = d.cd
GROUP BY d.dnome, c.cd, e.cc;

/* per ogni studente i dati dei vari esami escludendo l'esame c1 */
SELECT s.snome AS "Numero matricola", MAX(e.voto) AS "Voto più alto", MIN(e.voto) AS "Voto più basso", AVG(e.voto) AS "Voto medio", COUNT(e.voto) AS "Totale esami sostenuti"
FROM e, s
WHERE e.matr = s.matr
AND e.cc <> "c1"
GROUP BY e.matr, s.snome;

/* per ogni studente i dati dei vari esami escludendo l'esame fisica1 */
SELECT s.snome AS "Numero matricola", MAX(e.voto) AS "Voto più alto", MIN(e.voto) AS "Voto più basso", AVG(e.voto) AS "Voto medio", COUNT(e.voto) AS "Totale esami sostenuti"
FROM e, s, c
WHERE e.matr = s.matr
AND e.cc = c.cc 
AND c.cnome <> "Fisica 1"
GROUP BY e.matr, s.snome;

/* per ogni studente i dati dei vari esami escludendo l'esame fisica1 e che abbiano sostenuto almeno 2 esami (sempre escludendo fisica 1) */
SELECT s.snome AS "Numero matricola", MAX(e.voto) AS "Voto più alto", MIN(e.voto) AS "Voto più basso", AVG(e.voto) AS "Voto medio", COUNT(e.voto) AS "Totale esami sostenuti"
FROM e, s, c
WHERE e.matr = s.matr
AND e.cc = c.cc 
AND c.cnome <> "Fisica 1"
GROUP BY e.matr, s.snome
HAVING COUNT(e.voto)>1;

/* seleziona tutti gli studenti con media più alta di lucia quaranta */
SELECT s.snome, AVG(e.voto) AS "media voti"
FROM s, e
WHERE s.matr = e.matr
GROUP BY s.matr
HAVING AVG(e.voto)>(SELECT AVG(e.voto)
	FROM s, e
    WHERE s.matr = e.matr
    AND s.snome = "Lucia Quaranta");
    
/* seleziona tutti gli studenti con media più alta tra tutti gli studenti */
SELECT s.snome, AVG(e.voto) AS "media voti"
FROM s, e
WHERE s.matr = e.matr
GROUP BY s.matr
HAVING AVG(e.voto)>= ALL
	(SELECT AVG(e.voto)
	FROM e
    GROUP BY e.matr);
    
/* media dei voti per ogni esame sostenuto da più di 3 studenti*/
SELECT c.cnome, AVG(e.voto) AS "media voti"
FROM e, c
WHERE e.cc = c.cc
GROUP BY e.cc, c.cnome
HAVING COUNT(voto) > 3;

/* trovare il docente che ha dato il voto più basso */
SELECT d.dnome AS "Nome Docente", MIN(e.voto)
FROM e,c,d
WHERE e.cc = c.cc
AND c.cd = d.cd
GROUP BY d.cd, d.dnome
HAVING MIN(e.voto) <= ALL 
	(SELECT MIN(e.voto)
    FROM e);
