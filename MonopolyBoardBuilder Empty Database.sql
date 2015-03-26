-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2015 at 09:14 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mbb`
--

-- --------------------------------------------------------

--
-- Table structure for table `board`
--

CREATE TABLE IF NOT EXISTS `board` (
`board_id` int(5) NOT NULL,
  `board_name` varchar(100) NOT NULL,
  `image_url` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `chance`
--

CREATE TABLE IF NOT EXISTS `chance` (
  `chance_id` int(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(250) NOT NULL,
  `type` varchar(5) NOT NULL,
  `board_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `communitychest`
--

CREATE TABLE IF NOT EXISTS `communitychest` (
  `chest_id` int(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(250) NOT NULL,
  `type` varchar(5) NOT NULL,
  `board_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE IF NOT EXISTS `property` (
  `property_id` int(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `tax` varchar(50) NOT NULL,
  `house_cost` int(5) NOT NULL,
  `cost` int(5) NOT NULL,
  `type` varchar(5) NOT NULL,
  `board_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `board`
--
ALTER TABLE `board`
 ADD PRIMARY KEY (`board_id`);

--
-- Indexes for table `chance`
--
ALTER TABLE `chance`
 ADD PRIMARY KEY (`chance_id`,`board_id`), ADD KEY `chance_board_id_fk` (`board_id`);

--
-- Indexes for table `communitychest`
--
ALTER TABLE `communitychest`
 ADD PRIMARY KEY (`chest_id`,`board_id`), ADD KEY `chest_board_id_fk` (`board_id`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
 ADD PRIMARY KEY (`property_id`,`board_id`), ADD KEY `property_board_id_fk` (`board_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `board`
--
ALTER TABLE `board`
MODIFY `board_id` int(5) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `chance`
--
ALTER TABLE `chance`
ADD CONSTRAINT `chance_board_id_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `communitychest`
--
ALTER TABLE `communitychest`
ADD CONSTRAINT `chest_board_id_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `property`
--
ALTER TABLE `property`
ADD CONSTRAINT `property_board_id_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
