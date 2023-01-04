
-- Run using MySQL command
CREATE SCHEMA `bank`;

/* ********************************************************************************************************* */
/* ********************************************************************************************************* */

CREATE TABLE `cash_out_report` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `year` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cash_out_report_year_UQ` (`year`)
);

CREATE TABLE `monthly_spending` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `master_id` int unsigned NOT NULL,
  `amount` float unsigned NOT NULL,
  `month` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`master_id`) REFERENCES `cash_out_report` (`id`)
);