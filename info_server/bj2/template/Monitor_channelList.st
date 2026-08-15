<html>
<head></head>
<body>
Proxy List: 
<br/>
#foreach from=$channelList item=channel#
	&nbsp; &nbsp; &nbsp; &nbsp;#$channel.displayName#   <a href="?channelId=#$channel.channel.id#"> View </a>
	<br/>
#/foreach#
<br/>
------------------------------Info Server asyn taske queue size-------------------------------------------------------<br/>
#$asynQueueSize#<br/>		
-------------------------------Info Server Switch----------------------------------------------------<br/>	
<table>
	#foreach from=$switchs item=p#
		<tr>
			<td>#$switch.name#</td>   
			<td>&nbsp;&nbsp;&nbsp;&nbsp;   #$p.isOn#</td> 			
			<br/>
		</tr>
	#/foreach#		
</table>
-------------------------------Info Server Interface----------------------------------------------------
<table>
	<tr>
		<td>name</td> 
		<td> &nbsp; &nbsp;&nbsp;&nbsp;</td> 
		<td>average</td> 
		<td>maxCost</td> 
		<td>minCost</td> 
		<td>invokeCounts</td> 
		<td>memcacheHit</td>
		&nbsp; &nbsp;&nbsp;&nbsp;
		<td>memcacheMiss</td>
		&nbsp; &nbsp;&nbsp;&nbsp;
		<td>cacheHitRate</td>
	</tr>
	#foreach from=$datas item=data#
		<tr>
			<td>#$data.servletName#</td>
			<td> &nbsp; &nbsp;&nbsp;&nbsp;</td> 
			<td>#$data.averageCost#</td> 
			<td>#$data.maxCost#</td> 
			<td>#$data.minCost#</td> 
			<td>#$data.invokeCounts#</td>
			<td>#$data.memcacheHit#</td>
			&nbsp; &nbsp;&nbsp;&nbsp;
			<td>#$data.memcacheMiss#</td>
			&nbsp; &nbsp;&nbsp;&nbsp;
			<td>#$data.cacheHitRate#% </td>
		<tr/>
	#/foreach#
</table>
</body>
</html>