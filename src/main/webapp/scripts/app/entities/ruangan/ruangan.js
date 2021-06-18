'use strict';

angular.module('cowbookApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('ruangan', {
                parent: 'entity',
                url: '/ruangans',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Ruangans'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ruangan/ruangans.html',
                        controller: 'RuanganController'
                    }
                },
                resolve: {
                }
            })
            .state('ruangan.detail', {
                parent: 'entity',
                url: '/ruangan/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Ruangan'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ruangan/ruangan-detail.html',
                        controller: 'RuanganDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Ruangan', function($stateParams, Ruangan) {
                        return Ruangan.get({id : $stateParams.id});
                    }]
                }
            })
            .state('ruangan.new', {
                parent: 'ruangan',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/ruangan/ruangan-dialog.html',
                        controller: 'RuanganDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    nama_ruangan: null,
                                    harga: null,
                                    aktif: null,
                                    dibuatOleh: null,
                                    tanggalDibuat: null,
                                    diperbaharuiOleh: null,
                                    tanggalDiperbaharui: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('ruangan', null, { reload: true });
                    }, function() {
                        $state.go('ruangan');
                    })
                }]
            })
            .state('ruangan.edit', {
                parent: 'ruangan',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/ruangan/ruangan-dialog.html',
                        controller: 'RuanganDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Ruangan', function(Ruangan) {
                                return Ruangan.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('ruangan', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('ruangan.delete', {
                parent: 'ruangan',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/ruangan/ruangan-delete-dialog.html',
                        controller: 'RuanganDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Ruangan', function(Ruangan) {
                                return Ruangan.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('ruangan', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
