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
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${base}/js/jquery.min.js"></script>
    <script src="${base}/js/bootstrap.js"></script>

    <script src="${base}/js/common.js"></script>

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
            width: 400px;
            height: auto;
            position: fixed;
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
            padding-right: 430px;
            margin: 0;
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
            <#--<#if categories?exists>-->
            <#--<li><a href="${base}/index">Home</a></li>-->
            <#--<#list categories as category>-->
            <#--<#if category.id == parentCategoryId>-->
            <#--<li class="active"><a href="${base}/category/${category.id}">${category.categoryName}</a>-->
            <#--</li>-->
            <#--</#if>-->
            <#--<#if category.id != parentCategoryId>-->
            <#--<li><a href="${base}/category/${category.id}">${category.categoryName}</a></li>-->
            <#--</#if>-->
            <#--</#list>-->
            <#--</#if>-->
            </ul>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container-fluid">
    <div id="layout">
        <header>
            <h1>Markdown转HTML的显示处理之自定义 ToC 容器</h1>
            <p>即：非编辑情况下的HTML预览</p>
            <p>HTML Preview (markdown to html and custom ToC container)</p>
        </header>
        <div id="sidebar">
            <h1>目录</h1>
            <div class="markdown-body editormd-preview-container" id="markdown-toc-container"></div>
        </div>
        <div id="markdown-content">
            <textarea style="display:none;" name="markdown-content-doc"></textarea>
        </div>
    </div>
    <hr>
    <footer>
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
        $.get("${base}/readme.md", function (markdown) {
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
</body>
