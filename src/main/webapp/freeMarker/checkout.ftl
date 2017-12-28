<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>结账</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_488981_gsrfz4w9muslwhfr.css">


</head>
<body>

<div class="page-group">
<#--页面开始-->
    <div class="page">
    <#--顶部信息 餐桌号展示-->
        <header class="bar bar-nav">
            <h1 class="title">0&nbsp;号餐桌</h1>
        </header>
    <#--底部导航-->
        <nav class="bar bar-tab">
            <a class="tab-item" href="#" onclick="window.location='${commonBaseUrl}/index.html'">
                <span class="icon icon-home"></span>
                <span class="tab-label">菜单</span>
            </a>
            <a class="tab-item " href="#" onclick="window.location='${commonBaseUrl}/ordered.html'">
                <span class="icon icon-me"></span>
                <span class="tab-label">已点</span>
            </a>
            <a class="tab-item active" href="#" onclick="window.location='${commonBaseUrl}/checkout.html'">
                <span class="icon icon-star"></span>
                <span class="tab-label">结账</span>
            </a>
        </nav>
        <div style="width: 100%;  position:absolute; bottom:70px; z-index:99;">
            <div class="content-block" style="margin: 0;">
                <div class="row">
                    <div class="col-33"><a href="#" class="button button-big button-fill button-success " style="height: 1.7rem; line-height: 1.8rem;" onclick="window.location='${commonBaseUrl}/index.html'">还没吃饱</a></div>
                    <div class="col-33"><a href="#" class="button button-big " style="height: 1.7rem; line-height: 1.8rem;">总价：￥ ${totals / 100}</a></div>
                    <div class="col-33"><a href="#" class="button button-big button-fill button-success"
                                           style="height: 1.7rem; line-height: 1.8rem;" onclick="orders(this)">结账</a></div>
                </div>
            </div>
        </div>
    <#--页面内容展示-->
        <div class="content" style="margin-bottom: 55px;">
        <#--<iframe src="${commonBaseUrl}/ordered_panel1.html" frameborder="0" style="width: 100%; height: 100%;"></iframe>-->
            <div class="list-block">
                <ul>
                <#list orderedVO.temporaryOrderVOList as temporaryOrderVO>
                    <li class="item-content">
                        <div class="item-media"><i class="icon icon-f7"></i></div>
                        <div class="item-inner">
                            <div class="row" style="width: 100%">
                                <div class="col-33">${temporaryOrderVO.foodName}</div>
                                <div class="col-33">
                                    <div>
                                        <span id="temporaryOrderId" hidden>${temporaryOrderVO.id}</span>
                                        <span id="temporaryOrderNumber">${temporaryOrderVO.number}</span>
                                    </div>
                                </div>
                                <div class="col-33">￥${temporaryOrderVO.totalPrice / 100}</div>
                            </div>
                        </div>
                    </li>
                </#list>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>

<#--<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>-->
<#--<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->

<script src="${commonBaseUrl}/js/common.js"></script>
</body>
<script>
    // TODO 获取当前登录用户绑定的餐桌 ID
    var dinnerTableId = 1;
    // 根据餐桌 ID 显示标题
    $(".bar.bar-nav .title").html(dinnerTableId + "&nbsp;号餐桌");
    function orders() {

        request('${commonBaseUrl}/orders/', requestType.POST, dataType.JSON, undefined, function () {
            location.reload();
        });
    }

</script>
</html>