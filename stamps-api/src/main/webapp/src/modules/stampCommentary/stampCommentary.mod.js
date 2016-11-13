/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('stampCommentaryModule', ['ngCrud', 'ui.router']);
    
    mod.constant('stampCommentaryModel', {
        name: 'stampCommentary',
        displayName: 'stampCommentary',
        url: 'stampCommentaries',
        fields: {
            name: {
                displayName: 'Username',
                type: 'String',
                required: true
            },
            commentary: {
                displayName: 'commentary',
                type: 'String',
                required: true
            },
            qualification: {
                displayName: 'qualification',
                type: 'Integer',
                required: true
            },
            dateCommentary: {
                displayName: 'dateCommentary',
                type: 'Date',
                required: true
            },
            stamp: {
                displayName: 'Stamp',
                type: 'Reference',
                model: 'stampModel',
                options: [],
                required: true
            }
        }
    });
    
     mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/stampCommentary/';
            var baseInstancePath = basePath + 'instance/';
            
            $sp.state('stampCommentary', {
                url: '/stampCommentaries?page&limit',
                abstract: true,
                parent: 'stampDetail',
                views: {
                    stampChieldView: {
                        templateUrl: basePath + 'stampCommentary.tpl.html',
                        controller: 'stampCommentaryCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                        return $q.all({
                            stamp: r.all('stamps').getList()
                        });
                    }],
                    model: 'stampCommentaryModel',
                    stampCommentaries: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                        return r.all(model.url).getList($params);
                    /* stampCommentaries: ['stamp', 'model', '$stateParams', function (stamp, model, $params) {
                        return stamp.getList(model.url,$params);*/
                    }]
                }
            });
            
            $sp.state('stampCommentaryList', {
                url: '/list',
                parent: 'stampCommentary',
                views: {
                    stampCommentaryView: {
                        templateUrl: basePath + 'list/stampCommentary.list.tpl.html',
                        controller: 'stampCommentaryListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
            $sp.state('stampCommentaryNew', {
                url: '/new',
                parent: 'stampCommentary',
                views: {
                    stampCommentaryView: {
                        templateUrl: basePath + 'new/stampCommentary.new.tpl.html',
                        controller: 'stampCommentaryNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
            $sp.state('stampCommentaryInstance', {
                url: '/{stampCommentaryId:int}',
                abstract: true,
                parent: 'stampCommentary',
                views: {
                    stampCommentaryView: {
                        template: '<div ui-view="stampCommentaryInstanceView"></div>'
                    }
                },
                resolve: {
                    stampCommentary: ['stampCommentaries', '$stateParams', function (stampCommentaries, $params) {
                        return stampCommentaries.get($params.stampCommentaryId);
                    }]
                }
            });
            
            $sp.state('stampCommentaryDetail', {
                url: '/details',
                parent: 'stampCommentaryInstance',
                views: {
                    stampCommentaryInstanceView: {
                        templateUrl: baseInstancePath + 'detail/stampCommentary.detail.tpl.html',
                        controller: 'stampCommentaryDetailCtrl'
                    }
                }
            });
            
            $sp.state('stampCommentaryEdit', {
                url: '/edit',
                sticky: true,
                parent: 'stampCommentaryInstance',
                views: {
                    stampCommentaryInstanceView: {
                        templateUrl: baseInstancePath + 'edit/stampCommentary.edit.tpl.html',
                        controller: 'stampCommentaryEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
            $sp.state('stampCommentaryDelete', {
                url: '/delete',
                parent: 'stampCommentaryInstance',
                views: {
                    stampCommentaryInstanceView: {
                        templateUrl: baseInstancePath + 'delete/stampCommentary.delete.tpl.html',
                        controller: 'stampCommentaryDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);