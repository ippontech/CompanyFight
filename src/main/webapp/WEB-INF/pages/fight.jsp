<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" xmlns="http://www.w3.org/1999/html"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
    </style>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

    <script src="<%=request.getContextPath()%>/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>
</head>
<body>
<!--[if lt IE 7]>
<p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to
    improve your experience.</p>
<![endif]-->

<div class="container" ng-app>

    <a href="https://github.com/ippontech/CompanyFight"><img style="position: absolute; top: 0; right: 0; border: 0;" src="https://s3.amazonaws.com/github/ribbons/forkme_right_red_aa0000.png" alt="Fork me on GitHub"></a>

    <div class="well text-center">
        <h1>Github Company fight</h1>
        <p>
            This application fetches data from Github (repositories, forks, members, ...) and calculates which company is the best on Github
        </p>
        <a href="https://twitter.com/share" class="twitter-share-button" data-text="Have a fight with my company at http://fight.ippon.fr" data-count="none">Tweet</a>
        <br/>
        [ <a href="<%=request.getContextPath()%>/latest-fights">Latest fights</a> | <a href="<%=request.getContextPath()%>/high-scores">High scores</a> ]
        <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
    </div>
    <form novalidate class="simple-form">
        <div ng-controller="FightController">
            <div class="row">
                <div class="span5 text-center">
                    <h2>Company #1</h2>

                    <input type="text" ng-model="company1.name" placeholder="Enter company #1 Github name">
                    <br/>

                    <div ng-show="company1.status" class="alert alert-success">
                        {{company1.status}}
                    </div>
                    <div ng-show="company1.error" class="alert alert-error">
                        {{company1.error}}
                    </div>
                </div>

                <div class="span2 text-center">
                    <br/>
                    <br/>
                    <button ng-click="fight(company1,company2)" class="btn btn-primary btn-large">Fight!</button>
                </div>

                <div class="span5 text-center">
                    <h2>Company #2</h2>

                    <input type="text" ng-model="company2.name" placeholder="Enter company #2 Github name">
                    <br/>

                    <div ng-show="company2.status" class="alert alert-success">
                        {{company2.status}}
                    </div>
                    <div ng-show="company2.error" class="alert alert-error">
                        {{company2.error}}
                    </div>
                </div>
            </div>
            <div ng-show="company1.avatarUrl == null || company2.avatarUrl == null">
                <div class="row">
                    <div class="span12 text-center">
                        <img src="<%=request.getContextPath()%>/img/fight.jpg"/>
                    </div>
                </div>
            </div>
            <div ng-show="company1.score > company2.score">
                <div class="row">
                    <div class="span6 text-center">
                        <div class="alert alert-block alert-success">
                            <a href="https://github.com/{{company1.name}}" target="_blank">
                                <h2><img src="{{company1.avatarUrl}}" alt="" class="img-rounded img-medium"> Winner</h2>
                                <p>Score : {{company1.score}}</p>
                            </a>
                        </div>
                    </div>
                    <div class="span6 text-center">
                        <div class="alert alert-block alert-error">
                            <a href="https://github.com/{{company2.name}}" target="_blank">
                                <h2><img src="{{company2.avatarUrl}}" alt="" class="img-rounded img-medium"> Loser</h2>
                                <p>Score : {{company2.score}}</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div ng-show="company1.score < company2.score">
                <div class="row">
                    <div class="span6 text-center">
                        <div class="alert alert-block alert-error">
                            <a href="https://github.com/{{company1.name}}" target="_blank">
                                <h2><img src="{{company1.avatarUrl}}" alt="" class="img-rounded img-medium"> Loser</h2>
                                <p>Score : {{company1.score}}</p>
                            </a>
                        </div>
                    </div>
                    <div class="span6 text-center">
                        <div class="alert alert-block alert-success">
                            <a href="https://github.com/{{company2.name}}" target="_blank">
                              <h2><img src="{{company2.avatarUrl}}" alt="" class="img-rounded img-medium"> Winner</h2>
                              <p>Score : {{company2.score}}</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="span6 text-center">
                    <div ng-show="company1.avatarUrl">
                        <h3>Repositories</h3>
                        <table class="table table-striped">
                            <thead>
                            <td>Url</td>
                            <td>Forks</td>
                            </thead>
                            <tr ng-repeat="repository in company1.repositories">
                                <td><a href="{{repository.url}}" target="_blank">{{repository.url}}</a></td>
                                <td>{{repository.forks}}</td>
                            </tr>
                        </table>
                        <h3>Members</h3>
                        <table class="table table-striped">
                            <thead>
                            <td></td>
                            <td>Name</td>
                            <td>Followers</td>
                            </thead>
                            <tr ng-repeat="person in company1.members">
                                <td><img src="{{person.avatarUrl}}" alt="" class="img-rounded img-small"></td>
                                <td><a href="https://github.com/{{person.login}}" target="_blank">{{person.login}}</a>
                                </td>
                                <td>{{person.followersCount}}</td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="span6 text-center">
                    <div ng-show="company2.avatarUrl">
                        <h3>Repositories</h3>
                        <table class="table table-striped">
                            <thead>
                            <td>Url</td>
                            <td>Forks</td>
                            </thead>
                            <tr ng-repeat="repository in company2.repositories">
                                <td><a href="{{repository.url}}" target="_blank">{{repository.url}}</a></td>
                                <td>{{repository.forks}}</td>
                            </tr>
                        </table>
                        <h3>Members</h3>
                        <table class="table table-striped">
                            <thead>
                            <td></td>
                            <td>Name</td>
                            <td>Followers</td>
                            </thead>
                            <tr ng-repeat="person in company2.members">
                                <td><img src="{{person.avatarUrl}}" alt="" class="img-rounded img-small"></td>
                                <td><a href="https://github.com/{{person.login}}" target="_blank">{{person.login}}</a>
                                </td>
                                <td>{{person.followersCount}}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <hr>

    <footer>
        <p><a href="http">&copy; Ippon Technologies 2013</a></p>
    </footer>

</div>
<!-- /container -->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/js/vendor/jquery-1.10.1.min.js"><\/script>')</script>

<script src="<%=request.getContextPath()%>/js/vendor/bootstrap.min.js"></script>

<script src="<%=request.getContextPath()%>/js/main.js"></script>

<script>
    var _gaq = [
        ['_setAccount', 'UA-XXXXX-X'],
        ['_trackPageview']
    ];
    (function (d, t) {
        var g = d.createElement(t), s = d.getElementsByTagName(t)[0];
        g.src = '//www.google-analytics.com/ga.js';
        s.parentNode.insertBefore(g, s)
    }(document, 'script'));
</script>
</body>
</html>
