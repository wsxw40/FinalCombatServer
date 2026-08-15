<html>
	<head></head>
	<body>
		
		-------------------------------Current Proxy :#$proxyName#----------------------------------------------------	
		
		<br/>
		<br/>
	
	        RequestQueue : #$reqQueueSize# <br/>
			ResponseQueue : #$resQueueSize# <br/>
		
		<table>
			<td>name</td> <td> &nbsp; &nbsp;&nbsp;&nbsp;</td> <td>active</td> <td>size</td> <td>queue</td> 
			<tr>
				 <td>clientPool<td/>
				 <td>#$clientActive#</td>
				 <td>#$clientMax#</td>
				 <td>#$clientTaskQueue#</td>
			</tr>
			<tr>
				 <td>playerPool<td/>
				 <td>#$playerActive#</td>
				 <td>#$playerMax#</td>
				 <td>#$playerTaskQueue#</td>
			</tr>
			<tr>
				 <td>loginPool<td/>
				 <td>#$loginActive#</td>
				 <td>#$loginMax#</td>
				 <td>#$loginTaskQueue#</td>
			</tr>
			<tr>
				 <td>serverPool<td/>
				 <td>#$serverActive#</td>
				 <td>#$serverMax#</td>
				 <td>#$serverTaskQueue#</td>
			</tr>
			<tr>
				 <td>gamePool<td/>
				 <td>#$gameActive#</td>
				 <td>#$gameMax#</td>
				 <td>#$gameTaskQueue#</td>
			</tr>
			
			
			
		</table>
		
		
		
		<br/>
		<br/> ======================================Proxy  info ============================================
		<br/> 
		
		<table>
			
			#foreach from=$proxyInfo item=item #
				<tr>
				#foreach from=$item item=im #
					<td width=100>#$im#</td>
				#/foreach#
				 <tr/>
			#/foreach#
		</table>
	</body>
</html>