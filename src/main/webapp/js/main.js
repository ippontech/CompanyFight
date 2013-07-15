function FightController($scope, $http) {
    $scope.master = {};

    $scope.fight = function (company1, company2) {
        location.hash = '#' + company1.name + '/' + company2.name;
        $scope.fetchData(company1);
        $scope.fetchData(company2);
    };

    $scope.fetchData = function (company) {
        company.error = "";
        company.avatarUrl = "";
        company.status = "Fetching data for " + company.name + "...";
        $http.get('rest/organizations/' + company.name).success(function (data, status, headers, config) {
            company.status = "";
            if (status == 204) {
                company.error = "Error! Company does not exist!";
            } else {
                company.avatarUrl = data.avatarUrl;
                company.repositories = data.repositories;
                company.members = data.members;
                company.score = calculateScore(company);
            }
        }).error(function (data, status, headers, config) {
                company.status = "";
                company.error = "Error! Data could not be retrieved!";
        });
    };

    $scope.reset = function () {
        $scope.company1 = angular.copy($scope.master);
        $scope.company2 = angular.copy($scope.master);
        location.hash = '';
    };

    var hash = location.hash.slice(1);
    if (hash != '') {
        $scope.company1 = angular.copy($scope.master);
        $scope.company2 = angular.copy($scope.master);
        var companies = hash.split('/');
        if (companies[0] != null && companies[1] != null) {
            $scope.company1.name = companies[0];
            $scope.company2.name = companies[1];
        } else {
            $scope.reset();
        }
    } else {
        $scope.reset();
    }
}

// The score is : (number of repositories + 0.5 bonus per fork) + (number of users + 0.5 bonus per follower)
function calculateScore(company) {
    score = 0;
    for(var i= 0; i < company.repositories.length; i++) {
        score++;
        score += company.repositories[i].forks * 0.5;
    }
    for(var i= 0; i < company.members.length; i++) {
        score ++;
        score += company.members[i].followersCount * 0.5;
    }
    return score;
}
