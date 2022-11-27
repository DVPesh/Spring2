angular.module('market').controller('storeController', function ($scope, $http, $localStorage) {

    const contextPath = 'http://localhost:5555';

    $scope.getProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/core/api/v1/products',
            method: 'GET',
            params: {
                page: pageIndex,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                title: $scope.filter ? $scope.filter.title : null,
                category: $scope.filter ? $scope.filter.category : null
            }
        }).then(function (response) {
            $scope.products = response.data.content;
            $scope.paginationArray = $scope.generatePagesIndexes(1, response.data.totalPages);
            // console.log(response);
        });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

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

    $scope.getProducts();
    $scope.getCategories();
});
