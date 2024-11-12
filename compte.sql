-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 12 nov. 2024 à 11:08
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_banque`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `type_cpte` varchar(2) NOT NULL,
  `code_compte` varchar(255) NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `solde` double NOT NULL,
  `decouvert` double DEFAULT NULL,
  `taux` double DEFAULT NULL,
  `code_cli` bigint(20) DEFAULT NULL,
  `code_emp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`type_cpte`, `code_compte`, `date_creation`, `solde`, `decouvert`, `taux`, `code_cli`, `code_emp`) VALUES
('CC', '5', '2024-11-10 18:06:32.000000', 10000, 0, NULL, 1, 1),
('CC', 'CO100', '2024-11-10 18:19:11.000000', 42052, 0, NULL, 10, 1),
('CC', 'CO40', '2024-11-10 18:17:41.000000', 59907, 0, NULL, 4, 1),
('CC', 'CO90', '2024-11-10 18:18:52.000000', 1367758, 0, NULL, 9, 1),
('CE', 'EP30', '2024-11-10 18:17:33.000000', 100, NULL, 0, 3, 1),
('CE', 'EP50', '2024-11-10 18:17:52.000000', 10237, NULL, 0, 5, 1),
('CE', 'EP60', '2024-11-10 18:18:05.000000', 143445, NULL, 0, 6, 1),
('CE', 'EP70', '2024-11-10 18:18:18.000000', 1022408, NULL, 0, 7, 1),
('CE', 'EP80', '2024-11-10 18:18:34.000000', 1023659, NULL, 0, 8, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`code_compte`),
  ADD KEY `FKa8l2at3oxclvmce52td0vsabn` (`code_cli`),
  ADD KEY `FKbm21kemcgkptyq3x0ge8ciaqu` (`code_emp`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FKa8l2at3oxclvmce52td0vsabn` FOREIGN KEY (`code_cli`) REFERENCES `clients` (`code_client`),
  ADD CONSTRAINT `FKbm21kemcgkptyq3x0ge8ciaqu` FOREIGN KEY (`code_emp`) REFERENCES `employe` (`code_employe`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
