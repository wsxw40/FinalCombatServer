select
			p.ID,
			CONCAT("\\\"",p.NAME,"\\\""),
			p.WEEK_SCORE ,
      		p.IS_VIP,
			p.RANK,
			p.WEEK_KILL,
			p.WEEK_DEAD,
			p.WEEK_ASSIST,
			p.WEEK_WIN,
			p.WEEK_TOTAL,
    		p.EXP,p.ID,CONCAT("\\\"",p.ICON,"\\\"")
		from PLAYER p
		where p.IS_DELETED='N'  order by p.WEEK_SCORE desc,p.NAME desc limit 0,10000;