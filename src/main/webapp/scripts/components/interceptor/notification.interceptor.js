 'use strict';

angular.module('cowbookApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-cowbookApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-cowbookApp-params')});
                }
                return response;
            }
        };
    });
