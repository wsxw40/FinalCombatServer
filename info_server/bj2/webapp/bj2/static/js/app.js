var app = angular.module('app', [
    'ngRoute',
    'datatables',
    'app.controllers'
]);

app.run(['$rootScope', '$location', '$http', function ($rootScope, $location, $http) {
    $rootScope.data = {
        title: '',
        breadcrumb: []
    };

    $http.get('gm/user/get').success(function (data) {
//    	console.log(data[0]);
//        $rootScope.loginUser = data;
    });

    var menu = $('.sidebar-menu');
    $rootScope.$on('$routeChangeSuccess', function (event, current, last) {
        var path = $location.path();
        if (!path) {
            return;
        }
        var anchors = menu.find('li a[href="#' + path + '"]');
        if (!anchors.parent('li').hasClass('active')) {
            $rootScope.$applyAsync(function () {
                anchors.first().click();
            });
        }

        $rootScope.data.title = anchors.first().text();
        $rootScope.data.breadcrumb = [];
        var p = anchors.first().parents('li.treeview').children('a').first().text();
        if (p != null && p.length != 0) {
            $rootScope.data.breadcrumb.push(p);
        }
        $rootScope.data.breadcrumb.push($rootScope.data.title);
    });
}]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo: '/import'
        })
        .when('/:page*', {
            templateUrl: function ($routeParams) {
                return $('#app_root').val()+'/bj2/gm/views/' + $routeParams.page + '.jsp';
            },
            controller: function ($scope, $routeParams, $controller) {
                var controller = $routeParams.page.replace(/(?:^|\/)(\w)/g, function (m, c) {
                        return c.toUpperCase();
                    }) + 'Controller';
                $controller(controller, {$scope: $scope});
            }
            //templateUrl: 'views/dashboard.html',
            //controller: 'DashboardController'
        })
        .otherwise({
            redirectTo: '/dashboard'
        });
}]);


$.extend(!0, $.fn.dataTable.defaults, {
    searchDelay: 1000,
    oLanguage: {
        sProcessing: i18nWords.datatables.sProcessing,
        sLengthMenu: "_MENU_",
        sZeroRecords: i18nWords.datatables.sZeroRecords,
        sInfo: i18nWords.datatables.sInfo,
        sInfoEmpty: i18nWords.datatables.sInfoEmpty,
        sInfoFiltered: i18nWords.datatables.sInfoFiltered,
        sInfoPostFix: "",
        sSearch: "<div class='input-group-addon'><i class='glyphicon glyphicon-search'></i></div>",
        sUrl: "",
        sEmptyTable: i18nWords.datatables.sEmptyTable,
        sLoadingRecords: i18nWords.datatables.sLoadingRecords,
        sInfoThousands: ",",
        oPaginate: {
            sFirst : i18nWords.datatables.sFirst,
            sPrevious : i18nWords.datatables.sPrevious,
            sNext: i18nWords.datatables.sNext,
            sLast: i18nWords.datatables.sLast
        },
        oAria: {
            sSortAscending: i18nWords.datatables.sSortAscending,
            sSortDescending:  i18nWords.datatables.sSortDescending
        }
    },
    'fnRowCallback': function (row) {
        if ($.fn.dataTable.Responsive === undefined) {
            return;
        }
        var td = $('td:first-child', row);
        if (td === undefined) {
            return;
        }
        if ($('span.responsiveExpander', td).length == 0) {
            $(td).prepend('<span class="responsiveExpander"></span>');
        }
    }
});

$.extend(!0, $.fn.dataTable.Responsive.defaults.details, {
    type: 'column',
    target: 'td > span.responsiveExpander'
});

$(function () {
    var menu = '.sidebar';
    $("li a", $(menu)).on('click', function (e) {
        var $this = $(this);
        var checkElement = $this.next();

        if (!checkElement.is('.treeview-menu')) {
            var parent = $this.parents('ul').first();
            var ul = parent.find('ul:visible').slideUp($.AdminLTE.options.animationSpeed);
            parent.find('li.active').removeClass('active');

            $(menu).find('li.active').each(function () {
                if ($this.parents('li').filter(this).length == 0 && checkElement.children('li').filter(this).length == 0) {
                    $(this).removeClass('active');
                    $(this).parents('ul:visible').removeClass('menu-open');
                }
            });

            $this.parents('li.treeview').addClass('active');
            $this.parents('.treeview-menu').addClass('menu-open');
        }

        $this.parent().addClass('active');
    });
});

$.AdminLTE.options.animationSpeed = 'fast';

