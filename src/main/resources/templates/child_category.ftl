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
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${base}/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${base}/js/bootstrap.js"></script>

    <script src="${base}/js/common.js"></script>

    <style>
        .article-metadata-item {
            margin-right: 15px;
        }

        .article-metadata-description {
            margin-top: 10px;
        }

        .article-metadata-title{
            font-size: 25px;
            font-weight: 700;
            color: dodgerblue;
        }
    </style>

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
                        <#if category.id == parentCategory.id>
                           <li class="active"><a href="${base}/parent/category/${category.id}">${category.categoryName}</a>
                           </li>
                        </#if>
                        <#if category.id != parentCategory.id>
                          <li><a href="${base}/parent/category/${category.id}">${category.categoryName}</a></li>
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
                     <div class="col-xs-12 col-lg-12">
                         <#list articles as article>
                             <div class="panel panel-info">
                                 <div class="panel-heading">
                                     <h3 class="panel-title article-metadata-title"><a
                                             href="${base}/content/${article.id}">${article.title}</a></h3>
                                 </div>
                                 <div class="panel-body">
                                     <div>
                                         <span class="article-metadata-item"><i><object data="${base}/svg/clock.svg"
                                                                                        type="image/svg+xml"></object></i>&nbsp;&nbsp;${article.createTime}</span>
                                         <span class="article-metadata-item"><i><object data="${base}/svg/eye.svg"
                                                                                        type="image/svg+xml"></object></i>&nbsp;&nbsp;${article.views}</span>
                                         <span class="article-metadata-item"><i><object data="${base}/svg/tag.svg"
                                                                                        type="image/svg+xml"></object></i>&nbsp;
                                             <a href="${base}/parent/category/${article.parentCategoryId}">${article.parentCategoryName}</a>
                                         </span>
                                         <#if article.childCategoryId != 1>
                                         <span class="article-metadata-item"><i><object data="${base}/svg/tag.svg"
                                                                                        type="image/svg+xml"></object></i>&nbsp;
                                             <a href="${base}/child/category/${article.childCategoryId}">${article.childCategoryName}</a>
                                         </span>
                                         </#if>
                                     </div>
                                     <div>
                                         <p class="article-metadata-description">${article.description}</p>
                                     </div>
                                 </div>
                             </div>
                         </#list>
                     </div>
                 </#if>
            </div>
        </div>
    </div>
    <p style="text-align: right;padding-right: 30px">
        <a title="返回顶部" id="return-to-top"><i class="fa fa-3x fa-arrow-up"></i></a>
    </p>
    <hr>
    <footer>
        <p style="text-align: right">
            <a title="返回顶部" id="return-to-top"><i class="fa fa-2x fa-arrow-up"></i></a>
        </p>
        <p style="text-align: center">
            <a href="https://github.com/zjcscut" target="_blank">
                <i class="fa fa-2x fa-github"></i>
            </a>
        </p>
        <p style="text-align: center" id="copy-right"></p>
    </footer>
</div>

<script src="${base}/js/category.js"></script>

</body>
</html>