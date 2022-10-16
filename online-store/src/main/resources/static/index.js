angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.getProducts = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                // console.log(response);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.getProducts();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.getProducts();
            });
    }

    $scope.getCategories = function () {
        $http.get('http://localhost:8189/market/api/v1/categories')
            .then(function (response) {
                $scope.categoryTitles = response.data;
            });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/market/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8189/market/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            })
    }

    $scope.getProducts();
    $scope.getCategories();
    $scope.loadCart();
});