-- ----------------------------createWeaponPlayerPack-----------------------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS `bj`.`createWeaponPlayerPack` $$
CREATE  PROCEDURE `createWeaponPlayerPack`(in cNum int,in seqNum int,in playerId int)
BEGIN
declare  pn int;
declare  sn int;
set pn=1;
while pn<=cNum do
  set sn=1;
  while sn<=seqNum do
    insert into PLAYER_PACK(PLAYER_ID,PACK_ID,SEQ,TYPE,SYS_CHARACTER_ID) values(playerId,1,sn,'W',pn);
  set sn=sn+1;
  end while;
  set pn =pn+1;
end while;
END $$
DELIMITER ;
-- ----------------------------createCostumePlayerPack-----------------------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS `bj`.`createCostumePlayerPack` $$
CREATE  PROCEDURE `createCostumePlayerPack`(in cNum int,in seqNum int,in playerId int)
BEGIN
declare  pn int;
declare  sn int;
set pn=1;
while pn<=cNum do
  set sn=1;
  while sn<=seqNum do
	insert into PLAYER_PACK(PLAYER_ID,PACK_ID,SEQ,TYPE,SYS_CHARACTER_ID) values(playerId,1,sn,'C',pn);
  set sn=sn+1;
  end while;
  set pn =pn+1;
end while;
END $$
DELIMITER ;
-- ----------------------------initDefaultPlayeritem-----------------------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS `bj`.`initWeaponPack` $$
CREATE  PROCEDURE `initWeaponPack`(in cNum int,in seqNum int,in playerId int)
BEGIN
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1132,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1129,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1136,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1132) where SYS_CHARACTER_ID=1 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1129 ) where SYS_CHARACTER_ID=1 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1136) where SYS_CHARACTER_ID=1 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";

	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1233,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1230,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1236,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1233 ) where SYS_CHARACTER_ID=2 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1230 ) where SYS_CHARACTER_ID=2 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1236 ) where SYS_CHARACTER_ID=2 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";
	
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1332,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1330,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1335,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1332 ) where SYS_CHARACTER_ID=3 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1330 ) where SYS_CHARACTER_ID=3 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1335 ) where SYS_CHARACTER_ID=3 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";

	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1432,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1430,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1435,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1438,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1432 ) where SYS_CHARACTER_ID=4 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";	
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1430) where SYS_CHARACTER_ID=4 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";	
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1435) where SYS_CHARACTER_ID=4 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";	
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1438 ) where SYS_CHARACTER_ID=4 and SEQ =4  and PLAYER_ID=playerId and TYPE="W";
	
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1532,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1531,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1535,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1532 ) where SYS_CHARACTER_ID=5 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";	
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1531) where SYS_CHARACTER_ID=5 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";	
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1535) where SYS_CHARACTER_ID=5 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";

END $$
DELIMITER ;
-- ----------------------------initCostumePack-----------------------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS `bj`.`initCostumePack` $$
CREATE  PROCEDURE `initCostumePack`(in cNum int,in seqNum int,in playerId int)
BEGIN

	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,2000,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3000,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3500,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=2000) where SYS_CHARACTER_ID=1 and SEQ =1  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3000 ) where SYS_CHARACTER_ID=1 and SEQ =2  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3500) where SYS_CHARACTER_ID=1 and SEQ =3  and PLAYER_ID=playerId and TYPE="C";
	
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,2103,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3100,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3510,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=2103) where SYS_CHARACTER_ID=2 and SEQ =1  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3100 ) where SYS_CHARACTER_ID=2 and SEQ =2  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3510) where SYS_CHARACTER_ID=2 and SEQ =3  and PLAYER_ID=playerId and TYPE="C";
	
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,2202,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3200,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3520,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=2202) where SYS_CHARACTER_ID=3 and SEQ =1  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3200 ) where SYS_CHARACTER_ID=3 and SEQ =2  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3520) where SYS_CHARACTER_ID=3 and SEQ =3  and PLAYER_ID=playerId and TYPE="C";
	
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,2301,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3301,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3530,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=2301) where SYS_CHARACTER_ID=4 and SEQ =1  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3301 ) where SYS_CHARACTER_ID=4 and SEQ =2  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3530) where SYS_CHARACTER_ID=4 and SEQ =3  and PLAYER_ID=playerId and TYPE="C";
	
	
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,2402,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3405,"0000000",now(),now(),"Y","Y",1,0);
	insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3540,"0000000",now(),now(),"Y","Y",1,0);
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=2402) where SYS_CHARACTER_ID=5 and SEQ =1  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3405 ) where SYS_CHARACTER_ID=5 and SEQ =2  and PLAYER_ID=playerId and TYPE="C";
	update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3540) where SYS_CHARACTER_ID=5 and SEQ =3  and PLAYER_ID=playerId and TYPE="C";
END $$
DELIMITER ;
-- ----------------------------createCharacterDate-----------------------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS `bj`.`createCharacterDate` $$
CREATE  PROCEDURE `createCharacterDate`(in playerId int)
BEGIN
declare  pn int;
	set pn=1;
	while pn<=10 do
	  insert into CHARACTER_DATA(PLAYER_ID,CHARACTER_ID) values(playerId,pn);
	  set pn =pn+1;
	end while;
	insert into CHARACTER_DATA(PLAYER_ID,CHARACTER_ID) values(playerId,120);
END $$
DELIMITER ;
-- -----------------------------------------------------
-- function genGmGroupCode
-- -----------------------------------------------------

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