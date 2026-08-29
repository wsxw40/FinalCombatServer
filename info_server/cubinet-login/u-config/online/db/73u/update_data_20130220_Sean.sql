-- 更新 partner_id
update PARTNER set PARTNER_ID_V='fc-cubinet-ph' where ID_B=1;

-- 删除多余的测试数据
delete from PARTNER where ID_B>1;