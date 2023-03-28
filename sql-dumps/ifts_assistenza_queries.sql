/* selezionare tutti i clienti che hanno avuto un errore di tipo E1 */
SELECT cliente.*
FROM cliente, errore
WHERE cliente.codc = errore.codc
AND errore.code = 'E1';

/* selezionare tutti i clienti che hanno avuto un errore diverso dal tipo E1 
(si noti che sono INCLUSI quelli che hanno avuto ANCHE E1)
(si noti che sono ESCLUSI quelli che NON hanno avuto errori) */
SELECT DISTINCT cliente.*
FROM cliente, errore
WHERE cliente.codc = errore.codc
AND errore.code <> 'E1';

/* selezionare tutti i clienti che NON hanno MAI avuto un errore di tipo E1 
(si noti che sono INCLUSI quelli che NON hanno MAI avuto errori)*/
SELECT cliente.*
FROM cliente
WHERE cliente.codc NOT IN (
	SELECT errore.codc
    FROM errore
    WHERE errore.code = 'E1');

/* selezionare città dove NON si è MAI presentato l'errore E1 */
SELECT DISTINCT cliente.citta
FROM cliente
WHERE cliente.citta NOT IN (
	SELECT DISTINCT cliente.citta
	FROM cliente, errore
	WHERE cliente.codc = errore.codc
	AND errore.code = 'E1');
    
/* selezionare per ciascun codice errore il numero di interventi */
SELECT errore.code, COUNT(errore.code) AS 'Numero interventi'
FROM errore
GROUP BY errore.code;

/* selezionare per ogni citta i costi totali delle riparazioni */
SELECT cliente.citta, SUM(errore.costo) AS 'Somma costo riparazioni'
FROM cliente, errore
WHERE cliente.codc = errore.codc
GROUP BY cliente.citta;

/* selezionare i dati del cliente che ha avuto il costo totale maggiore dovuto ad errori */
SELECT cliente.*, SUM(errore.costo) AS 'Somma costo riparazioni'
FROM cliente, errore
WHERE cliente.codc = errore.codc
GROUP BY cliente.codc
HAVING SUM(errore.costo) >= ALL (
	SELECT SUM(errore.costo)
	FROM errore
	GROUP BY errore.codc);