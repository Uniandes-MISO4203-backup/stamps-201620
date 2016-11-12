/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("stampCommentaryModule");
    
    mod.controller("stampCommentaryListCtrl", ["$scope", '$state', 'stampCommentaries', '$stateParams','Restangular',
        function ($scope, $state, stampCommentaries, $params, Restangular) {
            $scope.records = stampCommentaries;

            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = stampCommentaries.totalRecords;
            
            $scope.categorys = [];

            $scope.getCategorys = function (parentCategory) {
                Restangular.all("categorys").customGET('parents/'+parentCategory).then(function (response) {
                    if (response.length>0) {
                        $scope.categorys=response;
                    } 
                });
            };
            $scope.filtrar = function (parentCategory) {
                $scope.getCategorys(parentCategory);
                Restangular.all("stampCommentaries").customGET(parentCategory).then(function (response) {                    
                        $scope.records=response;
                });
            };
            $scope.getCategorys("");
            
            this.pageChanged = function () {
                $state.go('stampCommentaryList', {page: this.currentPage});
            };
            
            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('stampCommentaryNew');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
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
            
            $scope.recordActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('stampCommentaryDetail', {stampCommentaryId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function (rc) {
                        $state.go('stampCommentaryEdit', {stampCommentaryId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function (rc) {
                        $state.go('stampCommentaryDelete', {stampCommentaryId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };
    }]);
})(window.angular);

    

