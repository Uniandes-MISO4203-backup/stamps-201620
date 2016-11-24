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

    mod.controller("stampDetailCtrl", ['$scope',"$rootScope", "$state", "stamp",'$cookieStore',
        function ($scope,$rootScope, $state, stamp, $cookieStore) {
            $scope.currentRecord = stamp;
            $scope.CurrentUser = $cookieStore.get("usr");
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
                }
            };
            document.getElementById('DisplayCommentary').style.display = 'block';
            }
            $scope.CreateComment = function() {  
                var commentariesUsers = $scope.currentRecord.comments;
                var StampCommentary = document.getElementById('commentaries').value;
                if (commentariesUsers == undefined){
                    commentariesUsers = ""; 
                }
                if (StampCommentary != ""){
                    var username = $scope.CurrentUser.userName;                    
                    var commentaryUser = username + ':  "' + StampCommentary;
                    console.log(username);
                    console.log(commentariesUsers);
                    var StampCommentaries = commentaryUser + '";\n' + commentariesUsers;
                    $scope.currentRecord.comments = StampCommentaries;
                    $scope.currentRecord.put().then(function (rc) {
                        $state.go('stampDetail', {stampId: rc.id}, {reload: true});
                    });
                }
                document.getElementById('commentaries').value = "Escribe aqui tu comentario";
                document.getElementById('Botones').style.display = 'none';
            };  
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
