/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('stampModule');

    mod.controller('commentariesStampEditCtrl', ['$scope', 'stampCommentaries', 'pool', 'model', '$state',
        function ($scope, stampCommentaries, available, model, $state) {
            $scope.records = stampCommentaries;
            $scope.model = model;
            $scope.available = available.plain();
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        $scope.records.customPUT().then(function () {
                            $state.go('commentariesStampList', null, {reload: true});
                        });
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('commentariesStampList');
                    }
                }
            };
        }]);
})(window.angular);