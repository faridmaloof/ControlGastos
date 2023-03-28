-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 26-03-2023 a las 07:16:26
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `control_gastos`
--
CREATE DATABASE IF NOT EXISTS `control_gastos` DEFAULT CHARACTER SET utf32 COLLATE utf32_spanish2_ci;
USE `control_gastos`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf32 COLLATE utf32_spanish2_ci NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish2_ci;

--
-- Truncar tablas antes de insertar `categorias`
--

TRUNCATE TABLE `categorias`;
--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `activo`) VALUES
(1, 'Salarios', 1),
(2, 'Pruebasss', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
CREATE TABLE IF NOT EXISTS `cuentas` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf32 COLLATE utf32_spanish2_ci NOT NULL,
  `es_visible` tinyint(1) NOT NULL DEFAULT '1',
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish2_ci;

--
-- Truncar tablas antes de insertar `cuentas`
--

TRUNCATE TABLE `cuentas`;
--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `nombre`, `es_visible`, `activo`) VALUES
(3, 'Personal', 1, 1),
(4, 'Personales', 1, 1),
(5, 'Mamacita', 0, 1),
(6, 'Banco ladron', 1, 1),
(7, 'Pibank', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
CREATE TABLE IF NOT EXISTS `movimientos` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `naturaleza` enum('Ingreso','Egreso') CHARACTER SET utf32 COLLATE utf32_spanish2_ci NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valor` double NOT NULL,
  `id_tag` int UNSIGNED DEFAULT NULL,
  `id_tercero` int UNSIGNED DEFAULT NULL,
  `id_cuenta` int UNSIGNED NOT NULL,
  `id_catergoria` int UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tag` (`id_tag`),
  KEY `id_tercero` (`id_tercero`),
  KEY `id_cuenta` (`id_cuenta`),
  KEY `id_catergoria` (`id_catergoria`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish2_ci;

--
-- Truncar tablas antes de insertar `movimientos`
--

TRUNCATE TABLE `movimientos`;
--
-- Volcado de datos para la tabla `movimientos`
--

INSERT INTO `movimientos` (`id`, `naturaleza`, `fecha`, `valor`, `id_tag`, `id_tercero`, `id_cuenta`, `id_catergoria`) VALUES
(1, 'Ingreso', '2023-03-14 05:00:00', 58, NULL, NULL, 5, NULL),
(2, 'Ingreso', '2023-03-14 05:00:00', 4, NULL, NULL, 5, NULL),
(3, 'Ingreso', '2023-03-14 21:18:06', 9000000, NULL, NULL, 6, NULL),
(4, 'Egreso', '2023-03-14 21:19:38', 0, NULL, NULL, 7, NULL),
(5, 'Ingreso', '2023-03-16 03:08:09', 64270.52, NULL, NULL, 5, NULL),
(10, 'Egreso', '2023-03-11 05:00:00', 31354, NULL, NULL, 4, NULL),
(11, 'Egreso', '2023-03-25 05:00:00', 123654, NULL, NULL, 4, NULL),
(12, 'Egreso', '2023-03-25 05:00:00', 123654, NULL, NULL, 4, NULL),
(13, 'Egreso', '2023-03-24 05:00:00', 123, NULL, NULL, 6, NULL),
(14, 'Egreso', '2023-03-24 05:00:00', 123, NULL, NULL, 6, NULL),
(15, 'Egreso', '2023-03-24 05:00:00', 123, NULL, NULL, 6, NULL),
(16, 'Ingreso', '2023-03-27 05:00:00', 569, NULL, NULL, 6, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf32 COLLATE utf32_spanish2_ci NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish2_ci;

--
-- Truncar tablas antes de insertar `tags`
--

TRUNCATE TABLE `tags`;
--
-- Volcado de datos para la tabla `tags`
--

INSERT INTO `tags` (`id`, `nombre`, `activo`) VALUES
(1, 'Personales', 1),
(2, 'nueva pruebas', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `terceros`
--

DROP TABLE IF EXISTS `terceros`;
CREATE TABLE IF NOT EXISTS `terceros` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `numero_identificacion` char(15) COLLATE utf32_spanish2_ci NOT NULL,
  `nombre` varchar(100) CHARACTER SET utf32 COLLATE utf32_spanish2_ci NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `numero_identificacion` (`numero_identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish2_ci;

--
-- Truncar tablas antes de insertar `terceros`
--

TRUNCATE TABLE `terceros`;
--
-- Volcado de datos para la tabla `terceros`
--

INSERT INTO `terceros` (`id`, `numero_identificacion`, `nombre`, `activo`) VALUES
(2, '1216548', 'nombre s', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`id_catergoria`) REFERENCES `categorias` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `movimientos_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tags` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `movimientos_ibfk_3` FOREIGN KEY (`id_tercero`) REFERENCES `terceros` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `movimientos_ibfk_4` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
