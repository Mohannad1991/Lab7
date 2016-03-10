var place = angular.module('place',[]);

place.controller('homeController', function($scope, $http, $window){
    $http.get("http://localhost:8075/Tutorial_6/mongoservlet/mongoservlet").success(function(dataOne) {
        placeArray = dataOne;
        lenght = dataOne.length;
        $scope.selectors = dataOne;
        places = [{}];


        for (i = 0; i < Number(lenght); i++) {
            places[i] = placeArray[i].place;
        }
    })

    $scope.update = function(){
        var placevalue = document.getElementById('place').value;
        var place = placeArray[placevalue];
        var place2 = document.getElementById('updateValue').value;;
        $http.get("http://localhost:8075/Tutorial_6/Update/Update/?place1=" + place + "&place2=" + place2).success(function(data){

        });
    }

    $scope.remove1 = function(){
        var placevalue = document.getElementById('place').value;
        var place = placeArray[placevalue];
        $http.get("http://localhost:8075/Tutorial_6/Delete/Delete?place=" + place).success(function(data){
        });
    }


    $scope.Create = function(data){
        var cityCreate = document.getElementById('createPlace').value;
        var url = "http://localhost:8075/Tutorial_6/mongoservlet/mongoservlet";
        var res = $http.post(url, data);
    }

});
