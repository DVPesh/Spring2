angular.module('market').controller('registrationController', function ($rootScope, $scope, $http, $location, $localStorage) {

    const contextPath = 'http://localhost:5555';

    $scope.newUser = {
        username: '',
        password: '',
        email: ''
    };
    $scope.passwordRepeat = '';

    $scope.registerNewUser = function () {
        if ($scope.passwordRepeat === $scope.newUser.password) {
            $http.post(contextPath + '/registration', $scope.newUser)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.springWebUser = {
                            username: $scope.newUser.username,
                            token: response.data.token,
                            roles: response.data.roles
                        };
                        $rootScope.username = $scope.newUser.username;
                        $scope.newUser.username = '';
                        $scope.newUser.password = '';
                        $scope.newUser.email = '';
                        $scope.passwordRepeat = '';
                        $location.path('/');
                    }
                }, function errorCallback(response) {
                    if (response.data) {
                        alert(response.data.error);
                    }
                });
        } else {
            alert('Поля "Пароль" и "Повтор пароля" не совпадают');
        }
    }
});
