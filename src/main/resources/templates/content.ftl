<!DOCTYPE html>
<#assign base = context.contextPath>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Throwable's Blog</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <link href="css/ztree.css" rel="stylesheet">
    <style>
        /*.ztree {*/
            /*position: fixed;*/
            /*top: 50px;*/
            /*bottom: 0;*/
            /*overflow-y: auto;*/
            /*padding-top: 30px;*/
            /*padding-left: 15px;*/
            /*padding-bottom: 30px;*/
            /*width: 300px;*/
            /*color: #364149;*/
            /*background: #fff;*/
            /*border-right: 1px solid rgba(0,0,0,.07);*/
            /*-webkit-transition: left 250ms ease;*/
            /*-moz-transition: left 250ms ease;*/
            /*-o-transition: left 250ms ease;*/
            /*transition: left 250ms ease;*/
        /*}*/
    </style>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.js"></script>

    <script src="js/common.js"></script>

    <!--strapdownify-->
    <script src="js/strapdown.js"></script>

    <script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="js/ztree_toc.js"></script>
    <!--toc-->
<#--<script src="js/toc.js"></script>-->

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
            <a class="navbar-brand" href="#">Throwable's Blog</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li ><a href="${base}/index">Home</a></li>
                <li class="active"><a href="#">Spring</a></li>
                <li><a href="#">Jdk</a></li>
            </ul>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container-fluid">

    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <ul id="ztree" class="ztree"></ul>
        </div>
        <div class="col-xs-12 col-sm-9" id="content" data-spy="scroll" data-target="#toc">

        </div>
    </div>
    <hr>
    <footer>
        <p style="text-align: center" id="copy-right"></p>
    </footer>

</div><!--/.container-->


<script type="text/javascript">
    $.ajax({
        url: 'test.md',
        type: 'GET',
        dataType: 'text',
        success: function (data) {
            markdownFromText(data, "content");
        }
    });

    //生成Toc
    $(document).ready(function () {
        $('#ztree').ztree_toc({
            is_auto_number: false,
            is_expand_all: true,
            is_highlight_selected_line: false
        });
    });
</script>

<!--toc-->
<#--<script src="js/toc.js"></script>-->

</body>
