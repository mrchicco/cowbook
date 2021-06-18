'use strict';

angular.module('cowbookApp')
	.controller('RuanganDeleteController', function($scope, $uibModalInstance, entity, Ruangan) {

        $scope.ruangan = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Ruangan.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
