/*
* Copyright (c) 2009,盛大在线 计费平台中心
* All rights reserved.
* 
* 文件名称：sdoa4billing.h
* 摘    要：各接口函数定义
* 当前版本：2.0
* 作    者：SNDA
* 完成日期：2009年11月03日
*
*/

#ifndef SDOA4BILLING_H
#define SDOA4BILLING_H

#include "sndabase.h"

interface ISDOAMsg : ISDUnknown 
{
	//----------------------------------------------------------------------------------------
	//名称：GetValue
	//描述：获取指定消息内指定字段的值
	//参数：
	//	[in]szKey：         数据关键字
	//返回：                关键字对应的内容，没有该关键字返回NULL
	//----------------------------------------------------------------------------------------
	SNDAMETHOD(const char*)	GetValue(const char* szKey) PURE;

	//----------------------------------------------------------------------------------------
	//名称：SetValue
	//描述：给指定消息添加指定字段的值
	//参数：
	//	[in]szKey：         数据关键字
	//	[in]szValue：       数据值
	//返回：                0成功，其他失败（一般为数据长度溢出）
	//----------------------------------------------------------------------------------------
	SNDAMETHOD(int)	SetValue(const char* szKey, const char* szValue) PURE;
};
SNDAAPI(ISDOAMsg*) sdoaCreateMsg(void);

//---------------------------------------------------------------------------------------
//名称：BillingCallback
//描述：订单信息回调处理函数，由sdk使用方实现，收到消息后需要调用SendResponse发送应答
//参数：
//	nMsgType：              消息类型：用以区分收到的应答消息类型
//	pResponse：             应答消息：应答消息内容，需要通过getValue方法获取指定值
//返回：                    无
//----------------------------------------------------------------------------------------
typedef int (SNDACALLBACK *BILLINGCALLBACKFUN)(int nMsgType, ISDOAMsg* pResponse);

interface ISDOBillingHandler : ISDUnknown
{
	//----------------------------------------------------------------------------------------
	//名称：Initialize
	//描述：初始化计费客户端
	//参数：
	//	[in]szConfigFile：  配置文件，如果为NULL则使用缺省的sdoa4server.ini配置文件
	//	[in]pCallbackFunc： 消息处理回调函数
	//返回：                0 成功；!0 失败；
	//----------------------------------------------------------------------------------------
	 SNDAMETHOD(int) Initialize(const char *szConfigFile, BILLINGCALLBACKFUN callbackFun) PURE;
	//----------------------------------------------------------------------------------------
	//名称：Uninitialize
	//描述：计费客户端下载
	//参数：
	//	无
	//返回：                0 成功；无其他返回失败；
	//----------------------------------------------------------------------------------------
	 SNDAMETHOD(int) Uninitialize() PURE;	
	 //----------------------------------------------------------------------------------------
	//名称：SendRequest
	//描述：发送订单请求
	//参数：
	//	无
	//返回：                > 0 成功；
	//						< 0失败；
	//----------------------------------------------------------------------------------------
	 SNDAMETHOD(int) SendRequest(int nMsgType, const ISDOAMsg* req) PURE;
	//----------------------------------------------------------------------------------------
	//名称：GetUniqueId
	//描述：获取唯一ID，szId 需要33字节空间,用于生成订单号
	//参数：
	//	[in/out]szId            数据关键字
	//  返回：                  0成功 其他失败
	//----------------------------------------------------------------------------------------
	 SNDAMETHOD(int) GetUniqueId(char *szId) PURE;
	//----------------------------------------------------------------------------------------
	//名称：GetUniqueIdByParam
	//描述：通过输入区组获取唯一ID，szId 需要33字节空间,用于生成订单号
	//参数：
	//	[in]nAreaId             游戏区ID
	//	[in]nGroupId            游戏组ID
	//	[in/out]szId            数据关键字
	//  返回：                  0成功 其他失败
	//----------------------------------------------------------------------------------------
	 SNDAMETHOD(int) GetUniqueIdByParam(int nAreaId,int nGroupId, char *szId) PURE;
};
SNDAAPI(ISDOBillingHandler*) sdoaCreateBillingHandler();

//---------------------------------------------------------------------------------------
//名称：UserInfoCallback
//描述：认证信息回调处理函数，由sdk使用方实现
//参数：
//	[out]nResult：          返回消息结果(具体错误类型说明参见相关错误文档)
//	[out]nUniqueId：        关联ID
//	[in/out]pMsg：          消息解析设置接口
//返回：无
//----------------------------------------------------------------------------------------
typedef int (SNDACALLBACK *UserInfoCallback)(int nResult, unsigned long nUniqueId, ISDOAMsg *pMsg);

interface ISDOAUserInfoAuthen : ISDUnknown
{
	//----------------------------------------------------------------------------------------
	//名称：Initialize
	//描述：初始化
	//参数：
	//	[in]szConfPath：    配置文件，如果为NULL则使用缺省的ConfClt.conf配置文件
	//	[in]pCallbackFunc： 回调函数地址
	//返回：                0 成功；!0 失败；
	//----------------------------------------------------------------------------------------
	SNDAMETHOD(int) Initialize(const char *szConfPath, UserInfoCallback pCallbackFunc) = 0;

	//----------------------------------------------------------------------------------------
	//名称：AsyncGetUserInfo
	//描述：异步获取认证结果(通过Initialize设置回调函数)
	//参数：
	//	[in]szSessionId：   需要验证的Token或者SessionId
	//	[in]szEndpointIp：  客户端IP
	//	[in]nUniqueId：     token唯一id
	//返回：                0 成功；!0 失败；
	//----------------------------------------------------------------------------------------
	SNDAMETHOD(int) AsyncGetUserInfo(const char *szSessionId, const char *szEndpointIp, const unsigned long nUniqueId) = 0;

	//----------------------------------------------------------------------------------------
	//名称：SyncGetUserInfo
	//描述：同步获取认证结果
	//参数：
	//	[in]szSessionId：   需要验证的Token或者SessionId
	//	[in]szEndpointIp：  客户端IP
	//	[in]nUniqueId：     token唯一id
	//	[in/out]pMsg：      返回的用户信息		
	//返回：                0 成功；!0 失败；
	//----------------------------------------------------------------------------------------
	SNDAMETHOD(int) SyncGetUserInfo(const char *szSessionId, const char *szEndpointIp, const unsigned long nUniqueId, ISDOAMsg* pMsg) = 0;

};
SNDAAPI(ISDOAUserInfoAuthen*) sdoaCreateUserInfoAuthen(void);

#endif
