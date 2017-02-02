'use strict';

/**
 * @ngdoc overview
 * @name genbandUxGridApp
 * @description
 * # genbandUxGridApp
 *
 * Main module of the application.
 */
angular
  .module('genbandUxGridApp', [
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.grid',
    'ui.grid.selection',
    'ui.grid.resizeColumns',
    'ui.grid.moveColumns',
    'ui.grid.pagination',
    'ui.grid.draggable-rows',
    'ui.grid.exporter',
    'ui.grid.grouping',
    'ui.grid.autoResize'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'static/htmlpages/main.html'
//        controller: 'MainCtrl',
//        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: '../WEB-INF/pages/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
