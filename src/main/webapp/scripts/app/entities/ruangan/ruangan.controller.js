'use strict';

angular.module('cowbookApp')
    .controller('RuanganController', function ($scope, $state, Ruangan, ParseLinks) {

        $scope.ruangans = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        $scope.loadAll = function() {
            Ruangan.query({page: $scope.page - 1, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.ruangans = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.ruangan = {
                nama_ruangan: null,
                harga: null,
                aktif: null,
                dibuatOleh: null,
                tanggalDibuat: null,
                diperbaharuiOleh: null,
                tanggalDiperbaharui: null,
                id: null
            };
        };
    });
