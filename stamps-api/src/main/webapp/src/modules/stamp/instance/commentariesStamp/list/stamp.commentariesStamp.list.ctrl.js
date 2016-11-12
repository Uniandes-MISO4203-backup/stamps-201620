/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('stampModule');
    
    mod.controller('commentariesStampListCtrl', ['$scope', 'stampCommentaries', 'model', '$state',
        function ($scope, stampCommentaries, model, $state) {
            $scope.records = stampCommentaries;
            $scope.model = model;
            $scope.actions = {
                edit: {
                    displayName: 'Edit',
                    icon: 'ok',
                    fn: function () {
                        $state.go('commentariesStampEdit');
                    }
                },
                cancel: {
                    displayName: 'Go back',
                    icon: 'arrow-left',
                    fn: function () {
                        $state.go('stampDetail');
                    }
                }
            };
        }]);
})(window.angular);
    


