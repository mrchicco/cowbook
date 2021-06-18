'use strict';

describe('Controller Tests', function() {

    describe('Ruangan Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRuangan, MockKategori;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRuangan = jasmine.createSpy('MockRuangan');
            MockKategori = jasmine.createSpy('MockKategori');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Ruangan': MockRuangan,
                'Kategori': MockKategori
            };
            createController = function() {
                $injector.get('$controller')("RuanganDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'cowbookApp:ruanganUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
