/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("stampCommentaryModule");

    mod.controller("stampCommentaryNewCtrl", ["$scope", "$state", "stampCommentaries",
        function ($scope, $state, stampCommentaries) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.stampCommentaryForm.$valid) {
                            stampCommentaries.post($scope.currentRecord).then(function (rc) {
                                $state.go('stampCommentaryDetail', {stampCommentaryId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('stampCommentaryList');
                    }
                }
            };
        }]);
})(window.angular);