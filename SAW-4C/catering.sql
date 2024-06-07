-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2024 at 05:25 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `catering`
--

-- --------------------------------------------------------

--
-- Table structure for table `alternatif`
--

CREATE TABLE `alternatif` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alternatif`
--

INSERT INTO `alternatif` (`id`, `nama`, `alamat`, `no_hp`) VALUES
(1, 'Twin Catering', 'Tegal', '03158458575'),
(2, 'Bintang Rasa', 'Tegal', '0815454875'),
(3, 'Puspa Catering', 'Tegal', '08145658587'),
(4, 'Bumbu Desa', 'Tegal', '0815454555'),
(5, 'Arya Duta Catering', 'Tegal', '08132545785');

-- --------------------------------------------------------

--
-- Table structure for table `datanilai`
--

CREATE TABLE `datanilai` (
  `id` int(11) NOT NULL,
  `alt_id` int(11) NOT NULL,
  `c1` double NOT NULL,
  `c2` double NOT NULL,
  `c3` double NOT NULL,
  `c4` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `datanilai`
--

INSERT INTO `datanilai` (`id`, `alt_id`, `c1`, `c2`, `c3`, `c4`) VALUES
(1, 1, 8, 3, 4, 5),
(2, 2, 5, 6, 4, 5),
(3, 3, 8, 4, 5, 3),
(4, 4, 5, 4, 3, 9),
(5, 5, 7, 10, 6, 8);

-- --------------------------------------------------------

--
-- Table structure for table `hasilsaw`
--

CREATE TABLE `hasilsaw` (
  `id` int(11) NOT NULL,
  `alt_id` int(11) NOT NULL,
  `bobot` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hasilsaw`
--

INSERT INTO `hasilsaw` (`id`, `alt_id`, `bobot`) VALUES
(1, 1, 0.5351388888888888),
(2, 2, 0.7188888888888889),
(3, 3, 0.4795833333333333),
(4, 4, 0.8200000000000001),
(5, 5, 0.8007936507936507);

-- --------------------------------------------------------

--
-- Table structure for table `kriteria`
--

CREATE TABLE `kriteria` (
  `id` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `bobot` double NOT NULL,
  `label` enum('cost','benefit') NOT NULL DEFAULT 'benefit',
  `flag` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kriteria`
--

INSERT INTO `kriteria` (`id`, `nama`, `bobot`, `label`, `flag`) VALUES
(1, 'harga', 0.25, 'cost', 'c1'),
(2, 'rasa', 0.3, 'benefit', 'c2'),
(3, 'lokasi', 0.2, 'cost', 'c3'),
(4, 'layanan', 0.25, 'benefit', 'c4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alternatif`
--
ALTER TABLE `alternatif`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `datanilai`
--
ALTER TABLE `datanilai`
  ADD PRIMARY KEY (`id`),
  ADD KEY `alt_id` (`alt_id`);

--
-- Indexes for table `hasilsaw`
--
ALTER TABLE `hasilsaw`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kriteria`
--
ALTER TABLE `kriteria`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alternatif`
--
ALTER TABLE `alternatif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `datanilai`
--
ALTER TABLE `datanilai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `hasilsaw`
--
ALTER TABLE `hasilsaw`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `kriteria`
--
ALTER TABLE `kriteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `datanilai`
--
ALTER TABLE `datanilai`
  ADD CONSTRAINT `datanilai_ibfk_1` FOREIGN KEY (`alt_id`) REFERENCES `alternatif` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
