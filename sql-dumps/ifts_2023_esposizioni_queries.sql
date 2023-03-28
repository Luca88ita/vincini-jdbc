/* le sale nelle quali è esposto nel 97 un quadro di picasso */
SELECT DISTINCT espone.sala AS "Sale dove è stato esposto un quadro di Picasso nel 1997"
FROM espone, quadro, mostra
WHERE espone.cq = quadro.cq
AND mostra.cm = espone.cm
AND quadro.autore = "Picasso"
AND mostra.anno = 1997;

/* tutti i dati dei quadri di Picasso che non sono mai stati esposti nel 1997 */
SELECT DISTINCT quadro.*
FROM quadro
WHERE quadro.autore = "Picasso"
AND quadro.cq NOT IN (
		SELECT quadro.cq
		FROM espone, quadro, mostra
		WHERE espone.cq = quadro.cq
		AND mostra.cm = espone.cm
		AND mostra.anno = 1997
        AND quadro.autore = "Picasso");

/* selezionare tutti i dati dei quadri che non sono mai stati esposti insieme ad un quadro di Picasso */
SELECT DISTINCT quadro.*
FROM quadro
WHERE quadro.cq NOT IN (
	SELECT DISTINCT espone.cq
	FROM espone
	WHERE espone.cm IN (
		SELECT DISTINCT espone.cm
		FROM espone, quadro
		WHERE espone.cq = quadro.cq
		AND quadro.autore = "Picasso"));
    
/* seleziona tutti i dati delle mostre in cui sono stati esposti i quadri di almeno 5 autori distinti */
SELECT mostra.*
FROM espone, quadro, mostra
WHERE espone.cq = quadro.cq
AND mostra.cm = espone.cm
GROUP BY mostra.cm 
HAVING COUNT(DISTINCT quadro.autore) >= 5;

/* selezionare per ogni mostra l'autore di cui esponevano il maggior numero di quadri */
SELECT mostra.cm, quadro.autore, COUNT(quadro.autore)
FROM espone, quadro, mostra
WHERE espone.cq = quadro.cq
AND mostra.cm = espone.cm
GROUP BY quadro.autore, mostra.cm
HAVING COUNT(*) >= ALL 
	(SELECT COUNT(*) 
    FROM espone e2, quadro q2, mostra m2
	WHERE e2.cq = q2.cq
	AND m2.cm = e2.cm
    AND m2.cm = mostra.cm
	GROUP BY q2.autore, m2.cm);