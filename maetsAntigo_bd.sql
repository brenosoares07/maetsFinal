-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           12.2.2-MariaDB - MariaDB Server
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.14.0.7165
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para maets
DROP DATABASE IF EXISTS `maets`;
CREATE DATABASE IF NOT EXISTS `maets` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `maets`;

-- Copiando estrutura para tabela maets.auditoria
DROP TABLE IF EXISTS `auditoria`;
CREATE TABLE IF NOT EXISTS `auditoria` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `tabela_alterada` varchar(50) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `data_hora` datetime DEFAULT current_timestamp(),
  `acao_realizada` text DEFAULT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.auditoria: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela maets.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.cliente: ~1 rows (aproximadamente)
INSERT INTO `cliente` (`cod`, `nome`, `cpf`, `email`, `telefone`) VALUES
	(4, 'Valentina é linda', '67676767', 'anoes@gmail.com', '69696969696969');

-- Copiando estrutura para tabela maets.estoque
DROP TABLE IF EXISTS `estoque`;
CREATE TABLE IF NOT EXISTS `estoque` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `fk_cod_produto` int(11) NOT NULL,
  `quantidade_atual` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`cod`),
  KEY `fk_estoque_produto` (`fk_cod_produto`),
  CONSTRAINT `fk_estoque_produto` FOREIGN KEY (`fk_cod_produto`) REFERENCES `produto` (`cod`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.estoque: ~1 rows (aproximadamente)
INSERT INTO `estoque` (`cod`, `fk_cod_produto`, `quantidade_atual`) VALUES
	(1, 1, 30);

-- Copiando estrutura para tabela maets.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  `data_admissao` date DEFAULT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.funcionario: ~1 rows (aproximadamente)
INSERT INTO `funcionario` (`cod`, `nome`, `cargo`, `salario`, `data_admissao`) VALUES
	(2, 'Valentina maravilhosa a melhor funcionaria da vidah', 'Dona', 100000.00, '2008-01-04');

-- Copiando estrutura para tabela maets.itens_venda
DROP TABLE IF EXISTS `itens_venda`;
CREATE TABLE IF NOT EXISTS `itens_venda` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `fk_cod_venda` int(11) NOT NULL,
  `fk_cod_produto` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT 1,
  `valor_unitario` decimal(10,2) NOT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `fk_itens_produto` (`fk_cod_produto`),
  KEY `fk_itens_venda_pai` (`fk_cod_venda`),
  CONSTRAINT `fk_itens_produto` FOREIGN KEY (`fk_cod_produto`) REFERENCES `produto` (`cod`),
  CONSTRAINT `fk_itens_venda_pai` FOREIGN KEY (`fk_cod_venda`) REFERENCES `venda` (`cod`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.itens_venda: ~2 rows (aproximadamente)
INSERT INTO `itens_venda` (`cod`, `fk_cod_venda`, `fk_cod_produto`, `quantidade`, `valor_unitario`, `total`) VALUES
	(1, 1, 1, 30, 3000.00, 2999.99),
	(2, 1, 1, 5, 3000.00, 2999.99);

-- Copiando estrutura para tabela maets.pagamento
DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE IF NOT EXISTS `pagamento` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `metodo` varchar(50) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.pagamento: ~1 rows (aproximadamente)
INSERT INTO `pagamento` (`cod`, `metodo`) VALUES
	(1, 'por valentina');

-- Copiando estrutura para tabela maets.produto
DROP TABLE IF EXISTS `produto`;
CREATE TABLE IF NOT EXISTS `produto` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `preco_venda` decimal(10,2) NOT NULL,
  `fk_cod_tipo` int(11) NOT NULL,
  `fabricante` varchar(50) DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `fk_produto_tipo` (`fk_cod_tipo`),
  CONSTRAINT `fk_produto_tipo` FOREIGN KEY (`fk_cod_tipo`) REFERENCES `tipo` (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.produto: ~1 rows (aproximadamente)
INSERT INTO `produto` (`cod`, `nome`, `preco_venda`, `fk_cod_tipo`, `fabricante`, `genero`) VALUES
	(1, 'jogo da valentina ', 3000.00, 1, 'Crianças chinesas', 'jogos para meninas');

-- Copiando estrutura para tabela maets.servicos
DROP TABLE IF EXISTS `servicos`;
CREATE TABLE IF NOT EXISTS `servicos` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.servicos: ~1 rows (aproximadamente)
INSERT INTO `servicos` (`cod`, `descricao`, `valor`) VALUES
	(1, 'manutenção de console', 400.00);

-- Copiando estrutura para tabela maets.servicos_venda
DROP TABLE IF EXISTS `servicos_venda`;
CREATE TABLE IF NOT EXISTS `servicos_venda` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `fk_cod_venda` int(11) NOT NULL,
  `fk_cod_servico` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT 1,
  `valor_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `fk_servvenda_servico` (`fk_cod_servico`),
  KEY `fk_servvenda_venda` (`fk_cod_venda`),
  CONSTRAINT `fk_servvenda_servico` FOREIGN KEY (`fk_cod_servico`) REFERENCES `servicos` (`cod`),
  CONSTRAINT `fk_servvenda_venda` FOREIGN KEY (`fk_cod_venda`) REFERENCES `venda` (`cod`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.servicos_venda: ~0 rows (aproximadamente)
INSERT INTO `servicos_venda` (`cod`, `fk_cod_venda`, `fk_cod_servico`, `quantidade`, `valor_unitario`, `subtotal`) VALUES
	(1, 1, 1, 1, 400.00, 400.00);

-- Copiando estrutura para tabela maets.tipo
DROP TABLE IF EXISTS `tipo`;
CREATE TABLE IF NOT EXISTS `tipo` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.tipo: ~1 rows (aproximadamente)
INSERT INTO `tipo` (`cod`, `nome`) VALUES
	(1, 'Valentina é linda');

-- Copiando estrutura para tabela maets.venda
DROP TABLE IF EXISTS `venda`;
CREATE TABLE IF NOT EXISTS `venda` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `data_venda` datetime DEFAULT current_timestamp(),
  `fk_cod_cliente` int(11) DEFAULT NULL,
  `fk_cod_funcionario` int(11) DEFAULT NULL,
  `fk_cod_pagamento` int(11) DEFAULT NULL,
  `valor_total` decimal(10,2) DEFAULT 0.00,
  PRIMARY KEY (`cod`),
  KEY `fk_venda_cliente` (`fk_cod_cliente`),
  KEY `fk_venda_func` (`fk_cod_funcionario`),
  KEY `fk_venda_pagto` (`fk_cod_pagamento`),
  CONSTRAINT `fk_venda_cliente` FOREIGN KEY (`fk_cod_cliente`) REFERENCES `cliente` (`cod`),
  CONSTRAINT `fk_venda_func` FOREIGN KEY (`fk_cod_funcionario`) REFERENCES `funcionario` (`cod`),
  CONSTRAINT `fk_venda_pagto` FOREIGN KEY (`fk_cod_pagamento`) REFERENCES `pagamento` (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Copiando dados para a tabela maets.venda: ~1 rows (aproximadamente)
INSERT INTO `venda` (`cod`, `data_venda`, `fk_cod_cliente`, `fk_cod_funcionario`, `fk_cod_pagamento`, `valor_total`) VALUES
	(1, '2026-08-05 21:45:48', 4, 2, 1, 400.00);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
