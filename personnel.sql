
CREATE TABLE `employe` (
  `idemploye` int(11) NOT NULL,
  `nom` varchar(42) DEFAULT NULL,
  `prénom` varchar(42) DEFAULT NULL,
  `Tel` int(42) NOT NULL,
  `date_anniv` date DEFAULT NULL,
  `mail` varchar(42) DEFAULT NULL,
  `password_emp` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


ALTER TABLE `employe`
  ADD PRIMARY KEY (`idemploye`);

ALTER TABLE `employe`
  MODIFY `idemploye` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

