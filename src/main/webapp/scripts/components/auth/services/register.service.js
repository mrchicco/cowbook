'use strict';

angular.module('cowbookApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


