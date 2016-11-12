/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("stampCommentaryModule");

    mod.controller("stampCommentaryEditCtrl", ["$scope", "$state", "stampCommentary",
        function ($scope, $state, stampCommentary) {
            $scope.currentRecord = stampCommentary;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.stampCommentaryForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('stampCommentaryDetail', {stampCommentaryId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('stampCommentaryDetail');
                    }
                }
            };
        }]);
})(window.angular);

