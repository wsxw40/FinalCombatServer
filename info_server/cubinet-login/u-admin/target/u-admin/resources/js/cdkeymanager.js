/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-10-15
 * Time: 上午11:01
 * To change this template use File | Settings | File Templates.
 */
//common
"use strict";
var module = 'cdkey';
var cdkeyUrl = {
    loadDataUrl:[module , 'loadData'].join("/")
}


function formatCdkeyStatus(value) {
    return value ? '启用' : '禁用';
}

function createChannelcbg(/*string*/target) {
    var jqueryTarget = '#' + target;
    $(jqueryTarget).combogrid({
        idField:'channelId',
        textField:'game',
        width:150,
        panelWidth:300,
        rownumbers:true,
        required:true,
        onLoadSuccess:function (data) {
            $(jqueryTarget).combogrid('grid').datagrid('selectRow', 0);
        },
        columns:[
            [
//                {field:'gameName', title:'游戏名称', width:80},
//                {field:'gameService', title:'游戏区服', width:80},
                {field:'game', title:'游戏区服', width:120},
                {field:'status', title:'游戏区服状态', width:80, formatter:function (value, row, index) {
                    return formatCdkeyStatus(value);
                }}
            ]
        ]
    });
}

function createGameCdKeyTyptcbg(/*string*/target) {
    var jqueryTarget = '#' + target;
    $(jqueryTarget).combogrid({
        idField:'id',
        textField:'name',
        width:150,
        panelWidth:300,
        rownumbers:true,
        required:true,
        onLoadSuccess:function (data) {
            $(jqueryTarget).combogrid('grid').datagrid('selectRow', 0);
        },
        columns:[
            [
                {field:'name', title:'激活码用途', width:80},
                {field:'enable', title:'激活码用途状态', width:120, formatter:function (value, row, index) {
                    return formatCdkeyStatus(value);
                }}
            ]
        ]
    });
}

function gameCdKeyChannelcbgLoadData(/*string*/target, /*array or object*/loadData) {
    $('#' + target).combogrid('grid').datagrid('loadData', loadData);
}
function combogridLoadData(/*string*/target, /*array or object*/loadData) {
    $('#' + target).combogrid('grid').datagrid('loadData', loadData);
}
function datagridLoadData(/*string*/target, /*array or object*/loadData) {
    var jqueryTarget = $('#' + target);
    jqueryTarget.datagrid("loadData", loadData);
    jqueryTarget.datagrid("loading");
    setTimeout(function () {
        jqueryTarget.datagrid("loaded")
    }, 500);
}

//cdkey查询
"use strict";
$(function () {
    $('#cdkeyList').datagrid({
        pagination:true,
        pagePosition:'bottom',
        pageNumber:1,
        pageSize:100,
        pageList:[10, 100, 200, 300, 500, 1000],
        fitColumns:false,
        fit:true,
        remoteSort:false,
        singleSelect:true,
        rownumbers:true,
        url:['cdkey' , 'queryCdKeyByChannelAndGameCdKeyType'].join("/"),
        toolbar:'#cdkeyListtb',
        columns:[
            [
//                {field:'id', title:'id', width:120, align:'center'},
//                {field:'gameCdKeyChannelId', title:'gameCdKeyChannelId', width:120, align:'center'},
                {field:'userId', title:'userId', width:120, align:'center'},
                {field:'cdKey', title:'cdKey', width:120, align:'center'},
                {field:'expireDate', title:'expireDate', width:120, align:'center', formatter:function (value, row, index) {
                    return formatMSTime(value)
                }},
                {field:'enable', title:'enable', width:50, align:'center', formatter:function (value, row, index) {
                    return formatCdkeyStatus(value);
                }},
                {field:'query', title:'query', width:50, align:'center', formatter:function (value, row, index) {
                    return "<a href='javascript:queryCdKeyBy(\"" + row.cdKey + "\")'>查看</a>";
                }}
            ]
        ],
        loader:function (param, success, error) {
            var opts = $(this).datagrid("options");
            if (!param.channelId) {
                return false;
            }
            $.ajax({type:opts.method, url:opts.url, data:param, dataType:"json", success:function (data) {
                success(data);
            }, error:function () {
                error.apply(this, arguments);
            }});
        }
    });
});

