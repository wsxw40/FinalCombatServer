weapon_info =
#if $sysItem!=null# 
{type=#$sysItem.wId#, #foreach from=$wpStable item=itemS##if($itemS!="")#"#$sysItem.name#/#$itemS#", #/if##/foreach# #foreach from=$wpChange item=itemC##if($itemC!="")#"#$sysItem.name#/#$itemC#", #/if##/foreach#}
#else#
nil
#/if#