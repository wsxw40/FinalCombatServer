DELIMITER $$

DROP PROCEDURE IF EXISTS `bj`.`createPlayerPack` $$
CREATE  PROCEDURE `createPlayerPack`(in cNum int,in seqNum int,in playerId int)
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

insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,1,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,3,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,5,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,9,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,10,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,12,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,13,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,230,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,231,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,232,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,233,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,234,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,235,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,236,"0000000",now(),now(),"Y","Y",0,1);
insert into PLAYER_ITEM(PLAYER_ID,ITEM_ID,MODIFIED_DESC,VALID_TIME,EXPIRE_TIME,IS_BIND,IS_DEFAULT,CURRENCY,UNIT_TYPE) values(playerId,237,"0000000",now(),now(),"Y","Y",0,1);
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=12) where SYS_CHARACTER_ID=1 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=230) where SYS_CHARACTER_ID=1 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=5) where SYS_CHARACTER_ID=1 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";


update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=231 ) where SYS_CHARACTER_ID=2 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=232 ) where SYS_CHARACTER_ID=2 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=235 ) where SYS_CHARACTER_ID=2 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";

update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=9) where SYS_CHARACTER_ID=3 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=233) where SYS_CHARACTER_ID=3 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=234) where SYS_CHARACTER_ID=3 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";

update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=3 ) where SYS_CHARACTER_ID=4 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=10 ) where SYS_CHARACTER_ID=4 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=236 ) where SYS_CHARACTER_ID=4 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";
		
		
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=1 ) where SYS_CHARACTER_ID=5 and SEQ =1  and PLAYER_ID=playerId and TYPE="W";	
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=13 ) where SYS_CHARACTER_ID=5 and SEQ =2  and PLAYER_ID=playerId and TYPE="W";	
update PLAYER_PACK set PLAYER_ITEM_ID=(select ID from PLAYER_ITEM where  PLAYER_ID=playerId and ITEM_ID=237 ) where SYS_CHARACTER_ID=5 and SEQ =3  and PLAYER_ID=playerId and TYPE="W";	
		
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