$(function () {
    $('#cdkey_query_dg').datagrid({
        fitColumns:true,
        fit:true,
        remoteSort:false,
        singleSelect:true,
        toolbar:'#cdkey_query_dg_tb',
        columns:[
            [
                {field:'name', title:'名称', width:150, align:'right'},
                {field:'value', title:'值', width:200, align:'center', formatter:function (value, row, index) {
                    if (row.name == "过期时间") {
                        return '<input id="cdkey_query_expireDate"/>  ';
                    } else if (row.name == "激活码状态") {
                        return '<input id="cdkey_query_enable"/>';
                    } else if (row.name.indexOf("状态") != -1) {
                        return row.value ? "正常" : "关闭";
                    }
                    return value;
                }},
                {field:'operation', title:'操作', width:100, align:'center', formatter:function (value, row, index) {
                    if (row.name == "过期时间") {
                        return "<a href='javascript:void(0)'>调整过期时间</a>";
                    } else if (row.name == "激活码状态") {
                        return "<a href='javascript:void(0)'>调整状态</a>";
                    }
                }}
            ]
        ],
        onLoadSuccess:function (data) {
            $('#cdkey_query_expireDate').datetimebox({
                value:data.rows[7].value,
                required:true,
                showSeconds:false
            });
            $('#cdkey_query_enable').combobox({
                valueField:'value',
                textField:'text',
                value:data.rows[8].value,
                data:[
                    {
                        value:0,
                        text:'关闭'
                    },
                    {
                        value:1,
                        text:'正常'
                    }
                ]
            });
        }
    });
});

function queryCdKeyByGameCdKeyChannel() {
    if ($("#cdkeyList_channelcbg").combogrid("isValid") && $("#cdkeyList_gameCdKeyTyptcbg").combogrid("isValid")) {
        $('#cdkeyList').datagrid("load",
            {
                channelId:$("#cdkeyList_channelcbg").combogrid("getValue"),
                gameCdKeyTyptId:$("#cdkeyList_gameCdKeyTyptcbg").combogrid("getValue")
            }
        );
    }
}

function queryCdKeyAll() {
    $('#cdkeyList').datagrid("load", {id:0});
}

function queryCdKeyBy(cdkey) {
    $("#cdkeyvb").val(cdkey);
    queryCdKey();
}

function queryCdKey() {
    if ($("#cdkeyvb").validatebox("isValid")) {
        var cdkey = $("#cdkeyvb").val();
        $.getJSON(["cdkey", "query", cdkey].join("/"), function (jsondata) {
            if (jsondata.cdKey) {
                var loadData = [
                    {name:"游戏名称", value:jsondata.channel.gameName},
                    {name:"游戏区服", value:jsondata.channel.gameService},
                    {name:"区服状态", value:jsondata.channel.status},
                    {name:"激活码用途", value:jsondata.cdKeyType.name},
                    {name:"激活码用途状态", value:jsondata.cdKeyType.enable},
//                    {name:"激活码区服用途状态", value:jsondata.cdKeyChannel.enable},
                    {name:"激活者ID", value:jsondata.cdKey.userId},
                    {name:"激活者用户名", value:jsondata.user ? jsondata.user.userName : ''},
                    {name:"过期时间", value:formatMSTime(jsondata.cdKey.expireDate)},
                    {name:"激活码状态", value:jsondata.cdKey.enable}
                ];
                datagridLoadData("cdkey_query_dg", loadData);
            } else {
                $.messager.alert('cdkey不存在', cdkey + ':cdkey不存在', 'warning');
            }
        });
    }
}

