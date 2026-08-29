USE `bj`;
DROP function IF EXISTS `bj`.`genGmGroupCode`;

DELIMITER $$
USE `bj`$$
CREATE FUNCTION `bj`.`genGmGroupCode` (param VARCHAR(40)) RETURNS varchar(40)
BEGIN
   DECLARE i INT DEFAULT 1;
    DECLARE num INT;
    DECLARE result VARCHAR(40);
    SET result = CONCAT(param ,'01');
    WHILE i > 0 DO
      SELECT COUNT(*) INTO num FROM GM_GROUP WHERE CODE = result;
      IF(num = 0) THEN
        SET i = 0;
      ELSE
        SET result = result + 1;
        IF((LENGTH(result) % 2) > 0) THEN
          SET result = CONCAT('0', result);
        END IF;
      END IF;
    END WHILE;
    RETURN result;
END $$

DELIMITER ;