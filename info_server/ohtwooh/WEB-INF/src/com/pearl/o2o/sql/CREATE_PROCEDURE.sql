DELIMITER $$

DROP PROCEDURE IF EXISTS `ohtwooh`.`createItemMode` $$
CREATE  PROCEDURE `createItemMode`(in parent_item_id int,in num int,in child_items VARCHAR(10000),in seqs VARCHAR(10000),in modifiedDesc VARCHAR(45))
BEGIN
DECLARE i INT(8) DEFAULT 0;
delete from ITEM_MOD where ITEM_MOD.PARENT_ITEM_ID=parent_item_id;
if num <>0 then
  REPEAT SET i = i + 1;
  INSERT INTO ITEM_MOD VALUES (SUBSTRING_INDEX(SUBSTRING_INDEX(child_items, ',', i), ',', -1),parent_item_id, SUBSTRING_INDEX(SUBSTRING_INDEX(seqs, ',', i), ',', -1));
  UNTIL i >= num
  END REPEAT;
end if;
update PLAYER_ITEM set MODIFIED_DESC=modifiedDesc where ID=parent_item_id;
END $$

DELIMITER ;



DELIMITER $$

DROP PROCEDURE IF EXISTS `ohtwooh`.`createPlayerPack` $$
CREATE  PROCEDURE `createPlayerPack`(in packNum int,in seqNum int,in cPackNum int,in cSeqNum int,in playerId int,in userId int)
BEGIN
declare  pn int;
declare  sn int;
declare  cpn int;
declare  csn int;
declare  buff int;
set pn=1;
while pn<=packNum do
  set sn=1;
  while sn<=seqNum do
    insert into PLAYER_PACK(USER_ID,PLAYER_ID,PACK_ID,SEQ,TYPE) values(userId,playerId,pn,sn,'W');
  set sn=sn+1;
  end while;
  set pn =pn+1;
end while;
insert into PLAYER_ITEM(USER_ID,PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(userId,playerId,1,"111111111111",now(),now(),"Y","Y",0,2);
insert into PLAYER_ITEM(USER_ID,PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(userId,playerId,4,"000001000100",now(),now(),"Y","Y",0,2);
insert into PLAYER_ITEM(USER_ID,PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(userId,playerId,5,"",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(USER_ID,PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(userId,playerId,6,"",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(USER_ID,PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(userId,playerId,5,"",now(),now(),"Y","Y",0,1);

insert into PLAYER_ITEM(USER_ID,PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(userId,playerId,5,"",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(USER_ID,PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(userId,playerId,5,"",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(USER_ID,PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(userId,playerId,5,"",now(),now(),"Y","Y",0,1);

update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where USER_ID=userId and PLAYER_ID=playerId and ITEM_ID=1) where PACK_ID=1 and SEQ =1 and USER_ID=userId and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where USER_ID=userId and PLAYER_ID=playerId and ITEM_ID=4) where PACK_ID=1 and SEQ =2 and USER_ID=userId and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where USER_ID=userId and PLAYER_ID=playerId and ITEM_ID=5 limit 0,1) where PACK_ID=1 and SEQ =3 and USER_ID=userId and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where USER_ID=userId and PLAYER_ID=playerId and ITEM_ID=6) where PACK_ID=1 and SEQ =4 and USER_ID=userId and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where USER_ID=userId and PLAYER_ID=playerId and ITEM_ID=5 limit 1,1) where PACK_ID=2 and SEQ =3 and USER_ID=userId and PLAYER_ID=playerId and TYPE="W";

update PLAYER_PACK set 
			PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where USER_ID=userId and PLAYER_ID=playerId and ITEM_ID=5 limit 2,1)
		where PACK_ID=3 and SEQ =3 and USER_ID=userId and PLAYER_ID=playerId and TYPE="W";

update PLAYER_PACK set 
			PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where USER_ID=userId and PLAYER_ID=playerId and ITEM_ID=5 limit 3,1)
		where PACK_ID=4 and SEQ =3 and USER_ID=userId and PLAYER_ID=playerId and TYPE="W";
		
		
update PLAYER_PACK set 
			PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where USER_ID=userId and PLAYER_ID=playerId and ITEM_ID=5 limit 4,1)
		where PACK_ID=5 and SEQ =3 and USER_ID=userId and PLAYER_ID=playerId and TYPE="W";

update PLAYER_PACK set 
			EXPIRE_TIME = now() where USER_ID=userId and PLAYER_ID=playerId and PACK_ID !=1 and PACK_ID !=2 ;
		
		
set cpn=1;
while cpn<=cPackNum do
  set csn=1;
  while csn<=cSeqNum do
    insert into PLAYER_PACK(USER_ID,PLAYER_ID,PACK_ID,SEQ,TYPE,SIDE) values(userId,playerId,cpn,csn,'C',0);
	  insert into PLAYER_PACK(USER_ID,PLAYER_ID,PACK_ID,SEQ,TYPE,SIDE) values(userId,playerId,cpn,csn,'C',1);
  set csn=csn+1;
  end while;
  set cpn =cpn+1;
end while;
set buff =1;
while buff <=11 do
	insert into PLAYER_BUFF(USER_ID,PLAYER_ID,BUFF_ID) values(userId,playerId,buff);
	set buff=buff+1;
end while;
END $$

DELIMITER ;