//cdkey生成
var cdkey_make_tab = new (function () {
    var cdkey_make_dg;
    var gameCdKeyChannelcbg;
    var gameCdKeyTypecbg;
    var gameCdkeyMakeExpireDate;
    var gameCdkeyMakeNum;
    var gameCdkeyMakeEnable;

    $(function () {
        cdkey_make_dg = $('#cdkey_make_dg');
        gameCdKeyChannelcbg = $('#cdkey_make_channelcbg');
        gameCdKeyTypecbg = $('#cdkey_make_gameCdKeyTyptcbg');
        gameCdkeyMakeExpireDate = $('#gameCdkeyMakeExpireDate');
        gameCdkeyMakeNum = $('#gameCdkeyMakeNum');
        gameCdkeyMakeEnable = $('#gameCdkeyMakeEnable');

        cdkey_make_dg.datagrid({
            rownumbers:true,
            singleSelect:true,
            fit:true,
            toolbar:'#cdkey_make_dg_tb',
            columns:[
                [
                    {field:'cdKey', title:'cdkey', width:120},
                    {field:'expireDate', title:'过期时间', width:150, formatter:function (value, row, index) {
                        return formatMSTime(value);
                    }},
                    {field:'enable', title:'状态', width:80, formatter:function (value, row, index) {
                        return value ? "开放" : "关闭";
                    }}
//                    {field:'gameCdKeyChannelId', title:'区服用途', width:80}
                ]
            ]
        });
    });

    this.cdkeyMake = function () {
        if (gameCdkeyMakeExpireDate.datetimebox('isValid')) {
            var data = {
                channelId:gameCdKeyChannelcbg.combogrid('getValue'),
                gameCdKeyTypeId:gameCdKeyTypecbg.combogrid('getValue'),
                gameCdKeyType:gameCdKeyChannelcbg.combogrid('getValue'),
                expireDate:new Date(gameCdkeyMakeExpireDate.datetimebox('getValue')).getTime(),
                num:gameCdkeyMakeNum.val(),
                enable:gameCdkeyMakeEnable.attr('checked') ? 1 : 0
            }
            $.post(joinUrl(['cdkey' , 'gameCdKeyMake']), data, function (response) {
                cdkey_make_dg.datagrid('loadData', response);
            });
        }
    }
})();
//cdkey管理
var cdkey_manange_cdkeychannel_tab = new (function () {

    var ref = this;
    var targetId = "allCdKeyChanneldg";
    var target;
    $(function () {
        target = $('#' + targetId);
        target.datagrid({
            title:'cdkey游戏区服用途',
            rownumbers:true,
            singleSelect:true,
            fit:true,
            frozenColumns:[
                [
                    {title:'游戏名称', field:'gameName', width:150, align:'center'},
                    {title:'游戏区服', field:'gameService', width:100, align:'center'},
                    {title:'状态', field:'status', width:50, align:'center',
                        formatter:function (status, row, index) {
                            return status ? '开放' : '关闭';
                        },
                        editor:{
                            type:'checkbox',
                            options:{on:1, off:0}
                        }
                    }
                ]
            ],
            columns:[
                [
                    {title:'运营状态', field:'type', width:150, align:'center'},
                    {title:'是否需要CDKEY', field:'enable', width:150, align:'center'}
                ]
            ],
            onClickRow:onClickRow,
            toolbar:[
                {
                    text:'刷新',
                    iconCls:'icon-ok',
                    handler:function () {
                        reLoadData();
                    }
                },
                {
                    text:'保存',
                    iconCls:'icon-save',
                    handler:accept
                },
                {
                    text:'撤销',
                    iconCls:'icon-undo',
                    handler:reject
                }
            ]
        });
    });

    var editIndex = undefined;

    this.reLoadData = function (allChannel, allCdKeyTypeList) {
        editIndex = undefined;

        allCdKeyTypeList.push({id:'0', name:'未开放'});

        target.datagrid({
            columns:[
                [
                    {title:'运营状态', field:'type', width:150, align:'center',
                        formatter:function (value, row) {
                            return row.name;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'id',
                                textField:'name',
                                data:allCdKeyTypeList,
                                required:true
                            }
                        }
                    },
                    {title:'是否需要CDKEY', field:'enable', width:150, align:'center',
                        formatter:function (value, row) {
                            return value == null ? '未开放' : (value == '1' ? '需要' : '不需要');
                        },
                        editor:{
                            type:'checkbox',
                            options:{on:1, off:0}
                        }
                    }
                ]
            ]
        });

        datagridLoadData(targetId, allChannel);
    }

    function endEditing() {
        if (editIndex == undefined) {
            return true;
        }
        if (target.datagrid('validateRow', editIndex)) {
            var row = target.datagrid('getRows')[editIndex];
            var statusField = $(target.datagrid('getEditor', {index:editIndex, field:'status'}).target);
            var typeField = $(target.datagrid('getEditor', {index:editIndex, field:'type'}).target);
            var enableField = $(target.datagrid('getEditor', {index:editIndex, field:'enable'}).target);
            var name = typeField.combobox('getText');

            var id = row.channelId;
            var status = statusField.attr('checked') ? 1 : 0;
            var typeId = typeField.combobox('getValue');
            var enable = enableField.attr('checked') ? 1 : 0;

            var result = false;
            $.ajax({
                async:false,
                cache:false,
                type:'GET',
                dataType:'json',
                url:joinUrl(['cdkey', 'gameCdKeyChannelUpdate']),
                data:{id:id, gameCdKeyTypeId:typeId, enable:enable, status:status},
                success:function (data) {
                    result = data;
                    if (result) {
                        $.messager.show({
                            title:'数据更新',
                            msg:'更新成功',
                            timeout:1000,
                            showType:'show'
                        });

                        row.name = name;
                        row.status = status;
                        row.enable = enable;
                        target.datagrid('endEdit', editIndex);
                        editIndex = undefined;
                    } else {
                        $.messager.alert('数据更新', '更新失败,请重试!', 'error');
                    }
                },
                error:function (XMLHttpRequest, textStatus, errorThrown) {
                    $.messager.alert('数据更新', '更新失败,请重试!', 'error');
                }
            });
            return result;
        } else {
            return false;
        }
    }

    function onClickRow(index) {
        if (editIndex != index) {
            if (endEditing() && editIndex == undefined) {
                target.datagrid("selectRow", index).datagrid("beginEdit", index);
                editIndex = index;
            } else {
                target.datagrid("selectRow", editIndex);
            }
        }
    }

    function accept() {
        if (endEditing() && editIndex == undefined) {
            target.datagrid("acceptChanges");
        }
    }

    function reject() {
        target.datagrid("rejectChanges");
        editIndex = undefined;
    }

    function getChanges() {
        var rows = target.datagrid("getChanges");
        alert(rows.length + " rows are changed!");
    }
});

