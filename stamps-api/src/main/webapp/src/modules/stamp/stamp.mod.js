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
    var mod = ng.module('stampModule', ['ngCrud', 'ui.router']);

    mod.constant('stampModel', {
        name: 'stamp',
        displayName: 'Stamp',
		url: 'stamps',
        fields: {            
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            image: {
                displayName: 'Image',
                type: 'String',
                required: true
            },
            price: {
                displayName: 'Price',
                type: 'Long',
                required: true
            },
            availableForSale:{
                displayName: 'Available  for sale',
                type: 'Boolean',
                required: true
            },
            qualification:{
                displayName: 'Qualification',
                type: 'Integer',
                required: false   
            },
            description:{
                displayName: 'Description',
                type: 'String',
                required: true
            },
            artistComment:{
                displayName: 'Artist Comment',
                type: 'String',
                required: true
            },            
            popularity:{
                displayName: 'Popularity',
                type: 'Long',
                required: true
            },
            comments:{
                displayName: 'Comments',
                type: 'String',
                required: false
            }
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/stamp/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('stamp', {
                url: '/stamps?page&limit',
                abstract: true,
                parent: 'artistDetail',
                views: {
                     artistChieldView: {
                        templateUrl: basePath + 'stamp.tpl.html',
                        controller: 'stampCtrl'
                    }
                },
                resolve: {
                    model: 'stampModel',
                    stamps: ['artist', '$stateParams', 'model', function (artist, $params, model) {
                            return artist.getList(model.url, $params);
                        }]                }
            });
            $sp.state('stampList', {
                url: '/list',
                parent: 'stamp',
                views: {
                    stampView: {
                        templateUrl: basePath + 'list/stamp.list.tpl.html',
                        controller: 'stampListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('stampNew', {
                url: '/new',
                parent: 'stamp',
                views: {
                    stampView: {
                        templateUrl: basePath + 'new/stamp.new.tpl.html',
                        controller: 'stampNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('stampInstance', {
                url: '/{stampId:int}',
                abstract: true,
                parent: 'stamp',
                views: {
                    stampView: {
                        template: '<div ui-view="stampInstanceView"></div>'
                    }
                },
                resolve: {
                    stamp: ['stamps', '$stateParams', function (stamps, $params) {
                            return stamps.get($params.stampId);
                        }]
                }
            });
            $sp.state('stampDetail', {
                url: '/details',
                parent: 'stampInstance',
                views: {
                    stampInstanceView: {
                        templateUrl: baseInstancePath + 'detail/stamp.detail.tpl.html',
                        controller: 'stampDetailCtrl'
                    }
                }
            });
            $sp.state('stampEdit', {
                url: '/edit',
                sticky: true,
                parent: 'stampInstance',
                views: {
                    stampInstanceView: {
                        templateUrl: baseInstancePath + 'edit/stamp.edit.tpl.html',
                        controller: 'stampEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('stampDelete', {
                url: '/delete',
                parent: 'stampInstance',
                views: {
                    stampInstanceView: {
                        templateUrl: baseInstancePath + 'delete/stamp.delete.tpl.html',
                        controller: 'stampDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('stampCategory', {
                url: '/category',
                parent: 'stampDetail',
                abstract: true,
                views: {
                    stampChieldView: {
                        template: '<div ui-view="stampCategoryView"></div>'
                    }
                },
                resolve: {
                    category: ['stamp', function (stamp) {
                            return stamp.getList('category');
                        }],
                    model: 'categoryModel'
                }
            });
            $sp.state('stampCategoryList', {
                url: '/list',
                parent: 'stampCategory',
                views: {
                    stampCategoryView: {
                        templateUrl: baseInstancePath + 'category/list/stamp.category.list.tpl.html',
                        controller: 'stampCategoryListCtrl'
                    }
                }
            });
            $sp.state('stampCategoryEdit', {
                url: '/edit',
                parent: 'stampCategory',
                views: {
                    stampCategoryView: {
                        templateUrl: baseInstancePath + 'category/edit/stamp.category.edit.tpl.html',
                        controller: 'stampCategoryEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    pool: ['Restangular', 'model', function (r, model) {
                            return r.all(model.url).getList();
                        }]
                }
            });
            $sp.state('stampGallery', {
                url: '/stampsGallery',
                views: {
                     mainView: {
                        templateUrl: basePath + 'list/stamp.gallery.tpl.html',
                        controller: 'stampListCtrl',
                        controllerAs: 'ctrl'
                    },
                },
                resolve: {
                    model: 'stampModel',
                    stamps: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]                
                }
            });
            
             $sp.state('latest', {
                url: '/new',
                views: {
                     mainView: {
                        templateUrl: basePath + 'list/stamp.list.latest.html',
                        controller: 'stampListLatestCtrl',
                        controllerAs: 'ctrl'
                    },
                },
                resolve: {
                    model: 'stampModel',
                    stamps: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]                
                }
            });
          
	}]);
    
    
})(window.angular);
