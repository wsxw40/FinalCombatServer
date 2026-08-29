cid = #$id#
#if ($erroMsg!=null)# 
	error = "#$erroMsg#" 
#else# 
	error=nil 
#/if#
#if ($warnMsg!=null)# 
	warning = "#$warnMsg#" 
#else# 
	warning=nil 
#/if#