angular.module('statistic', []).controller('statisticController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/market/';

    $scope.getStatistic = function () {
        $http.get(contextPath + 'statistic')
            .then(function (response) {
                $scope.statistic = response.data;
                // console.log(response);
            });
    };

    $scope.getStatistic();
});
