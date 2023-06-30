-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-09-2022 a las 18:30:04
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_encomienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tcliente`
--

CREATE TABLE `tcliente` (
  `id` int(11) NOT NULL,
  `dni` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(180) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tcliente`
--

INSERT INTO `tcliente` (`id`, `dni`, `nombre`, `telefono`, `direccion`) VALUES
(1, '75626262', 'Jheyson Arone', '986734534', 'Lima - Perú'),
(2, '00471373', 'Enrique Chacon Rondon', '978372183', 'Cusco'),
(3, '80155953', 'David Huari Sullca', '978276526', 'AV.Grau 309'),
(4, '76523132', 'ADVANCED THEGNOLOGY', '', 'AV.Separadora Industrial'),
(5, '20418140551', 'ALBIS S.A', '978298722', 'Cusco-Cusco'),
(6, '20550856337', 'ALVILLANTAS E.I.R.L.', '', 'CAL.LECHUGAL NRO. 380');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tconductor`
--

CREATE TABLE `tconductor` (
  `id` int(11) NOT NULL,
  `dni` varchar(8) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(70) COLLATE utf8_spanish_ci NOT NULL,
  `licencia` varchar(70) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `vehiculo` varchar(11) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tconductor`
--

INSERT INTO `tconductor` (`id`, `dni`, `nombre`, `licencia`, `direccion`, `telefono`, `vehiculo`) VALUES
(1, '71717171', 'Little Harby', 'AB-1228233', 'Lima - Perú', '987283928', 'ABC-123'),
(2, '45064434', 'Alfredo Castañeda Quinta', 'Z-45064434', 'LIMA', '966548210', 'AJC-877'),
(3, '30493034', 'Adriel Meza Bazan', 'T-42531684', '', '999765876', 'XZA-917'),
(4, '43889481', 'Alfonzo Urgarte Chipa', 'Z-43572277', 'Huancayo', '', 'VGB-808'),
(5, '43565188', 'Alexander Velasques Soria', 'H-43565188', 'Santa Anita - Lima', '982738456', 'JHX-649');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tdetalle`
--

CREATE TABLE `tdetalle` (
  `id` int(11) NOT NULL,
  `id_pro` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_Factura` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tdetalle`
--

INSERT INTO `tdetalle` (`id`, `id_pro`, `cantidad`, `precio`, `id_Factura`) VALUES
(1, 1, 1, '11.00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tdetalleguia`
--