$(function(){
	if(localStorage['bodyClass']){
		$('body').addClass(localStorage['bodyClass']);
	}
	$(document).tooltip();
	$('#dialogAlert').dialog({
		autoOpen:false,modal:true,draggable:false,width:$(window).width()/3,
		close:function(){
			if((location.href+'').endsWith('gmResetPwd')){
				location.reload(true);
			}
		},
		buttons: [{
			text:i18nWords.button.yes,
			click:function(){$(this).dialog('close');}
		}]
	});
	$('#dialogRemove').dialog({
		autoOpen:false,modal:true,draggable:false,width:$(window).width()/3,
		buttons: [{
			text:i18nWords.button.yes,
			click:function(){
				$(this).dialog('close');
				location.href=$('#app_root').data('removeUrl');
			}
		},{
			text:i18nWords.button.no,
			click:function(){$(this).dialog('close');}
		}]
	});
	$('#dialogRestore').dialog({
		autoOpen:false,modal:true,draggable:false,width:$(window).width()/3,
		buttons: [{
			text:i18nWords.button.yes,
			click:function(){
				$(this).dialog('close');
				location.href=$('#app_root').data('restoreUrl');
			}
		},{
			text:i18nWords.button.no,
			click:function(){$(this).dialog('close');}
		}]
	});
});

$.fn.extend({
	pkg:function(){
		var form=[];
		$(this).find('[name]').each(function(){
			form[$(this).attr('name')]=$(this).val();
		});
		return form;
	},
	depkg:function(jsonObject,agScope){
		var a=false;
		console.log(typeof(a));
		for(var key in jsonObject){	
			if(typeof(jsonObject[key])=='boolean' && jsonObject[key]==false){
				agScope[key]=0;				
			}else if(typeof(jsonObject[key])=='boolean' && jsonObject[key]==true){
				agScope[key]=1;				
			}else{
				agScope[key]=jsonObject[key];				
			}
			$(this).find('p[ng-model="'+key+'"]').html(jsonObject[key]);
		}
	}
});

function getRowIndexSelected(tableId){
	var rowIndex=$($(tableId).find('tr[class*="active"]')).data('rowIndex');	
	if(undefined == rowIndex || null== rowIndex){
		$('#dialogAlert p').html(i18nWords.alert.selectOneRow);
		$('#dialogAlert').dialog('open');
		return -1;
	}
	return rowIndex;
}

