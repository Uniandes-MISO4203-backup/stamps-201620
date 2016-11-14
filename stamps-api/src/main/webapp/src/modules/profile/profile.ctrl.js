/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module('profileModule');

    mod.controller('profileCtrl', ['$scope','$cookieStore',
        function ($scope,$cookieStore) {
            $scope.CurrentUser=$cookieStore.get("usr");
            console.log($scope.CurrentUser);
        }]);

})(window.angular);
