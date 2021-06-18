'use strict';

angular.module('cowbookApp')
    .controller('RuanganDetailController', function ($scope, $rootScope, $stateParams, entity, Ruangan, Kategori) {
        $scope.ruangan = entity;
        $scope.load = function (id) {
            Ruangan.get({id: id}, function(result) {
                $scope.ruangan = result;
            });
        };
        var unsubscribe = $rootScope.$on('cowbookApp:ruanganUpdate', function(event, result) {
            $scope.ruangan = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
