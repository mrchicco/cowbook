'use strict';

angular.module('cowbookApp').controller('KategoriDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Kategori',
        function($scope, $stateParams, $uibModalInstance, entity, Kategori) {

        $scope.kategori = entity;
        $scope.load = function(id) {
            Kategori.get({id : id}, function(result) {
                $scope.kategori = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('cowbookApp:kategoriUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.kategori.id != null) {
                Kategori.update($scope.kategori, onSaveSuccess, onSaveError);
            } else {
                Kategori.save($scope.kategori, onSaveSuccess, onSaveError);
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
