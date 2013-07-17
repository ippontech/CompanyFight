<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body ng-app>
<!--[if lt IE 7]>
<p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to
    improve your experience.</p>
<![endif]-->

<div class="container">
    <a href="https://github.com/ippontech/CompanyFight"><img style="position: absolute; top: 0; right: 0; border: 0;" src="https://s3.amazonaws.com/github/ribbons/forkme_right_red_aa0000.png" alt="Fork me on GitHub"></a>

    <div class="well text-center">
        <h1>30 Latest Fights</h1>
        <a href="https://twitter.com/share" class="twitter-share-button" data-text="Have a fight with my company at http://fight.ippon.fr" data-count="none">Tweet</a>
        <br/>
        [ <a href="<%=request.getContextPath()%>/fight">Fight</a> | <a href="<%=request.getContextPath()%>/high-scores">High scores</a> ]
        <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>

    </div>

    <table class="table table-striped">
        <thead>
            <td>Company #1</td>
            <td>Company #2</td>
            <td></td>
        </thead>
        <c:forEach var="fight" items="${fights}">
            <tr>
                <td>
                    <a href="https://github.com/${fight.organization1.id}" target="_blank">
                        <img src="${fight.organization1.avatarUrl}" alt="" class="img-rounded img-small">
                        ${fight.organization1.id}
                    </a>
                </td>
                <td>
                    <a href="https://github.com/${fight.organization2.id}" target="_blank">
                        <img src="${fight.organization2.avatarUrl}" alt="" class="img-rounded img-small">
                            ${fight.organization2.id}
                    </a>
                </td>
                <td><a href="<%=request.getContextPath()%>/fight#${fight.organization1.id}/${fight.organization2.id}">Fight again</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- /container -->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/js/vendor/jquery-1.10.1.min.js"><\/script>')</script>

<script src="<%=request.getContextPath()%>/js/vendor/bootstrap.min.js"></script>

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