var cdkey_manange_cdkeytype_tab = new (function () {
    //namespace cdkey_manange_tab
    var ref = this;
    var targetId = "allCdKeyTypedg";
    var target;
    $(function () {
        target = $('#' + targetId);
        target.datagrid({
            fitColumns:true,
            fit:true,
            remoteSort:false,
            singleSelect:true,
            rownumbers:true,
            title:"cdkey用途",
//            url:['cdkey' , 'allCdKeyType'].join("/"),
            columns:[
                [
                    {field:'name', title:'name', width:100, align:'center', editor:{type:'validatebox', options:{required:true}}},
                    {field:'enable', title:'是否开放', width:50, align:'center', editor:{type:'checkbox', options:{on:1, off:0}}, formatter:function (value, row, index) {
                        if (value == 1) {
                            return "开放";
                        } else {
                            return "关闭";
                        }
                    }},
                    {field:'operation', title:'operation', width:50, align:'center', formatter:formatAction}
                ]
            ],
            onBeforeEdit:function (rowIndex, rowData) {
                rowData.editing = true;
                updateActions(rowIndex);
            },
            onAfterEdit:function (index, row) {
                row.editing = false;
                updateActions(index);
            },
            onCancelEdit:function (index, row) {
                row.editing = false;
                updateActions(index);
            },
            toolbar:[
                {
                    text:'创建',
                    iconCls:'icon-add',
                    handler:function () {
                        target.datagrid('insertRow', {
                            row:{
                                name:'',
                                enable:0
                            }
                        });
                        var rows = target.datagrid('getRows');
                        var insertIndex = rows.length - 1;
                        rows[insertIndex].adding = true;
                        ref.editrow(insertIndex);
                    }
                },
                '-',
                {
                    text:'刷新',
                    iconCls:'icon-reload',
                    handler:function () {
                        reLoadData();
//                        target.datagrid("reload");
                    }
                }
            ]
        });
    });

    this.editrow = function (index) {
        target.datagrid('beginEdit', index);
    }

    //edit save
    this.saverow = function (index) {
        var id = getRow(index).id;
        var name = $(target.datagrid('getEditor', {index:index, field:'name'}).target).val();
        var enable = $(target.datagrid('getEditor', {index:index, field:'enable'}).target).attr('checked') ? 1 : 0;
        $.post(joinUrl(['cdkey' , 'gameCdKeyTypeUpdate']), {id:id, name:name, enable:enable}, function (response) {
            if (response) {
                target.datagrid('endEdit', index);
                reLoadData();
            } else {
                $.messager.alert('cekey用途修改', '失败!', 'info');
            }
        });
    }

    this.cancelrow = function (index) {
        target.datagrid('cancelEdit', index);
    }

    this.deleterow = function (index) {
        operateRow(index, 'deleteRow');
    }

    //add save
    this.addrow = function (index) {
        var name = $(target.datagrid('getEditor', {index:index, field:'name'}).target).val();
        var enable = $(target.datagrid('getEditor', {index:index, field:'enable'}).target).attr('checked') ? 1 : 0;
        $.post(joinUrl(['cdkey' , 'gameCdKeyTypeInsert']), {name:name, enable:enable}, function (response) {
            if (response) {
                target.datagrid('endEdit', index);
                target.datagrid('updateRow', { index:index, row:response });
                reLoadData();
            } else {
                $.messager.alert('cekey用途添加', '失败!', 'info');
            }
        });
    }

    function getRow(index) {
        return target.datagrid('getRows')[index];
    }


    this.createCdKeyChannel = function (channel, cdKeyType, index, cdKeyTypeId) {
        $.post(joinUrl(['cdkey' , 'gameCdKeyChannelInsert']), {channelId:channel, gameCdKeyTypeId:cdKeyType}, function (response) {
            var row = {};
            row["status" + cdKeyTypeId] = response;
            row["operation" + cdKeyTypeId] = response;
            $('#allCdKeyChanneldg').datagrid('updateRow', { index:index, row:row});
        });
    }

    this.openCdKeyChannel = function (id, index, cdKeyTypeId) {
        $.post(joinUrl(['cdkey' , 'gameCdKeyChannelUpdate']), {id:id, enable:1}, function (response) {
            var row = $('#allCdKeyChanneldg').datagrid('getRows')[index];
            row["status" + cdKeyTypeId].enable = 1;
            row["operation" + cdKeyTypeId].enable = 1;
            $('#allCdKeyChanneldg').datagrid('updateRow', { index:index, row:row});
        });
    }

    this.closeCdKeyChannel = function (id, index, cdKeyTypeId) {
        $.post(joinUrl(['cdkey' , 'gameCdKeyChannelUpdate']), {id:id, enable:0}, function (response) {
            var row = $('#allCdKeyChanneldg').datagrid('getRows')[index];
            row["status" + cdKeyTypeId].enable = 0;
            row["operation" + cdKeyTypeId].enable = 0;
            $('#allCdKeyChanneldg').datagrid('updateRow', { index:index, row:row});
        });
    }

    var updateActions = function (index) {
        var row = $('#allCdKeyTypedg').datagrid('getRows')[index];
        row.operation = "";
        target.datagrid('updateRow', { index:index, row:row });
    }

    var operateRow = function (index, operate) {
        $('#allCdKeyTypedg').datagrid(operate, index);
    }

    var formatAction = function (value, row, index) {
        if (row.editing) {
            if (row.adding) {
                return '<a href="javascript:cdkey_manange_cdkeytype_tab.addrow(' + index + ')">Save</a> <a href="javascript:cdkey_manange_cdkeytype_tab.deleterow(' + index + ')">Cancel</a>';
            } else {
                return '<a href="javascript:cdkey_manange_cdkeytype_tab.saverow(' + index + ')">Save</a> <a href="javascript:cdkey_manange_cdkeytype_tab.cancelrow(' + index + ')">Cancel</a>';
            }
        } else {
            return '<a href="javascript:cdkey_manange_cdkeytype_tab.editrow(' + index + ')">Edit</a> ';
        }
    }
})();

