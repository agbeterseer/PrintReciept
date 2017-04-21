/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module("myApp",['ngRoute']);

var baseUrl= "http://localhost:8080/hg/order/";

app.config(['$routeProvider',function($routeProvider){
        $routeProvider
            .when('/',{
                title: '/',
                templateUrl:'invoice.html',
                controller:'printCtrl'
            });
}]);

app.controller('printCtrl',function($scope,$http){
    var id = 11393846950311;
    $http.get(baseUrl+id).then(function (response){
        console.log(response);
        $scope.order_data = response;
    });
    
});