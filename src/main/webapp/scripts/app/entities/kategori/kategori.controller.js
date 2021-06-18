'use strict';

angular.module('cowbookApp')
    .controller('KategoriController', function ($scope, $state, Kategori, ParseLinks) {

        $scope.kategoris = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        $scope.loadAll = function() {
            Kategori.query({page: $scope.page - 1, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.kategoris = result;
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
            $scope.kategori = {
                namaKategori: null,
                kapasitas: null,
                dibuatOleh: null,
                tanggalDibuat: null,
                diperbaharuiOleh: null,
                tanggalDiperbaharui: null,
                id: null
            };
        };
    });
