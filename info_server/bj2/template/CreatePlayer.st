cid = #$id#
#if ($erroMsg!=null)# 
	error = "#$erroMsg#" 
#else# 
	error={}
#/if#
#if ($warnMsg!=null)# 
	warning = "#$warnMsg#" 
#else# 
	warning={} 
#/if#