CREATE TABLE `tdetalleguia` (
  `id` int(11) NOT NULL,
  `Licencia` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombreConductor` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `Placa` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `Destino` varchar(60) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tdetalleguia`
--

INSERT INTO `tdetalleguia` (`id`, `Licencia`, `nombreConductor`, `Placa`, `Destino`) VALUES
(1, '1', 'Mike Harvey', '123', 'Lima');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tempresa`
--

CREATE TABLE `tempresa` (
  `id` int(11) NOT NULL,
  `ruc` int(15) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` int(11) NOT NULL,
  `direccion` text COLLATE utf8_spanish_ci NOT NULL,
  `mensaje` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tempresa`
--

INSERT INTO `tempresa` (`id`, `ruc`, `nombre`, `telefono`, `direccion`, `mensaje`) VALUES
(1, 101000201, 'Expreso Marvisur', 925491523, 'Lima - Perú', 'Toda carga a nivel nacional');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tfactura`
--

CREATE TABLE `tfactura` (
  `id` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `vendedor` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tfactura`
--

INSERT INTO `tfactura` (`id`, `cliente`, `vendedor`, `total`, `fecha`) VALUES
(1, 1, 'JHEYSON JHAIR', '11.00', '24/07/2022');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tproducto`
--

CREATE TABLE `tproducto` (
  `id` int(11) NOT NULL,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` text COLLATE utf8_spanish_ci NOT NULL,
  `proveedor` int(11) NOT NULL,
  `cliente` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `stock` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `volumen` decimal(10,2) NOT NULL,
  `peso` decimal(10,2) NOT NULL,
  `origen` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `destino` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tproducto`
--

INSERT INTO `tproducto` (`id`, `codigo`, `nombre`, `proveedor`, `cliente`, `stock`, `precio`, `volumen`, `peso`, `origen`, `destino`) VALUES
(1, '0001', 'Laptop Lenovo', 1, '75626262', 5, '11.00', '11.00', '11.00', 'ABANCAY', 'LIMA'),
(2, '0002', 'Caja peluches', 2, '00471373', 2, '30.00', '40.00', '0.15', 'ABANCAY', 'Lima');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tproveedor`
--

CREATE TABLE `tproveedor` (
  `id` int(11) NOT NULL,
  `ruc` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tproveedor`
--

INSERT INTO `tproveedor` (`id`, `ruc`, `nombre`, `telefono`, `direccion`) VALUES
(1, '01100110011', 'Open source', '98356786', 'Lima'),
(2, '01872839201', 'FedEx', '978276374', 'E.E.U.U'),
(3, '09028738291', 'MRW', '967234232', 'Andorra'),
(4, '76728376281', 'Seur', '986543234', 'Lima');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tusuario`
--

CREATE TABLE `tusuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `pass` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `rol` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tusuario`
--

INSERT INTO `tusuario` (`id`, `nombre`, `correo`, `pass`, `rol`) VALUES
(1, 'JHEYSON JHAIR', 'Jhair', 'jhaircito2003', 'Administrador'),
(2, 'JHAIR ASISTENTE', 'Asistente', 'jhaircito2003', 'Asistente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tvehiculo`
--

CREATE TABLE `tvehiculo` (
  `id` int(11) NOT NULL,
  `placa` varchar(8) COLLATE utf8_spanish_ci NOT NULL,
  `marca` varchar(180) COLLATE utf8_spanish_ci NOT NULL,
  `color` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `configuracion` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tvehiculo`
--

INSERT INTO `tvehiculo` (`id`, `placa`, `marca`, `color`, `configuracion`) VALUES
(1, 'ABC-123', 'Nissan', 'Blanco', 'C2RB1'),
(2, 'AJC-877', 'Hino', 'Blanco', 'T-3 S-3'),
(3, 'VGB-808', 'Scania', 'Rojo', 'C-3'),
(4, 'JHX-649', 'Kia Rios', 'Azul Plateado', 'C-2'),
(5, 'XZA-917', 'Volvo', 'Azul Blanco', 'C-3');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tcliente`
--
ALTER TABLE `tcliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tconductor`
--
ALTER TABLE `tconductor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tdetalle`
--
ALTER TABLE `tdetalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_venta` (`id_Factura`),
  ADD KEY `id_pro` (`id_pro`);

--
-- Indices de la tabla `tdetalleguia`
--
ALTER TABLE `tdetalleguia`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tempresa`
--
ALTER TABLE `tempresa`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tfactura`
--
ALTER TABLE `tfactura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente` (`cliente`);

--
-- Indices de la tabla `tproducto`
--
ALTER TABLE `tproducto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `proveedor` (`proveedor`);

--
-- Indices de la tabla `tproveedor`
--
ALTER TABLE `tproveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tusuario`
--
ALTER TABLE `tusuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tvehiculo`
--
ALTER TABLE `tvehiculo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tcliente`
--
ALTER TABLE `tcliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tconductor`
--
ALTER TABLE `tconductor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tdetalle`
--
ALTER TABLE `tdetalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tdetalleguia`
--
ALTER TABLE `tdetalleguia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tempresa`
--
ALTER TABLE `tempresa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tfactura`
--
ALTER TABLE `tfactura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tproducto`
--
ALTER TABLE `tproducto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tproveedor`
--
ALTER TABLE `tproveedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tusuario`
--
ALTER TABLE `tusuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tvehiculo`
--
ALTER TABLE `tvehiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tdetalle`
--
ALTER TABLE `tdetalle`
  ADD CONSTRAINT `detalle_ibfk_1` FOREIGN KEY (`id_pro`) REFERENCES `tproducto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_ibfk_2` FOREIGN KEY (`id_Factura`) REFERENCES `tfactura` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tfactura`
--
ALTER TABLE `tfactura`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `tcliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tproducto`
--
ALTER TABLE `tproducto`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`proveedor`) REFERENCES `tproveedor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
