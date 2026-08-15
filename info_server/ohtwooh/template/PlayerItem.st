weapon_info =
#if $playerItem!=null# 
{type=#$playerItem.wId#, #foreach from=$wpStable item=itemS# #if($itemS!="")#"#$playerItem.name#/#$itemS#", #/if# #/foreach# #foreach from=$wpChange item=itemC##if($itemC!="")#"#$playerItem.name#/#$itemC#", #/if##/foreach#}
#else#
nil
#/if#