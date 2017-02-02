'use strict';

/**
 * @ngdoc function
 * @name genbandUxGridApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the genbandUxGridApp
 */
angular.module('genbandUxGridApp')
  .controller('MainCtrl', ['$scope', '$http', 'uiGridConstants', 'uiGridGroupingService', 'uiGridGroupingConstants', '$timeout', '$filter', function ($scope, $http, uiGridConstants, uiGridGroupingService, uiGridGroupingConstants, $timeout, $filter) {

    $scope.currentSelectedRows = [];
   // $scope.test = {isActive: false};
    $scope.selectedColumn = null;

    $scope.highlightFilteredHeader = function( row, rowRenderIndex, col, colRenderIndex ) {
        if( col.filters[0].term ){
            return 'header-filtered';
        } else {
            return '';
        }
    };

    var paginationOptions = { // External Pagination
        pageNumber: 1,
        pageSize: 25,
        sort: []
    };

    $scope.grid1_Options = {
        enableGrouping: true,
        enableFiltering: true,
        paginationPageSizes: [25, 1000, 10000],
        paginationPageSize: 25,
        useExternalPagination: true, // External Pagination
        //useExternalSorting: true, // External Sorting
        disableCancelFilterButton: true,
        //enableColumnResizing: true,
        enableColumnResize : true,
        enableGroupHeaderSelection : true,
        multiSelect: true,
        enableGridMenu: true,
        exporterMenuCsv: false,
        exporterMenuPdf: false,
        rowTemplate: '<div grid="grid" class="ui-grid-draggable-row" draggable="true"><div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader, \'custom\': true }" ui-grid-cell></div></div>',
        onRegisterApi: function(gridApi){
            $scope.gridApi = gridApi;

            gridApi.selection.on.rowSelectionChanged($scope,function(row){
                $scope.currentSelectedRows = gridApi.selection.getSelectedRows();
                if($scope.currentSelectedRows.length > 0)
                    $scope.deleteRow = true;
                else
                    $scope.deleteRow = false;
            });
            gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
                $scope.currentSelectedRows = gridApi.selection.getSelectedRows();
                if($scope.currentSelectedRows.length > 0)
                    $scope.deleteRow = true;
                else
                    $scope.deleteRow = false;
            });

            gridApi.draggableRows.on.rowDropped($scope, function (info, dropTarget) { /*console.log("Dropped", info);*/ });

            gridApi.grid.registerRowsProcessor( $scope.singleFilter, 200 ); // $scope.

            gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                paginationOptions.pageNumber = newPage;
                paginationOptions.pageSize = pageSize;
                getPage();
            });

            gridApi.core.on.sortChanged($scope, function(grid, sortColumns) { // $scope.

                angular.forEach(sortColumns, function (sortColumn) {
                    var item = {
                            fieldName: sortColumn.name,
                            order: sortColumn.sort.direction
                        };

                    function search(fieldName, order, arr){
                        var fieldName = fieldName,
                            order = order,
                            arr = arr,
                            result = true;
                        for (var i = 0; i < arr.length; i++) {
                            if (arr[i].fieldName === fieldName) {
                                if(arr[i].order !== order) {
                                    arr[i].order = order;
                                }
                                result = false;
                            }
                        }
                        return result;
                    }

                    if (search(sortColumn.name, sortColumn.sort.direction, paginationOptions.sort) === true) {
                        paginationOptions.sort.push(item);
                    }
                });

                //console.log(paginationOptions.sort);
                getPage();
            });

        }
    };

    $scope.grid1_Options.columnDefs = [
        { name: 'starttime',
            headerCellClass: $scope.highlightFilteredHeader,
            groupingShowAggregationMenu : false,
            visible: false
        },
		{ name: 'mswname', groupingShowAggregationMenu : false },
		{ name: 'origRealm', groupingShowAggregationMenu : false },
		{ name: 'origIp', groupingShowAggregationMenu : false },
		{ name: 'origPort', groupingShowAggregationMenu : false,
            visible: false },
		{ name: 'termRealm', groupingShowAggregationMenu : false },
		{ name: 'termIp', groupingShowAggregationMenu : false },
		{ name: 'termGw', groupingShowAggregationMenu : false,
            visible: false },
        { name: 'callduration',
            enableFiltering: false,
            groupingShowAggregationMenu : false },
        { name: 'termPort', groupingShowAggregationMenu : false,
                visible: false },
        { name: 'pdd', groupingShowAggregationMenu : false },
	    { name: 'callRoute', groupingShowAggregationMenu : false,
            visible: false },
        { name: 'callDstCustId', groupingShowAggregationMenu : false,
                visible: false },
        { name: 'callZoneData', groupingShowAggregationMenu : false,
            visible: false },
        { name: 'callDstNumType', groupingShowAggregationMenu : false,
            visible: false },
        { name: 'callSrcNumType', groupingShowAggregationMenu : false,
                visible: false },
	    { name: 'lastEvent', groupingShowAggregationMenu : false,
            visible: false },
	    { name: 'protocol', groupingShowAggregationMenu : false,
            visible: false },
        { name: 'newANI', groupingShowAggregationMenu : false,
            visible: false }
    ];

    $scope.removeRows = function() {
        if($scope.currentSelectedRows.length > 0){
            for(var index = 0; index < $scope.currentSelectedRows.length; index++){
                var removeRowIndex = $scope.grid1_Options.data.indexOf($scope.currentSelectedRows[index]);
                $scope.grid1_Options.data.splice(removeRowIndex, 1);
            }
            //$scope.gridOpts.data.splice(0,1);
            $scope.gridApi.selection.clearSelectedRows();
        }
    };
        
    $scope.export = function(input){
        //var myElement = angular.element(document.querySelectorAll(".custom-csv-link-location"));
        //$scope.gridApi.exporter.csvExport( 'all', 'all', myElement );
        $scope.export_format = input;
        if ($scope.export_format == 'csv') {
            var myElement = angular.element(document.querySelectorAll(".custom-csv-link-location"));
            $scope.gridApi.exporter.csvExport( 'all', 'all', myElement );
        } else if ($scope.export_format == 'excel') {
            //$scope.gridApi.exporter.pdfExport( 'all', 'all' );
        } else if ($scope.export_format == 'pdf') {
            //$scope.gridApi.exporter.pdfExport( 'all', 'all' );
        };
    };
          
    //$scope.changeGrouping = function() {
        //if($scope.selectedColumn !== undefined){
          //$scope.gridApi.grouping.clearGrouping();
          //$scope.gridApi.grouping.groupRows();
          //$scope.gridApi.grouping.groupColumn($scope.selectedColumn);
          //$scope.gridApi.grouping.aggregateColumn($scope.selectedColumn, uiGridGroupingConstants.aggregation.COUNT);
       //}

    //};

    $scope.filter = function() {
        $scope.gridApi.grid.refresh();
    };
    
