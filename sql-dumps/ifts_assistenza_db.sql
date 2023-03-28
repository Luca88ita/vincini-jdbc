-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 28, 2023 alle 11:01
-- Versione del server: 10.4.27-MariaDB
-- Versione PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ifts_assistenza`
--
CREATE DATABASE IF NOT EXISTS `ifts_assistenza` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ifts_assistenza`;

-- --------------------------------------------------------

--
-- Struttura della tabella `cliente`
--

CREATE TABLE `cliente` (
  `codc` varchar(10) NOT NULL,
  `nomecliente` varchar(45) NOT NULL,
  `citta` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `cliente`
--

INSERT INTO `cliente` (`codc`, `nomecliente`, `citta`) VALUES
('C1', 'Mario Rossi', 'Modena'),
('C2', 'Luca Verdi', 'Firenze'),
('C3', 'Alessandra Monzani', 'Modena'),
('C4', 'Lucia Davoli', 'Milano'),
('C5', 'Marco Costi', 'Bologna'),
('C6', 'Pippo Franco', 'Bologna');

-- --------------------------------------------------------

--
-- Struttura della tabella `errore`
--

CREATE TABLE `errore` (
  `code` varchar(10) NOT NULL,
  `codc` varchar(10) NOT NULL,
  `descrizione` varchar(45) NOT NULL,
  `costo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `errore`
--

INSERT INTO `errore` (`code`, `codc`, `descrizione`, `costo`) VALUES
('E1', 'C1', 'Meccanico', 4),
('E1', 'C2', 'Luce Rotta', 4),
('E1', 'C3', 'Palla verde', 10),
('E1', 'C4', 'Confezione incompleta', 10),
('E2', 'C1', 'Palla rossa', 6),
('E2', 'C4', 'Non arrivato', 6),
('E3', 'C1', 'Elettrico', 2),
('E3', 'C2', 'Indirizzo errato', 2);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codc`);

--
-- Indici per le tabelle `errore`
--
ALTER TABLE `errore`
  ADD PRIMARY KEY (`code`,`codc`),
  ADD KEY `FK1` (`codc`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `errore`
--
ALTER TABLE `errore`
  ADD CONSTRAINT `FK1` FOREIGN KEY (`codc`) REFERENCES `cliente` (`codc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
