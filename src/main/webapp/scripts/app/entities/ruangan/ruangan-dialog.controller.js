'use strict';

angular.module('cowbookApp').controller('RuanganDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Ruangan', 'Kategori',
        function($scope, $stateParams, $uibModalInstance, entity, Ruangan, Kategori) {

        $scope.ruangan = entity;
        $scope.kategoris = Kategori.query();
        $scope.load = function(id) {
            Ruangan.get({id : id}, function(result) {
                $scope.ruangan = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('cowbookApp:ruanganUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.ruangan.id != null) {
                Ruangan.update($scope.ruangan, onSaveSuccess, onSaveError);
            } else {
                Ruangan.save($scope.ruangan, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForTanggalDibuat = {};

        $scope.datePickerForTanggalDibuat.status = {
            opened: false
        };

        $scope.datePickerForTanggalDibuatOpen = function($event) {
            $scope.datePickerForTanggalDibuat.status.opened = true;
        };
        $scope.datePickerForTanggalDiperbaharui = {};

        $scope.datePickerForTanggalDiperbaharui.status = {
            opened: false
        };

        $scope.datePickerForTanggalDiperbaharuiOpen = function($event) {
            $scope.datePickerForTanggalDiperbaharui.status.opened = true;
        };
}]);