//    $scope.$watch('test.isActive', function (active, oldActive) {
//        if (active && active !== oldActive && $scope.gridApi) {
//        	$timeout(function () {
//                $scope.gridApi.grid.handleWindowResize();
//            });
//        }
//    });

    $scope.singleFilter = function( renderableRows ){
        //console.log($scope.filterValue);
        var matcher = new RegExp($scope.filterValue);
        matcher = $filter('lowercase')(matcher);
        renderableRows.forEach( function( row ) {
            var match = false;
            [ 'starttime', 'mswname', 'origRealm', 'origIp','termRealm', 'termIp', 'termGw', 'callduration', 'callRoute'].forEach(function( field ){
                // console.log(row.entity[field]);
                if ( ( $filter('lowercase')(row.entity[field]) ).match(matcher) ){
                    match = true;
                }
            });
            if ( !match ){
                row.visible = false;
            }
        });
        return renderableRows;
    };

    $scope.toggleFiltering = function(){
        $scope.grid1_Options.enableFiltering = !$scope.grid1_Options.enableFiltering;
        $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
    };
    
    $scope.getTableHeight = function() {
        var rowHeight = 30; // your row height
        var headerHeight = 30; // your header height
        return {
           height: ($scope.grid1_Options.data.length * rowHeight + headerHeight) + "px"
        };
    };

    var getPage = function() {
    	$scope.loading = true;
    	var i = 0;
    	$scope.grid1_Options.data = [];
    	//url: 'http://172.28.174.178:9090/OneEMS/rest/getrecords?pageno='+paginationOptions.pageNumber+'&pagesize='+paginationOptions.pageSize,
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8081/OneEMS/rest/getrecords?pageno='+(paginationOptions.pageNumber - 1)+'&pagesize='+paginationOptions.pageSize,
            // The key needs to match your method's input parameter (case-sensitive).
            //data: sortObj,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(data){
                $scope.grid1_Options.totalItems = data.count;
                //var jsonObj = JSON.parse(data.records);
                //rowCount = jsonObj.length;
                $scope.grid1_Options.data = [];
                data.records.forEach(function (row) {
                    row.id = i; i++;
                    $scope.grid1_Options.data.push(row);
                    $scope.gridApi.grid.handleWindowResize();
                });
                $scope.loading = false;
                //$scope.grid1_Options.data = data.records;
            },
            failure: function(errMsg) {
                console.log(errMsg);
                $scope.loading = false;
            }
            
        });
//        $http.get('scripts/data/data.json?pageno='+paginationOptions.pageNumber+'&pagesize='+paginationOptions.pageSize+'&sortingData='+paginationOptions.sort)
//            .success(function (data) {
//                $scope.grid1_Options.totalItems = data.properties.length;
//                $scope.grid1_Options.data = data.records;
//                //var firstRow = (paginationOptions.pageNumber - 1) * paginationOptions.pageSize;
//                //$scope.grid1_Options.data = data.properties.slice(firstRow, firstRow + paginationOptions.pageSize);
//                //console.log($scope.grid1_Options.data);
//            });
    };

    getPage();

}]);