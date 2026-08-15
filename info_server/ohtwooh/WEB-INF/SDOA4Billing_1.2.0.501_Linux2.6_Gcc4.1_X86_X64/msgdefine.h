//消息类型定义
#ifndef MSGDEFINE_H
#define MSGDEFINE_H


/*--------------------------------时长认证------------------------------*/
#define  AUTHORREQUEST								0X1
#define  AUTHORRESPONSE								0X80000001

/*---------------------------------扣费---------------------------------*/
#define  ACCOUNTREQUEST								0X2
#define  ACCOUNTRESPONSE							0X80000002

/*-------------------------------账户认证-------------------------------*/
#define  ACCOUNTAUTHENREQUEST						0X3
#define  ACCOUNTAUTHENRESPONSE						0X80000003

/*---------------------------------预冻---------------------------------*/
#define  ACCOUNTLOCKREQUEST							0X4
#define  ACCOUNTLOCKRESPONSE						0X80000004

/*---------------------------------解冻---------------------------------*/
#define  ACCOUNTUNLOCKREQUEST						0X5
#define  ACCOUNTUNLOCKRESPONSE						0X80000005

/*---------------------------------寄售转帐-----------------------------*/
#define  CONSIGNTRANSFERREQUEST						0X6
#define  CONSIGNTRANSFERRESPONSE					0X80000006

/*---------------------------------预冻扩展-----------------------------*/
#define  ACCOUNTLOCKEXREQUEST						0X7
#define  ACCOUNTLOCKEXRESPONSE						0X80000007

/*---------------------------------解冻扩展-----------------------------*/
#define  ACCOUNTUNLOCKEXREQUEST						0X8
#define  ACCOUNTUNLOCKEXRESPONSE					0X80000008

/*---------------------------------领奖---------------------------------*/
/*-------------------------------领奖认证-------------------------------*/
#define  AWARDAUTHENREQUEST							0X9
#define  AWARDAUTHENRESPONSE						0X80000009


/*-------------------------------领奖确认-------------------------------*/
#define  AWARDACKREQUEST							0X10
#define  AWARDACKRESPONSE							0X80000010

/*-------------------------------抵用券交易-----------------------------*/
#define  TOKENDEPOSITREQUEST						0X11
#define  TOKENDEPOSITRESPONSE						0X80000011

/*-------------------------------元宝换时间-----------------------------*/
#define  GOLDCONSUMELOCKREQUEST						0X12
#define  GOLDCONSUMELOCKRESPONSE					0X80000012

#define  GOLDCONSUMEUNLOCKREQUEST					0X13
#define  GOLDCONSUMEUNLOCKRESPONSE					0X80000013

/*-------------------------------发货系统-------------------------------*/
#define  ITEMSYNCREQUEST							0X14
#define  ITEMSYNCRESPONSE							0X80000014

/*-------------------------------货币兑换-------------------------------*/
#define  ACCOUNTEXCHANGELOCKREQUEST					0X15
#define  ACCOUNTEXCHANGELOCKRESPONSE				0X80000015

#define  ACCOUNTEXCHANGEUNLOCKREQUEST				0X16
#define  ACCOUNTEXCHANGEUNLOCKRESPONSE				0X80000016

/*------------------------------密宝绑定--------------------------------*/
#define  EKEYBINDREQUEST							0X17
#define  EKEYBINDRESPONSE							0X80000017

/*--------------------NotifyS2CReqDef----------------------------------*/
#define  NOTIFYS2CREQUEST							0X18
#define  NOTIFYS2CRESPONSE							0X80000018

//------------------NotifyC2SReqDef------------------------------------*/
#define  NOTIFYC2SREQUEST							0X19
#define  NOTIFYC2SRESPONSE							0X80000019



#endif