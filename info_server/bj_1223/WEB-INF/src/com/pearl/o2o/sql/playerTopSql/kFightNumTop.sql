select
			p.ID,
			CONCAT("\\\"",p.NAME,"\\\""),
			p.MAX_FIGHT_NUM ,
      		p.IS_VIP,
			p.RANK,
			p.GENERAL_KILL,
			p.GENERAL_DEAD,
			p.GENERAL_ASSIST,
			p.GENERAL_WIN,
			p.GENERAL_LOSE,
    		p.EXP,p.ID,CONCAT("\\\"",p.ICON,"\\\"")
		from PLAYER p
		where p.IS_DELETED='N'  order by p.MAX_FIGHT_NUM desc,p.NAME desc limit 0,10000