function initTable(tableId,ajaxUrl,tableColumns,fixedColumns,subUrl,defaultOrder,specialSearch){
	var table=$(tableId).DataTable({
		scrollX:true,
		scrollY:$('.content-wrapper').height()*0.63,
//		scrollY:$(window).height()*0.55,
		scrollCollapse:true,
		stateSave:true,
		serverSide:true,
		searchable:false,
		lengthMenu: [[20,30,50,100,200,500,1000],[20,30,50,100,200,500,1000]],
		ajax:{
			url:ajaxUrl,
			type:'post',
			data:function(d){
				d.pId=localStorage['pId'];
				d.specialSearch=specialSearch;
				if(d.search && d.search.value){
					d.searchValue=d.search.value;
				}
				if(d.order && d.order.length>0){
					d.orderColumn=$($($(tableId+'_wrapper').find('th[property]')).get(d.order[0].column)).attr('property');
					if(tableId!='#tablePlayerItems'){
						d.orderColumn=d.orderColumn.replace(/[A-Z]/g,function(m){return '_'+m}).toUpperCase();	
					}
					d.orderDir=d.order[0].dir;
					
					d.orderColumns={},d.orderDirs={};
					for(var i in d.order){
						d.orderColumns[i]=$($($(tableId+'_wrapper').find('th[property]')).get(d.order[i].column)).attr('property');
						if(tableId!='#tablePlayerItems'){
							d.orderColumns[i]=d.orderColumns[i].replace(/[A-Z]/g,function(m){return '_'+m}).toUpperCase();	
						}
						d.orderDirs[i]=d.order[i].dir;
					}
				}
			}
		},
		columns:tableColumns,
		fixedColumns:fixedColumns,
		order:defaultOrder,
		rowCallback:function(row,data,rowIndex){
			$(row).data('rowIndex',rowIndex);
			$(row).find('td').click(function(){
				$(this).closest(tableId+'_wrapper').find('tr[class*="active"]').removeClass('active');
				$(this).closest(tableId+'_wrapper').find('table').each(function(){
					$($(this).find('tr').get(rowIndex+1)).addClass('active');
				});
			});
		}
	});

	$('#add').click(function(){
		localStorage.removeItem('formData');
		if(subUrl.add){
			location.href=subUrl.add;			
		}
	});
	$('#edit').click(function(){
		var rowIndex=getRowIndexSelected(tableId);
		if(rowIndex<0){
			return;
		}
		var data=table.data()[rowIndex];
		data.formId=$(tableId).attr('rowFormId');
		localStorage['formData']=JSON.stringify(data);
		if(subUrl.edit){
			location.href=subUrl.edit;
		}
	});
	$('#remove').click(function(){
		var rowIndex=getRowIndexSelected(tableId);
		if(rowIndex<0){
			return;
		}
		var data=table.data()[rowIndex];
		$('#app_root').data('removeUrl',subUrl.remove+'?id='+data.id+'&pId='+localStorage['pId']);
		$('#dialogRemove').dialog('open');
	});
	$('#restore').click(function(){
		var rowIndex=getRowIndexSelected(tableId);
		if(rowIndex<0){
			return;
		}
		var data=table.data()[rowIndex];
		$('#app_root').data('restoreUrl',subUrl.restore+'?id='+data.id+'&pId='+localStorage['pId']);
		$('#dialogRestore').dialog('open');
	});
	$('#delete').click(function(){
		var rowIndex=getRowIndexSelected(tableId);
		if(rowIndex<0){
			return;
		}
		var data=table.data()[rowIndex];
		$('#app_root').data('removeUrl',subUrl['delete']+'?id='+data.id);
		$('#dialogRemove').dialog('open');
	});
	$('#gear').click(function(){
		var rowIndex=getRowIndexSelected(tableId);
		if(rowIndex<0){
			return;
		}
		var data=table.data()[rowIndex];
		localStorage['pId']=data.id;
		localStorage['pName']=data.name;
		if(subUrl.gear){
			location.href=subUrl.gear;
		}		
	});
	$('#logTransactions').click(function(){
		var rowIndex=getRowIndexSelected(tableId);
		if(rowIndex<0){
			return;
		}
		var data=table.data()[rowIndex];
		localStorage['gmTransactionsSpecialSearch']=JSON.stringify({logId:data.id});
		if(subUrl.transactions){
			window.open(subUrl.transactions);
		}		
	});
	$('#modelTransactions').click(function(){
		var rowIndex=getRowIndexSelected(tableId);
		if(rowIndex<0){
			return;
		}
		var data=table.data()[rowIndex];
		localStorage['gmTransactionsSpecialSearch']=JSON.stringify({modelId:data.id,modelName:subUrl.modelName});
		if(subUrl.transactions){
			window.open(subUrl.transactions);
		}		
	});
	$('#rollBack').click(function(){
		var rowIndex=getRowIndexSelected(tableId);
		if(rowIndex<0){
			return;
		}
		var data=table.data()[rowIndex];
		if(subUrl.rollBack){
			$.post(subUrl.rollBack,{id:data.id},function(msg){
				alertDialog(msg);
			});
		}		
	});
	$('#resetPwd').click(function(){
		var rowIndex=getRowIndexSelected('#tableGmUsers');
		if(rowIndex<0){
			return;
		}
		$.post(subUrl.gear,{id:table.data()[rowIndex].id},function(msg){
			alertDialog(msg);
		});
	});
	return table;
}

function alertDialog(jsonStr){
	jsonStr=JSON.parse(jsonStr);
	if(jsonStr.error){
		$('#dialogAlert p').html(jsonStr.error);							
	}else{
		$('#dialogAlert p').html(jsonStr.msg);							
	}
	$('#dialogAlert').dialog('open');	
}

function initTableRowForm($scope,formId,parentUrl){
	$(formId).submit(function(){	
		$(this).find('input[type="number"][name!="id"]').each(function(){
			if($.trim($(this).val())==''){
				$(this).val(0);
			}
		});
	});
	$('#submit').click(function(){
		$(formId).submit();
	});
	$('#cancel').click(function(){
		location.href=parentUrl;
	});
	$('[name="id"]').attr('readonly','readonly');
	if(undefined!=localStorage['formData']){
		var data=JSON.parse(localStorage['formData']);
		if(data.formId!=formId){
			return;
		}
		$(formId).depkg(data,$scope);
		$('[name="u"]').val(1);
		return data;
	}
}

function saveBodyClass(){
	if($('body').attr('class').indexOf('sidebar-collapse')>0){
		localStorage.removeItem('bodyClass')
	}else{
		localStorage['bodyClass']='sidebar-collapse';		
	}
}