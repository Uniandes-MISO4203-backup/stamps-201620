/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("stampCommentaryModule");

    mod.controller("stampCommentaryDeleteCtrl", ["$state", "stampCommentary", function ($state, stampCommentary) {
            this.confirmDelete = function () {
                stampCommentary.remove().then(function () {
                    $state.go('stampCommentaryList', null, {reload: true});
                });
            };
        }]);
})(window.angular);

