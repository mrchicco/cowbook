'use strict';

angular.module('cowbookApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('kategori', {
                parent: 'entity',
                url: '/kategoris',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Kategoris'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/kategori/kategoris.html',
                        controller: 'KategoriController'
                    }
                },
                resolve: {
                }
            })
            .state('kategori.detail', {
                parent: 'entity',
                url: '/kategori/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Kategori'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/kategori/kategori-detail.html',
                        controller: 'KategoriDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Kategori', function($stateParams, Kategori) {
                        return Kategori.get({id : $stateParams.id});
                    }]
                }
            })
            .state('kategori.new', {
                parent: 'kategori',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/kategori/kategori-dialog.html',
                        controller: 'KategoriDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    namaKategori: null,
                                    kapasitas: null,
                                    dibuatOleh: null,
                                    tanggalDibuat: null,
                                    diperbaharuiOleh: null,
                                    tanggalDiperbaharui: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('kategori', null, { reload: true });
                    }, function() {
                        $state.go('kategori');
                    })
                }]
            })
            .state('kategori.edit', {
                parent: 'kategori',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/kategori/kategori-dialog.html',
                        controller: 'KategoriDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Kategori', function(Kategori) {
                                return Kategori.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('kategori', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('kategori.delete', {
                parent: 'kategori',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/kategori/kategori-delete-dialog.html',
                        controller: 'KategoriDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Kategori', function(Kategori) {
                                return Kategori.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('kategori', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
