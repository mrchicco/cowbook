'use strict';

angular.module('cowbookApp')
    .factory('Ruangan', function ($resource, DateUtils) {
        return $resource('api/ruangans/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.tanggalDibuat = DateUtils.convertDateTimeFromServer(data.tanggalDibuat);
                    data.tanggalDiperbaharui = DateUtils.convertDateTimeFromServer(data.tanggalDiperbaharui);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
