<!DOCTYPE html>
<#assign base = context.contextPath>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Throwable's Blog</title>
    <link rel="shortcut icon" href="${base}/img/favicon.ico"/>

    <!-- Bootstrap -->
    <link href="${base}/css/bootstrap.css" rel="stylesheet">
    <link href="${base}/css/catetory.css" rel="stylesheet">
    <link href="${base}/css/octicons.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${base}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${base}/js/bootstrap.js"></script>

    <script src="${base}/js/common.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${base}/index">Throwable's Blog</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <#if categories?exists>
                    <li><a href="${base}/index">Home</a></li>
                    <#list categories as category>
                        <#if category.id == id>
                           <li class="active"><a href="${base}/category/${category.id}">${category.categoryName}</a>
                           </li>
                        </#if>
                        <#if category.id != id>
                          <li><a href="${base}/category/${category.id}">${category.categoryName}</a></li>
                        </#if>
                    </#list>
                </#if>
            </ul>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="jumbotron">
                <h1>${category.categoryName}</h1>
                <p>${category.description}</p>
            </div>
            <div class="row">
                 <#if articles?exists>
                     <div class="col-xs-6 col-lg-12">
                         <#list articles as article>
                             <h3><a href="${base}/content/${article.id}">${article.title}</a></h3>
                             <p class="col-xs-6 col-lg-9">${article.description}</p>
                             <span class="col-xs-6 col-lg-3">${article.createTime}</span>
                         </#list>
                     </div>
                 </#if>
            </div>
        </div>

        <#if categoryCounts?exists>
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="category-sidebar">
            <div class="list-group">
                   <#list categoryCounts as categoryCount>
                       <a href="${base}/category/${categoryCount.categoryId}"
                          class="list-group-item">${categoryCount.categoryName}<span
                               class="badge">${categoryCount.totalCount}</span></a>
                   </#list>
            </div>
        </div>
        </#if>
    </div>

    <hr>

    <footer>
        <p style="text-align: center" id="copy-right"></p>
    </footer>
</div>

<script src="${base}/category.js"></script>

</body>
</html>