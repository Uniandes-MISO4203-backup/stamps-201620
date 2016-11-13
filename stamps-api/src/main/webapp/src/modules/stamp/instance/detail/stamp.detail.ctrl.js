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

    mod.controller("stampDetailCtrl", ['$scope',"$rootScope", "$state", "stamp",
        function ($scope,$rootScope, $state, stamp) {
            $scope.currentRecord = stamp;
            $scope.actions={};
            if($rootScope.authenticated){
            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('stampNew');
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function () {
                        $state.go('stampEdit');
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function () {
                        $state.go('stampDelete');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                },
                list: {
                    displayName: 'List',
                    icon: 'th-list',
                    fn: function () {
                        $state.go('stampList');
                    }
                },
                category: {
                    displayName: 'Category',
                    icon: 'link',
                    fn: function () {
                        $state.go('stampCategoryList');
                    }
                },
                stampCommentary: {
                    displayName: 'Commentary',
                    icon: 'link',
                    fn: function () {
                        $state.go('stampCommentaryList');
                    }
                },
                CommentariesStamp: {
                    displayName: 'Commentaries',
                    icon: 'link',
                    fn: function () {
                        $state.go('commentariesStampList');
                    }
                }
            };}
        }]);
   mod.directive('starRating', function () {
    return {
        restrict: 'A',
        template: '<ul class="star">' +
            '<li ng-repeat="star in stars">'  +
            '<a href="#"><i> </i> </a>'+
            '</li>' +
            '</ul>',
        scope: {
            ratingValue: '=',
            max: '='
        },
        link: function (scope, elem, attrs) {
            scope.stars = [];
            for (var i = 0; i < scope.max; i++) {
                scope.stars.push({
                    filled: i < scope.ratingValue
                });
            }
        }
    }
});
})(window.angular);
