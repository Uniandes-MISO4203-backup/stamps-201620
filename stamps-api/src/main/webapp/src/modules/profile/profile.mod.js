/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module('profileModule', ['ui.router','ngCookies']);
    
    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/profile/';
            $sp.state('profile', {
                url: '/profile',
                views: {
                     mainView: {
                        templateUrl: basePath + 'profile.tpl.html',
                        controller: 'profileCtrl',
                        controllerAs: 'ctrl'
                    },        
                }
            });
	}]);
    
    
})(window.angular);
