/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-8-30
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */
"use strict";

function joinUrl(arg){
    return arg.join('/');
}
function formatByteUnit(value, row, index) {
    var unit = ['B', 'KB', 'MB', 'GB', 'TB'];
    for (var i = 0; i < unit.length; i++) {
        if (value < 1024) {
            return value.toFixed(0) + ' ' + unit[i];
        }
        value /= 1024;
    }
}
/* 把 耗时(s) 进行格式化 */
function formatTimeUnit(value, row, index) {
    var oneD = 60 * 60 * 24;
    var oneH = 60 * 60;
    var oneM = 60;
    var d = value / oneD;
    var h = value % oneD / oneH;
    var m = value % oneH / oneM;
    var s = value % oneM;
    return d.toFixed(0) + '天' + h.toFixed(0) + '小时' + m.toFixed(0) + '分钟' + s + '秒';
}
/* 将unix时间(s) 格式化 */
function formatUnixTime(value, row, index){
    return formatMSTime(new Number(value)*1000);
}

/* 将unix时间(s) 格式化 */
function formatMSTime(value){
    var date = new Date(new Number(value));
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.toLocaleTimeString();
}
function getRadiusDiv(color) {
    var s = '<div style="width:100%;">';
    s += '<div style="width:100%;float: left;"><div style="margin:0 auto;width:16px;height:16px;border-radius:8px;background:'
        + color + ';"></div></div>';
    s += '</div>';
    return s;
}

function state_formatter(value) {
    var color;
    switch (value) {
        case true:
            color = "blue";
            break;
        case false:
            color = "red";
            break;
    }
    if (color) {
        return getRadiusDiv(color);
    }
    return "";
}