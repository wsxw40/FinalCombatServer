rid = #$stageClear.rid#
type = #$stageClear.type#
winner = #$stageClear.winner#
terrorist_score = #$stageClear.terrorist_score#
police_score = #$stageClear.police_score#
ace = "#$stageClear.ace#"
mvp = "#$stageClear.mvp#"
last_shot = "#$stageClear.last_shot#"
blast_succeed_king = "#$stageClear.blast_succeed_king_name#"
blast_disable_king = "#$stageClear.blast_disable_king_name#"
flag_succeed_king = "#$stageClear.flag_succeed_king_name#"
flag_return_king = "#$stageClear.flag_return_king_name#"
bio_zombie_lead = "#$stageClear.bio_zombie_lead_name#"
bio_hunter = "#$stageClear.bio_hunter_name#"
players = {
	#foreach from=$stageClear.stageClearPlayerInfo item=playerInfo#{#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#, #$playerInfo.rank#,#$playerInfo.curr_exp#, #$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.hit_point#, #$playerInfo.kill#, #$playerInfo.dead#, #$playerInfo.head_shot#, #$playerInfo.grenade_kill#, #$playerInfo.knife_kill#, #$playerInfo.drop_kill#, #$playerInfo.bio_infect#, #$playerInfo.bio_as_zombie_win#, #$playerInfo.bio_as_soldier_win#, #$playerInfo.bio_level#, 
	 #$playerInfo.bio_max_infect#, #$playerInfo.flag_capture#, #$playerInfo.flag_return#, #$playerInfo.flag_succeed#, #$playerInfo.blast_plant#, #$playerInfo.blast_disable#, #$playerInfo.blast_succeed#, #$playerInfo.fun_grenade_kill#,#$playerInfo.bio_infected#,},#/foreach#
}
