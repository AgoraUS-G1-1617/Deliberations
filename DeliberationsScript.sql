-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-11-2015 a las 11:35:20
-- Versión del servidor: 10.0.17-MariaDB
-- Versión de PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

# Privilegios para `acme-manager`@`%`

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, TRIGGER ON *.* TO 'acme-manager'@'%' IDENTIFIED BY PASSWORD '*FDB8CD304EB2317D10C95D797A4BD7492560F55F' WITH GRANT OPTION;

# Privilegios para `acme-user`@`%`

GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO 'acme-user'@'%' IDENTIFIED BY PASSWORD '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577' WITH GRANT OPTION;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `deliberations`
--
CREATE DATABASE IF NOT EXISTS `deliberations` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `deliberations`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actor`
--

CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrator`
--

CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `thread_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequences`
--

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `hibernate_sequences`
--

INSERT INTO `hibernate_sequences` (`sequence_name`, `sequence_next_hi_value`) VALUES
('DomainEntity', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hilo`
--

CREATE TABLE `hilo` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `banned` bit(1) NOT NULL,
  `numberOfMessages` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Estructura de tabla para la tabla `useraccount`
--

CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `useraccount`
--

INSERT INTO `useraccount` (`id`, `version`, `password`, `username`) VALUES
(1, 0, '21232f297a57a5a743894a0e4a801fc3', 'admin'),
(2, 0, '91ec1f9324753048c0096d036a694f86', 'customer'),
(3, 0, '1b3231655cebb7a1f783eddf27d254ca', 'super');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `useraccount_authorities`
--

CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`);

--
-- Indices de la tabla `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`);

--
-- Indices de la tabla `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_p3dpnjlcdpyerp1tmmllrkgkk` (`thread_id`),
  ADD KEY `FK_jhvt6d9ap8gxv67ftrmshdfhj` (`user_id`);

--
-- Indices de la tabla `hilo`
--
ALTER TABLE `hilo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_9tr0n5ixknn2g3pd338j3urkq` (`user_id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_o6s94d43co03sx067ili5760c` (`userAccount_id`);

--
-- Indices de la tabla `useraccount`
--
ALTER TABLE `useraccount`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`);

--
-- Indices de la tabla `useraccount_authorities`
--
ALTER TABLE `useraccount_authorities`
  ADD KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actor`
--
ALTER TABLE `actor`
  ADD CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`);

--
-- Filtros para la tabla `administrator`
--
ALTER TABLE `administrator`
  ADD CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`);

--
-- Filtros para la tabla `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_jhvt6d9ap8gxv67ftrmshdfhj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_p3dpnjlcdpyerp1tmmllrkgkk` FOREIGN KEY (`thread_id`) REFERENCES `hilo` (`id`);

--
-- Filtros para la tabla `hilo`
--
ALTER TABLE `hilo`
  ADD CONSTRAINT `FK_9tr0n5ixknn2g3pd338j3urkq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`);

--
-- Filtros para la tabla `useraccount_authorities`
--
ALTER TABLE `useraccount_authorities`
  ADD CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
