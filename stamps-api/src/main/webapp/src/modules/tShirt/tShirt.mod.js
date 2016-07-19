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
    var mod = ng.module('tShirtModule', ['ngCrud', 'ui.router']);

    mod.constant('tShirtModel', {
        name: 'tShirt',
        displayName: 'T Shirt',
		url: 'tShirts',
        fields: {            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            size: {
                displayName: 'Size',
                type: 'String',
                required: true
            },
            color: {
                displayName: 'Color',
                type: 'String',
                required: true
            },
            price: {
                displayName: 'Price',
                type: 'Long',
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/tShirt/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('tShirt', {
                url: '/tShirts?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'tShirt.tpl.html',
                        controller: 'tShirtCtrl'
                    }
                },
                resolve: {
                    model: 'tShirtModel',
                    tShirts: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('tShirtList', {
                url: '/list',
                parent: 'tShirt',
                views: {
                    tShirtView: {
                        templateUrl: basePath + 'list/tShirt.list.tpl.html',
                        controller: 'tShirtListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('tShirtNew', {
                url: '/new',
                parent: 'tShirt',
                views: {
                    tShirtView: {
                        templateUrl: basePath + 'new/tShirt.new.tpl.html',
                        controller: 'tShirtNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('tShirtInstance', {
                url: '/{tShirtId:int}',
                abstract: true,
                parent: 'tShirt',
                views: {
                    tShirtView: {
                        template: '<div ui-view="tShirtInstanceView"></div>'
                    }
                },
                resolve: {
                    tShirt: ['tShirts', '$stateParams', function (tShirts, $params) {
                            return tShirts.get($params.tShirtId);
                        }]
                }
            });
            $sp.state('tShirtDetail', {
                url: '/details',
                parent: 'tShirtInstance',
                views: {
                    tShirtInstanceView: {
                        templateUrl: baseInstancePath + 'detail/tShirt.detail.tpl.html',
                        controller: 'tShirtDetailCtrl'
                    }
                }
            });
            $sp.state('tShirtEdit', {
                url: '/edit',
                sticky: true,
                parent: 'tShirtInstance',
                views: {
                    tShirtInstanceView: {
                        templateUrl: baseInstancePath + 'edit/tShirt.edit.tpl.html',
                        controller: 'tShirtEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('tShirtDelete', {
                url: '/delete',
                parent: 'tShirtInstance',
                views: {
                    tShirtInstanceView: {
                        templateUrl: baseInstancePath + 'delete/tShirt.delete.tpl.html',
                        controller: 'tShirtDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
