(function (ng) {
    var mod = ng.module('roleModule', ['ngCrud']);
    mod.controller('roleCtrl', ['$rootScope', 'Restangular','$scope','$state', function ($rootScope, Restangular, $scope) {

        var getArtistByName = function(nam){
            
                Restangular.all("artists/byname/"+nam).customGET().then(function(resp){
                    $rootScope.artistObject = resp;
                    $scope.newStampUrl = "#/artists/"+ resp.id +"/details/stamps/new";
                    console.log($scope.newStampUrl);
                });
        }
        
        var getClientByName = function(nam){
            
                Restangular.all("clients/byname/"+nam).customGET().then(function(resp){
                    $rootScope.clientObject = resp;
                });
        }

        $rootScope.auth = function () {
                Restangular.all("users").customGET('me').then(function (response) {
                    if (response == null) {
                        $rootScope.category = false;
                        $rootScope.artist = false;
                        $rootScope.client = false;
                        $rootScope.tShirt = false;
                        $rootScope.authenticated = false;
                    } else {
                        $rootScope.authenticated = true;
                        var roles = $rootScope.roles = response.roles;
                        var isFreelance = false;
                        if (roles.indexOf("client") !== -1) {
                            isFreelance = true;
                            $rootScope.category = false;
                            $rootScope.artist = false;
                            $rootScope.client = true;
                            $rootScope.tShirt = false;
                            
                            getClientByName(response.userName);
                        }
                        if (roles.indexOf("artist") !== -1) {
                            $rootScope.category = false;
                            $rootScope.artist = true;
                            $rootScope.client = false;
                            $rootScope.tShirt = false;
                            
                            getArtistByName(response.userName);
                        }
                        if (roles.indexOf("admin") !== -1) {
                            $rootScope.category = true;
                            $rootScope.artist = true;
                            $rootScope.client = true;
                            $rootScope.tShirt = true;
                            
                            if(!$rootscope.artistObject){
                                getArtistByName(response.userName);
                            }
                            
                            if(!$rootscope.clientObject){
                                getClientByName(response.userName);
                            }
                        }
                    }
                });
            };
        $rootScope.auth();
        $rootScope.$on('logged-in', function () {
            $rootScope.auth();
            if ($rootScope.roles.indexOf("admin") !== -1) {
                $rootScope.category = true;
                $rootScope.artist = true;
                $rootScope.client = true;
                $rootScope.tShirt = true;
            }
        });

        $rootScope.$on('logged-out', function () {
            $rootScope.auth();
        });
    }]);
})(window.angular);




