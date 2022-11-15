angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    const contextPath = 'http://localhost:5555';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.loadOrders();
                }
            }, function errorCallback(response) {
                alert(response.data.error);
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
        $scope.myOrders = null;
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            $scope.username = $localStorage.springWebUser.username;
            return true;
        } else {
            $scope.username = null;
            return false;
        }
    };

    $scope.getProducts = function () {
        $http.get(contextPath + '/core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                // console.log(response);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/core/api/v1/products/' + id)
            .then(function (response) {
                $scope.getProducts();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post(contextPath + '/core/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.getProducts();
            });
    }

    $scope.getCategories = function () {
        $http.get(contextPath + '/core/api/v1/categories')
            .then(function (response) {
                $scope.categoryTitles = response.data;
            });
    }

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/cart/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.subtractFromCart = function (productId) {
        $http.get(contextPath + '/cart/api/v1/cart/subtract/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.removeFromCart = function (productId) {
        $http.get(contextPath + '/cart/api/v1/cart/remove/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/api/v1/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.checkOut = function () {
        $http({
            url: contextPath + '/core/api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            $scope.loadCart();
            $scope.orderDetails = null;
            $scope.loadOrders();
        });
    }

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись.");
    }

    $scope.loadOrders = function () {
        $http.get(contextPath + '/core/api/v1/orders')
            .then(function (response) {
                $scope.myOrders = response.data;
            });
    }

    if ($localStorage.springWebUser) {
        try {
            let jwt = $localStorage.springWebUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.springWebUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        if ($localStorage.springWebUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
            $scope.loadOrders();
        }
    }

    $scope.getProducts();
    $scope.getCategories();
    $scope.loadCart();
});
