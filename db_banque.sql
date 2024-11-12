-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 12 nov. 2024 à 11:10
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
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `code_client` bigint(20) NOT NULL,
  `nom_client` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`code_client`, `nom_client`) VALUES
(1, 'ABDESSAMAD'),
(2, 'Hodaifa'),
(3, 'Azize'),
(4, 'Mostafa'),
(5, 'ABBASS'),
(6, 'Fatima Zahra'),
(7, 'Fatima Zahra'),
(8, 'Yassir'),
(9, 'ABdellah'),
(10, 'Yassmine ');

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

-- --------------------------------------------------------

--
-- Structure de la table `db_banque`
--

CREATE TABLE `db_banque` (
  `COL 1` varchar(20) DEFAULT NULL,
  `COL 2` varchar(26) DEFAULT NULL,
  `COL 3` varchar(26) DEFAULT NULL,
  `COL 4` varchar(9) DEFAULT NULL,
  `COL 5` varchar(9) DEFAULT NULL,
  `COL 6` varchar(19) DEFAULT NULL,
  `COL 7` varchar(8) DEFAULT NULL,
  `COL 8` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `db_banque`
--

INSERT INTO `db_banque` (`COL 1`, `COL 2`, `COL 3`, `COL 4`, `COL 5`, `COL 6`, `COL 7`, `COL 8`) VALUES
('code_client', 'nom_client', NULL, NULL, NULL, NULL, NULL, NULL),
('1', 'Fatiha', NULL, NULL, NULL, NULL, NULL, NULL),
('2', 'Samir', NULL, NULL, NULL, NULL, NULL, NULL),
('3', 'Taher', NULL, NULL, NULL, NULL, NULL, NULL),
('4', 'Abdeljabbar', NULL, NULL, NULL, NULL, NULL, NULL),
('5', 'Saladin', NULL, NULL, NULL, NULL, NULL, NULL),
('type_cpte', 'code_compte', 'date_creation', 'solde', 'decouvert', 'taux', 'code_cli', 'code_emp'),
('CC', 'CO10', '2024-11-05 19:02:05.000000', '10000', '0', '', '1', ''),
('CC', 'CO40', '2024-11-11 15:27:26.000000', '8600', '0', '', '4', '1'),
('CC', 'CO50', '2024-11-11 21:31:55.000000', '100000000', '0', '', '5', '1'),
('CE', 'EP20', '2024-11-05 19:02:36.000000', '0', '', '0', '2', ''),
('CE', 'EP30', '2024-11-10 20:23:24.000000', '0', '', '0', '3', '1'),
('code_employe', 'nom_employe', 'code_emp_sup', NULL, NULL, NULL, NULL, NULL),
('1', 'Simo', '4', NULL, NULL, NULL, NULL, NULL),
('4', 'Hodaifa', '1', NULL, NULL, NULL, NULL, NULL),
('5', 'Amine', '1', NULL, NULL, NULL, NULL, NULL),
('6', 'Hafid', '1', NULL, NULL, NULL, NULL, NULL),
('7', 'Hadof', '1', NULL, NULL, NULL, NULL, NULL),
('8', 'Mehdiatkekigannouni', '4', NULL, NULL, NULL, NULL, NULL),
('9', 'Mehdiatkekigannouni', '4', NULL, NULL, NULL, NULL, NULL),
('10', 'Mehdiatkekigannouni', '4', NULL, NULL, NULL, NULL, NULL),
('11', 'Mehdiatkekigannouni', '4', NULL, NULL, NULL, NULL, NULL),
('employe_code_employe', 'groupes_code_groupe', NULL, NULL, NULL, NULL, NULL, NULL),
('5', '1', NULL, NULL, NULL, NULL, NULL, NULL),
('6', '1', NULL, NULL, NULL, NULL, NULL, NULL),
('7', '1', NULL, NULL, NULL, NULL, NULL, NULL),
('8', '1', NULL, NULL, NULL, NULL, NULL, NULL),
('9', '1', NULL, NULL, NULL, NULL, NULL, NULL),
('10', '1', NULL, NULL, NULL, NULL, NULL, NULL),
('11', '1', NULL, NULL, NULL, NULL, NULL, NULL),
('code_groupe', 'nom_groupe', NULL, NULL, NULL, NULL, NULL, NULL),
('1', 'Umnia', NULL, NULL, NULL, NULL, NULL, NULL),
('dtype', 'numero_operation', 'date_operation', 'montant', 'code_cpte', 'code_emp', NULL, NULL),
('V', '1', '2024-11-05 19:02:57.000000', '10000', 'CO10', '', NULL, NULL),
('V', '2', '2024-11-11 15:28:48.000000', '8700', 'CO40', '1', NULL, NULL),
('T', '3', '2024-11-11 15:29:17.000000', '100', 'CO40', '1', NULL, NULL),
('R', '4', '2024-11-11 21:29:24.000000', '100', 'CO10', '1', NULL, NULL),
('V', '5', '2024-11-11 21:32:30.000000', '100000000', 'CO50', '1', NULL, NULL),
('id', 'created_at', 'updated_at', 'user_id', 'user_role', 'username', NULL, NULL),
('1', '', '', '1', 'Admin', 'mouaad', NULL, NULL),
('133', '2024-11-05 19:02:04.000000', '2024-11-05 19:02:04.000000', '1', 'Client', 'Fatiha', NULL, NULL),
('134', '2024-11-05 19:02:36.000000', '2024-11-05 19:02:36.000000', '2', 'Client', 'Samir', NULL, NULL),
('135', '2024-11-10 17:54:34.000000', '2024-11-10 17:54:34.000000', '5', 'Employe', 'Amine', NULL, NULL),
('136', '2024-11-10 20:16:09.000000', '2024-11-10 20:16:09.000000', '6', 'Employe', 'Hafid', NULL, NULL),
('137', '2024-11-10 20:23:24.000000', '2024-11-10 20:23:24.000000', '3', 'Client', 'Taher', NULL, NULL),
('138', '2024-11-11 14:55:56.000000', '2024-11-11 14:55:56.000000', '7', 'Employe', 'Hadof', NULL, NULL),
('139', '2024-11-11 15:27:25.000000', '2024-11-11 15:27:25.000000', '4', 'Client', 'Abdeljabbar', NULL, NULL),
('140', '2024-11-11 19:31:40.000000', '2024-11-11 19:31:40.000000', '8', 'Employe', 'Mehdiatkekigannouni', NULL, NULL),
('144', '2024-11-11 21:31:55.000000', '2024-11-11 21:31:55.000000', '5', 'Client', 'Saladin', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `code_employe` bigint(20) NOT NULL,
  `nom_employe` varchar(255) DEFAULT NULL,
  `code_emp_sup` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`code_employe`, `nom_employe`, `code_emp_sup`) VALUES
(1, 'Mustafa', NULL),
(2, 'Mouaad ELHANSALI', 1),
(3, 'Mouaad ELHANSALI', 1),
(4, 'Hamid', 2),
(5, 'Med BARBYCH', 2),
(6, 'Aziz Mahboub', 2),
(7, 'Abdelah', NULL),
(8, 'Fadwa', 3),
(9, 'HANAE', 2);

-- --------------------------------------------------------

--
-- Structure de la table `emp_gr`
--

CREATE TABLE `emp_gr` (
  `employe_code_employe` bigint(20) NOT NULL,
  `groupes_code_groupe` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `emp_gr`
--

INSERT INTO `emp_gr` (`employe_code_employe`, `groupes_code_groupe`) VALUES
(2, 1),
(3, 1),
(4, 2),
(5, 2),
(6, 1),
(8, 2),
(9, 4);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `code_groupe` bigint(20) NOT NULL,
  `nom_groupe` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`code_groupe`, `nom_groupe`) VALUES
(1, 'A'),
(2, 'B'),
(3, 'C'),
(4, 'F');

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `dtype` varchar(1) NOT NULL,
  `numero_operation` bigint(20) NOT NULL,
  `date_operation` datetime(6) DEFAULT NULL,
  `montant` double NOT NULL,
  `code_cpte` varchar(255) DEFAULT NULL,
  `code_emp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`dtype`, `numero_operation`, `date_operation`, `montant`, `code_cpte`, `code_emp`) VALUES
('V', 1, '2024-11-11 21:50:25.000000', 2003, 'CO90', 1),
('R', 2, '2024-11-11 21:50:33.000000', 620, 'CO90', 1),
('T', 3, '2024-11-11 21:50:44.000000', 1200, 'CO90', 1),
('R', 4, '2024-11-11 21:50:59.000000', 126, 'CO90', 1),
('R', 5, '2024-11-11 21:51:38.000000', 1563, 'EP70', 1),
('T', 6, '2024-11-11 21:52:43.000000', 7892, 'CO100', 1),
('T', 7, '2024-11-11 21:52:56.000000', 56, 'CO100', 1),
('R', 8, '2024-11-11 21:53:08.000000', 123, 'CO40', 1),
('V', 9, '2024-11-11 21:53:16.000000', 20, 'CO40', 1),
('T', 10, '2024-11-11 21:53:51.000000', 123, 'CO90', 1),
('T', 11, '2024-11-11 21:54:08.000000', 123, 'CO90', 1),
('R', 12, '2024-11-11 21:54:14.000000', 150, 'CO90', 1),
('T', 13, '2024-11-11 21:54:21.000000', 45, 'CO90', 1),
('V', 14, '2024-11-11 21:54:44.000000', 569, 'EP50', 1),
('R', 15, '2024-11-11 21:54:48.000000', 12, 'EP50', 1),
('R', 16, '2024-11-11 21:54:58.000000', 56, 'EP50', 1),
('V', 17, '2024-11-12 09:17:54.000000', 10, 'CO40', 1),
('V', 18, '2024-11-12 11:07:02.000000', 1000000, 'CO90', 1),
('V', 19, '2024-11-12 11:07:22.000000', 500, 'CO90', 1),
('R', 20, '2024-11-12 11:07:30.000000', 100, 'CO90', 1),
('T', 21, '2024-11-12 11:07:41.000000', 150, 'CO90', 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `created_at`, `updated_at`, `user_id`, `user_role`, `username`) VALUES
(1, '2024-11-02 20:53:19.000000', '2024-11-02 20:53:19.000000', 2, 'Employe', 'Mouaad ELHANSALI'),
(2, '2024-11-02 20:53:24.000000', '2024-11-02 20:53:24.000000', 3, 'Employe', 'Mouaad ELHANSALI'),
(3, '2024-11-02 21:05:14.000000', '2024-11-02 21:05:14.000000', 4, 'Employe', 'Hamid'),
(4, '2024-11-02 22:40:07.000000', '2024-11-02 22:40:07.000000', 5, 'Employe', 'Med BARBYCH'),
(5, '2024-11-05 18:37:04.000000', '2024-11-05 18:37:04.000000', 6, 'Employe', 'Aziz Mahboub'),
(6, '2024-11-10 18:17:33.000000', '2024-11-10 18:17:33.000000', 3, 'Client', 'Azize'),
(7, '2024-11-10 18:17:41.000000', '2024-11-10 18:17:41.000000', 4, 'Client', 'Mostafa'),
(8, '2024-11-10 18:17:52.000000', '2024-11-10 18:17:52.000000', 5, 'Client', 'ABBASS'),
(9, '2024-11-10 18:18:05.000000', '2024-11-10 18:18:05.000000', 6, 'Client', 'Fatima Zahra'),
(10, '2024-11-10 18:18:18.000000', '2024-11-10 18:18:18.000000', 7, 'Client', 'Fatima Zahra'),
(11, '2024-11-10 18:18:34.000000', '2024-11-10 18:18:34.000000', 8, 'Client', 'Yassir'),
(12, '2024-11-10 18:18:52.000000', '2024-11-10 18:18:52.000000', 9, 'Client', 'ABdellah'),
(13, '2024-11-10 18:19:11.000000', '2024-11-10 18:19:11.000000', 10, 'Client', 'Yassmine '),
(14, NULL, NULL, 1, 'Admin', 'MedBR'),
(15, '2024-11-11 21:17:19.000000', '2024-11-11 21:17:19.000000', 8, 'Employe', 'Fadwa'),
(16, '2024-11-11 21:18:12.000000', '2024-11-11 21:18:12.000000', 9, 'Employe', 'HANAE');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`code_client`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`code_compte`),
  ADD KEY `FKa8l2at3oxclvmce52td0vsabn` (`code_cli`),
  ADD KEY `FKbm21kemcgkptyq3x0ge8ciaqu` (`code_emp`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`code_employe`),
  ADD KEY `FKhsh3jgj0ctoiewmr371xmvby5` (`code_emp_sup`);

--
-- Index pour la table `emp_gr`
--
ALTER TABLE `emp_gr`
  ADD KEY `FKh9b18q3rdl1rq8txwr8hx2uox` (`groupes_code_groupe`),
  ADD KEY `FKok7g6c24qy0e7p22le9civkqk` (`employe_code_employe`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`code_groupe`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`numero_operation`),
  ADD KEY `FKkr9nfjf0ipqrw5xwcf9jqq6gv` (`code_cpte`),
  ADD KEY `FK88rq579v3pyf0y6v3p9wvhjo7` (`code_emp`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `code_client` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `employe`
--
ALTER TABLE `employe`
  MODIFY `code_employe` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `code_groupe` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `numero_operation` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FKa8l2at3oxclvmce52td0vsabn` FOREIGN KEY (`code_cli`) REFERENCES `clients` (`code_client`),
  ADD CONSTRAINT `FKbm21kemcgkptyq3x0ge8ciaqu` FOREIGN KEY (`code_emp`) REFERENCES `employe` (`code_employe`);

--
-- Contraintes pour la table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `FKhsh3jgj0ctoiewmr371xmvby5` FOREIGN KEY (`code_emp_sup`) REFERENCES `employe` (`code_employe`);

--
-- Contraintes pour la table `emp_gr`
--
ALTER TABLE `emp_gr`
  ADD CONSTRAINT `FKh9b18q3rdl1rq8txwr8hx2uox` FOREIGN KEY (`groupes_code_groupe`) REFERENCES `groupe` (`code_groupe`),
  ADD CONSTRAINT `FKok7g6c24qy0e7p22le9civkqk` FOREIGN KEY (`employe_code_employe`) REFERENCES `employe` (`code_employe`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `FK88rq579v3pyf0y6v3p9wvhjo7` FOREIGN KEY (`code_emp`) REFERENCES `employe` (`code_employe`),
  ADD CONSTRAINT `FKkr9nfjf0ipqrw5xwcf9jqq6gv` FOREIGN KEY (`code_cpte`) REFERENCES `compte` (`code_compte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