/**
 * 加载区服列表信息
 */
$(function () {
    initUI();
    reLoadData();
});

function initUI() {
    createChannelcbg("cdkeyList_channelcbg");
    createChannelcbg("cdkey_make_channelcbg");

    createGameCdKeyTyptcbg("cdkeyList_gameCdKeyTyptcbg")
    createGameCdKeyTyptcbg("cdkey_make_gameCdKeyTyptcbg")
}
function reLoadData() {
    $.getJSON(['cdkey' , 'loadData'].join("/"), function (data) {
        var allChannel = data.allChannel;
        var allCdKeyTypeMap = data.allCdKeyTypeMap;
        var allCdKeyChannelMap = data.allCdKeyChannelMap;

        var allCdKeyTypeList = [];

        $.each(allCdKeyTypeMap, function (key, value) {
            allCdKeyTypeList.push(value)
        });

        $.each(allChannel, function (key, channel) {
            var gameCdKeyChannel = allCdKeyChannelMap[channel.channelId];
            var gameCdKeyType = gameCdKeyChannel ? allCdKeyTypeMap[gameCdKeyChannel.gameCdKeyTypeId] : null;

            channel.game = channel.gameName + '(' + channel.gameNumber + ')';
            channel.type = gameCdKeyType ? gameCdKeyType.id : '0';
            channel.name = gameCdKeyType ? gameCdKeyType.name : '未开放';
            channel.enable = gameCdKeyChannel ? gameCdKeyChannel.enable : null;
        });

        combogridLoadData("cdkeyList_channelcbg", allChannel);
        combogridLoadData("cdkey_make_channelcbg", allChannel);
        combogridLoadData("cdkeyList_gameCdKeyTyptcbg", allCdKeyTypeList);
        combogridLoadData("cdkey_make_gameCdKeyTyptcbg", allCdKeyTypeList);

        datagridLoadData("allCdKeyTypedg", allCdKeyTypeList);

        cdkey_manange_cdkeychannel_tab.reLoadData(allChannel, allCdKeyTypeList);
    })
}