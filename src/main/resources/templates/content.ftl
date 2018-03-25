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
    <!-- editor.md -->
    <link rel="stylesheet" href="${base}/css/markdown/style.css"/>
    <link rel="stylesheet" href="${base}/css/markdown/editormd.preview.css"/>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${base}/js/jquery.min.js"></script>
    <script src="${base}/js/bootstrap.js"></script>

    <script src="${base}/js/common.js"></script>
    <script src="${base}/js/content.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->

    <style>
        #layout > header, .btns {
            width: auto;
        }

        #sidebar {
            /*width: 400px;*/
            /*height: auto;*/
            /*position: fixed;*/
            top: 50px;
            right: 0;
            overflow: hidden;
            background: #fff;
            z-index: 100;
            padding: 18px;
            border: 1px solid #ddd;
            border-top: none;
            border-bottom: none;
        }

        #sidebar:hover {
            overflow: auto;
        }

        #sidebar h1 {
            font-size: 16px;
        }

        #markdown-toc-container {
            padding-left: 0;
        }

        #markdown-content {
            padding-left: 0;
            margin: 0;
        }

        .article {

        }

        .article-header {
            text-align: center;
            margin-bottom: 5px;
        }

        .article-metadata {
            margin-top: 10px;
        }

        .article-metadata-item {
            margin-right: 15px;
        }

        .article-title {
            font-weight: 700;
            color: #35a986;
            margin-bottom: 0.5em;
            padding-bottom: 0.3em;
            border-bottom: 1px solid #eee;
        }

        .article-description {
            margin-bottom: 5px;
            color: #4183c4;
        }

        .breadcrumb {
            margin-bottom: 5px;
        }

        .article-type-original {
            background-color: #337ab7;
            padding: 0.1em 0.6em 0.2em;
            font-size: 95%;
            font-weight: bold;
            line-height: 1;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: 1.25em;
        }

        .article-type-reprint {
            background-color: #5cb85c;
            padding: 0.1em 0.6em 0.2em;
            font-size: 95%;
            font-weight: bold;
            line-height: 1;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: 1.25em;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${base}/index">Throwable's Blog</a>`
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
            <#if categories?exists>
                <li><a href="${base}/index">Home</a></li>
            <#list categories as category>
                <#if category.id == article.parentCategoryId>
            <li class="active"><a href="${base}/parent/category/${category.id}">${category.categoryName}</a>
            </li>
                </#if>
                <#if category.id != article.parentCategoryId>
            <li><a href="${base}/parent/category/${category.id}">${category.categoryName}</a></li>
                </#if>
            </#list>
            </#if>
            </ul>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container-fluid">
    <div id="layout">
        <#if article?exists>
            <!-- 面包屑 -->
            <div class="row row-offcanvas row-offcanvas-right">
                <!-- 面包屑 -->
                <ol class="breadcrumb">
                    <#if article.parentCategoryId == homeCategoryId>
                     <li><a href="${base}/index">Home</a></li>
                    <#elseif article.parentCategoryId != homeCategoryId>
                    <li><a href="${base}/parent/category/${article.parentCategoryId}">${article.parentCategoryName}</a>
                    </li>
                    </#if>
                    <!-- 这里要排除首页 -->
                <#if article.childCategoryId != homeCategoryId>
                <li><a href="${base}/child/category/${article.childCategoryId}">${article.childCategoryName}</a></li>
                </#if>
                    <li class="active">${article.title}</li>
                </ol>
                <div class="article article-header">
                    <header>
                        <h1 class="article article-title">${article.title}</h1>
                        <p class="article-description">${article.description}</p>
                        <p class="article article-metadata-item">
                             <#if article.articleType == 1>
                                             <span class="article-type-original article-metadata-item">原创</span>
                             <#elseif article.articleType == 2>
                                             <span class="article-type-reprint article-metadata-item">转载</span>
                             </#if>
                            <#if article.articleType == 2 && article.articleUrl != "NULL">
                            <a href="${article.articleUrl}" target="_blank"><span
                                    class="label label-info article-metadata-item">阅读原文</span></a>
                            </#if>
                            <span class="article article-metadata-item"><i><object data="${base}/svg/person.svg"
                                                                                   type="image/svg+xml"></object></i>&nbsp;&nbsp;${article.author}</span>
                            <span class="article article-metadata-item"><i><object data="${base}/svg/clock.svg"
                                                                                   type="image/svg+xml"></object></i>&nbsp;&nbsp;${article.createTime}</span>
                            <span class="article article-metadata-item"><i><object data="${base}/svg/dashboard.svg"
                                                                                   type="image/svg+xml"></object></i>&nbsp;&nbsp;${article.updateTime}</span>
                            <span class="article article-metadata-item"><i><object data="${base}/svg/eye.svg"
                                                                                   type="image/svg+xml"></object></i>&nbsp;&nbsp;${article.views}</span>
                        </p>
                    </header>
                </div>
                <hr>
                <div class="col-xs-12 col-sm-2 sidebar-offcanvas">
                    <div id="sidebar">
                        <h1>目录</h1>
                        <div class="markdown-body editormd-preview-container" id="markdown-toc-container"></div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10">
                    <div id="markdown-content">
                        <textarea style="display:none;" name="markdown-content-toc"></textarea>
                    </div>
                </div>
            </div>
        </#if>
    </div>
    <p style="text-align: right;padding-right: 30px">
        <a title="返回顶部" id="return-to-top"><i class="fa fa-3x fa-arrow-up"></i></a>
    </p>
    <hr>
    <!-- 底栏 -->
    <footer>
        <p style="text-align: center">
            <a href="https://github.com/zjcscut" target="_blank">
                <i class="fa fa-2x fa-github"></i>
            </a>
        </p>
        <p style="text-align: center" id="copy-right"></p>
    </footer>

</div><!--/.container-->


<script src="${base}/js/marked.min.js"></script>
<script src="${base}/js/prettify.min.js"></script>
<script src="${base}/js/raphael.min.js"></script>
<script src="${base}/js/underscore.min.js"></script>
<script src="${base}/js/sequence-diagram.min.js"></script>
<script src="${base}/js/flowchart.min.js"></script>
<script src="${base}/js/jquery.flowchart.min.js"></script>
<script src="${base}/js/editormd.js"></script>

<script type="text/javascript">
    $(function () {
        var view;
        $.get("${base}/article/content/${article.id}", function (markdown) {
            view = editormd.markdownToHTML("markdown-content", {
                markdown: markdown,//+ "\r\n" + $("#append-test").text(),
                //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                htmlDecode: "style,script,iframe",  // you can filter tags decode
                //toc             : false,
                tocm: true,    // Using [TOCM]
                tocContainer: "#markdown-toc-container", // 自定义 ToC 容器层
                //gfm             : false,
                //tocDropdown     : true,
                // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                emoji: true,
                taskList: true,
                tex: true,  // 默认不解析
                flowChart: true,  // 默认不解析
                sequenceDiagram: true  // 默认不解析
            });
        });
    });
</script>

<!-- JiaThis Button BEGIN -->
<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?move=0&amp;btn=r3.gif"
        charset="utf-8"></script>
<!-- JiaThis Button END -->
</body>
