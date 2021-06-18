'use strict';

angular.module('cowbookApp')
	.controller('KategoriDeleteController', function($scope, $uibModalInstance, entity, Kategori) {

        $scope.kategori = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Kategori.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
