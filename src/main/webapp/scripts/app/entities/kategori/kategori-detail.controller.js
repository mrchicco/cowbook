'use strict';

angular.module('cowbookApp')
    .controller('KategoriDetailController', function ($scope, $rootScope, $stateParams, entity, Kategori) {
        $scope.kategori = entity;
        $scope.load = function (id) {
            Kategori.get({id: id}, function(result) {
                $scope.kategori = result;
            });
        };
        var unsubscribe = $rootScope.$on('cowbookApp:kategoriUpdate', function(event, result) {
            $scope.kategori = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
