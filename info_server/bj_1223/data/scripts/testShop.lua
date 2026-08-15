module("testShop", package.seeall)--包名，命名空间。要调用这个文件的方法必须使用testShop
--根据逻辑需求写以下测试用例
local tnum=2
local cidnum=5
local pnum=1
local cid=0
local curt=0
function testshop()
	--rpc.debug=2
	test(1,725,tnum,cidnum,pnum)
end




function test(uid,pid,tnum,cidnum,pnum)
	local c=1
	local p=1
	for t= 1,tnum do
		print("t"..t)
		for c=1,cidnum do
			print("c"..c)
			for p=1,pnum do
				print("p"..p)
				testOne(uid,pid,t,c,p)
				p=p+1
			end
			c=c+1
		end
		t=t+1
	end
end
function testOne(userid,playerid,types,characterid,page)
	curt=types
	cid=characterid
	rpc.safecall("shop_list", {uid= playeritemid,pid =playerid,t=types,cid=characterid,p=page,},callback)
end

function callback(data)
	if data.pages ~=0
	then 
		testBuy(1,725,data.items[1].sid,data.items[1].common.type,cid,data.items[1].gpprices[1].id,0)
		rpc.safecall("character_get", {pid=725,cid=cid})
		--testBuy(1,175,data.items[1].sid,data.items[1].common.type,cid,data.items[1].gpprices[1].id,1)
	end
	
end

function testBuy(userid,playerid,sysitemid,types,characterid,payid,ispack)
	rpc.safecall("shop_req_buy", {uid= userid,pid =playerid,sid=sysitemid,t=types,cid=characterid,costid=payid,packid=ispack},buycallback)
end
function buycallback(data)
	rpc.safecall("storage_list", {uid= 1,pid=725,t=curt,cid=cid,p=1})
	rpc.safecall("character_get", {pid=725,cid=cid})

end
