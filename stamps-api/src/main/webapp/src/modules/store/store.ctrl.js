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
    var mod = ng.module('mainApp');

    mod.controller('storeCtrl', ['$scope', 'Restangular', '$rootScope',
        function ($scope,r, $rootScope) {
            
            $scope.numPerPage = 9;
            $scope.maxSize = 5;
            
            //Alertas
            $scope.alerts = [];
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };
            
            $scope.stamps = [];

            r.all("stamps/all").getList().then(function(resp){
            
                r.all("tShirts").getList().then(function(resp2){
                    $scope.stamps = resp;
                    console.log($scope.stamps)
                    $scope.shirts = resp2;
                    
                    $scope.currentPage = 1;
                    

                });
            });
            
            $scope.$watch("currentPage + numPerPage", function() {
            var begin = (($scope.currentPage - 1) * $scope.numPerPage);
            var end = begin + $scope.numPerPage;

            $scope.filteredStamps = $scope.stamps.slice(begin, end);
          });
            
            console.log($scope.stamps);
            
            $scope.addToWishList = function(stamp, shirt){
                
                console.log($rootScope);

                r.all("clients/"+ $rootScope.clientObject.id + "/wishList").post({stamp:stamp,tShirt: shirt,name:"item",qty:1,status:0}).then(function(res){
                    
                    alert("Item added to wishlist");
                    
                });
            }
            
            $scope.acquire = function(){
                
                console.log($rootScope);

                
                r.all("clients/"+ $rootScope.clientObject.id + "/wishList/acquirecart").post({}).then(function(res){
                    
                    alert("Cart Items acquired");
                    
                });
            }

            /* Función showMessage: Recibe el mensaje en String y
             * su tipo con el fin de almacenarlo en el array $scope.alerts.
             */
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            $scope.showError = function (msg) {
                showMessage(msg, "danger");
            };

            $scope.showSuccess = function (msg) {
                showMessage(msg, "success");
            };
        }]);

})(window.angular);
