/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {

    var mod = ng.module("stampModule");
    
    mod.controller("stampListLatestCtrl", ["$scope", "$rootScope",'$state', 'stamps', '$stateParams','Restangular',
        function ($scope, $state,$rootScope, stamps, $params,Restangular) {
          $scope.latest=stamps;
          $scope.categorys = [];
            $scope.getCategorys = function (parentCategory) {
                Restangular.all("categorys").customGET('parents/'+parentCategory).then(function (response) {
                    if (response.length>0) {
                        $scope.categorys=response;
                    } 
                });
            };
          
            $scope.getLatest = function () {
                Restangular.all("stamps").customGET('latestStamps/').then(function (response) {
                    if (response.length>0) {
                        console.log(response);
                        $scope.latest=response;
                        
                    } 
                });
            };
                        $scope.filtrar = function (parentCategory) {
                            console.log(parentCategory);
                $scope.getCategorys(parentCategory);
                Restangular.all("stamps").customGET(parentCategory).then(function (response) {                        
                        $scope.latest=response;                    
                });
             
            };
            $scope.getLatest();
            $scope.getCategorys("");

        }]);
})(window.angular);
