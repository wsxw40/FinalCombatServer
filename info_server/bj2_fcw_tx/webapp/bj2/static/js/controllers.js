angular.module('app.controllers', [])
	
	.controller('ImportController', ['$scope', function ($scope) {
	
	}])
	
	.controller('ExportController', ['$scope', function ($scope) {
		initExportController($scope);
	}])

	.controller('PlayersController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initPlayersController($scope);
	}])
        
	.controller('PlayerController', ['$scope', function ($scope) {
		initPlayerController($scope);
	}])

	.controller('PlayerItemsController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initPlayerItemsController($scope);
	}])

	.controller('PlayerItemController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initPlayerItemController($scope);
	}])

	.controller('ServersController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initServersController($scope);
	}])
        
	.controller('ServerController', ['$scope', function ($scope) {
		initTableRowForm($scope,'#formServer', $('#app_root').val()+'/fcw/#/servers');
	}])

	.controller('ChannelsController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initChannelsController($scope);
	}])
        
	.controller('ChannelController', ['$scope', function ($scope) {
		initTableRowForm($scope,'#formChannel', $('#app_root').val()+'/fcw/#/channels');
	}])

	.controller('MessagesController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initMessagesController($scope);
	}])
        
	.controller('MessageController', ['$scope', function ($scope) {
		initMessageController($scope);
	}])

	.controller('SysCharactersController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initSysCharactersController($scope);
	}])
        
	.controller('SysCharacterController', ['$scope', function ($scope) {
		initTableRowForm($scope,'#formSysCharacter', $('#app_root').val()+'/fcw/#/sysCharacters');
	}])

	.controller('SysLevelsController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initSysLevelsController($scope);
	}])
        
	.controller('SysLevelController', ['$scope', function ($scope) {
		initSysLevelController($scope);
	}])

	.controller('SysItemsController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initSysItemsController($scope);
	}])
        
	.controller('SysItemController', ['$scope', function ($scope) {
		initTableRowForm($scope,'#formSysItem', $('#app_root').val()+'/fcw/#/sysItems');
	}])

	.controller('PaymentsController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initPaymentsController($scope);
	}])
        
	.controller('PaymentController', ['$scope', function ($scope) {
		initTableRowForm($scope,'#formPayment', $('#app_root').val()+'/fcw/#/payments');
	}])

	.controller('GmUsersController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initGmUsersController($scope);
	}])
        
	.controller('GmUserController', ['$scope', function ($scope) {
		initTableRowForm($scope,'#formGmUser', $('#app_root').val()+'/fcw/#/gmUsers');
	}])

	.controller('GmGroupsController', ['$scope', 'DTOptionsBuilder', 'DTColumnBuilder',function ($scope, DTOptionsBuilder, DTColumnBuilder) {
		initGmGroupsController($scope);
	}])
        
	.controller('GmGroupController', ['$scope', function ($scope) {
		initGmGroupController($scope);
	}])
        
	.controller('GmResetPwdController', ['$scope', function ($scope) {
		initGmResetPwdController($scope);
	}])
        
	.controller('GmLogsController', ['$scope', function ($scope) {
		initGmLogsController($scope);
	}])
        
	.controller('GmTransactionsController', ['$scope', function ($scope) {
		initGmTransactionsController($scope);
	}])
;
