'use strict';

describe('Controller Tests', function() {

    describe('Kategori Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockKategori;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockKategori = jasmine.createSpy('MockKategori');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Kategori': MockKategori
            };
            createController = function() {
                $injector.get('$controller')("KategoriDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'cowbookApp:kategoriUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
