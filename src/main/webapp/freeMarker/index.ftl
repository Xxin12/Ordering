<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>菜单</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_488981_gsrfz4w9muslwhfr.css">

    <style>
        /* 左侧列表 start */
        #foodTypeList {
            float: left;
            height: 100%;
            background-color: white;
        }

        .list-block {
            margin: 0px;
        }

        .list-block .item-content {
            padding-left: 0px;
        }

        .list-block .item-inner:hover {
            background-color: #e7e7e7;
            cursor: hand;
        }

        .list-block .item-media + .item-inner {
            margin-left: 0px;
            padding-left: 0.75rem
        }

        .list-block .item-link .item-inner {
            background-image: none;
        }

        /* 左侧列表 end */

        /*食物列表 start*/

        /*食物列表头部 start*/
        .foodList-head {
            height: 8%;
        }

        .foodList-head-foodname {
            float: left;
            font-size: 1rem;
            padding-top: 5px;
            padding-left: 10px;
        }

        /*搜索栏 start*/
        .foodList .searchbar {
            float: right;
            border: hidden;
            box-sizing: boder-box;
            height: 48px;
        }

        .searchbar .search-input {
            float: right;
            height: 30px;
            width: 300px;
        }

        .searchbar .searchbar-cancel {
            padding-right: 10px;
        }

        /*搜索栏 end*/
        /*食物列表头部 end*/

        /*食品列表中间 start*/
        .foodList-body {
            height: 42%;
            clear: both;
        }

        .foodList-body-card {
            width: 20%;
            height: 100%;
            float: left;
        }

        .foodList-body-card .card {
            margin: 0 10px;
        }

        .foodList-body-card .card .card-content {
            text-align: center;
            padding: 5px;
            overflow: hidden;
        }

        /*.iconfont.icon-jian:hover {*/
        /*cursor: hand;*/
        /*}*/

        /*.iconfont.icon-jia:hover {*/
        /*cursor: hand;*/
        /*}*/

        /*食品列表中间 end*/
        /*食品列表底部 start*/
        .foodList-foot {
            height: 8%;
        }

        .foodList-foot .iconfont {
            background-color: white;
            float: right;
            margin: 5px;;
            padding: 5px 20px;
        }

        .foodList-foot .iconfont:hover {
            background-color: #e7e7e7;
        }

        .foodList-foot .paging {
            float: right;
            padding: 0 8px;
            margin: 5px 0;
        }

        /*食品列表底部 end*/
        /*食物列表 end*/
    </style>
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
            <a class="tab-item active" href="#" onclick="window.location='${commonBaseUrl}/index.html'">
                <span class="icon icon-home"></span>
                <span class="tab-label">菜单</span>
            </a>
            <a class="tab-item" href="#" onclick="window.location='${commonBaseUrl}/ordered.html'">
                <span class="icon icon-me"></span>
                <span class="tab-label">已点</span>
            </a>
            <a class="tab-item" href="#" onclick="window.location='${commonBaseUrl}/checkout.html'">
                <span class="icon icon-star"></span>
                <span class="tab-label">结账</span>
            </a>
        </nav>
    <#--页面内容展示-->
        <div class="content">
            <div class="row no-gutter">
            <#--食品分类列表-->
                <div id="foodTypeList" class="col-20">
                    <div class="list-block">
                        <ul>
                        <#list foodTypeList as foodType>
                            <li>
                                <a class="item-content item-link"
                                   onclick="location='${commonBaseUrl}/index.html?foodTypeId=${foodType.id}'">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div hidden>${foodType.id}</div>
                                        <div class="item-title">
                                            <span class="iconfont <#--${foodType.iconUrl}-->"></span>&nbsp;${foodType.name}
                                        </div>
                                    </div>
                                </a>
                            </li>
                        </#list>

                        </ul>
                    </div>
                </div>
            <#--食物展示-->
                <div id="food" class="col-80">
                    <div id="foodList">
                    <#--食品分类信息和搜索栏-->
                        <div class="foodList-head">
                            <div class="foodList-head-foodname">
                                <span class="iconfont<#-- ${foodType.iconUrl}-->"></span>&nbsp;${(foodType.name)!"搜索结果"}
                            </div>
                            <div class="searchbar">
                                <div class="search-input" style="margin-right: 10px;">
                                    <label class="icon icon-search" for="search"></label>
                                    <input type="search" id='searchName' placeholder='输入关键字...' value="${foodName!}"/>
                                </div>
                            </div>
                        </div>
                    <#--食品展示列表-->
                        <div class="foodList-body">
                        <#list foodList as food>
                            <#if food_index lt 5>
                                <div class="foodList-body-card">
                                    <div class="card">
                                        <div class="card-header color-white no-border no-padding" style="height: 70%;">
                                            <div style="width: 100%;height: 100%; background-image:url(${food.imageUrl});background-position:center;background-size:cover"></div>
                                        </div>
                                        <div class="card-content">
                                            <div>
                                            ${food.name}<span class="food_price"
                                                              style="float: right">￥${food.price / 100}</span>
                                            </div>
                                            <div>
                                                <a href="#" onclick="updateTemporaryOrders(this, -1)"><span
                                                        class="iconfont icon-jian"></span></a>&nbsp;
                                                <#assign temporaryOrderNumber = 0 />

                                                <#list orderedVO.temporaryOrderVOList as temporaryOrderVO>
                                                    <#if temporaryOrderVO.foodId == food.id >
                                                        <#assign temporaryOrderNumber = temporaryOrderVO.number/>
                                                        <span id="temporaryOrderId" hidden>${temporaryOrderVO.id}</span>
                                                        <span id="foodId" hidden>${food.id}</span>
                                                        <#if temporaryOrderVO.number != 0>
                                                        <span id="temporaryOrderNumber">${temporaryOrderVO.number}</span>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                                <#if temporaryOrderNumber == 0>
                                                    <span id="temporaryOrderNumber">0</span>
                                                    <span id="foodId" hidden>${food.id}</span>
                                                </#if>
                                                &nbsp;<a href="#"
                                                         onclick="updateTemporaryOrders(this, 1)"><span
                                                    class="iconfont icon-jia"></span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </#list>
                        </div>
                        <div class="foodList-body">
                        <#list foodList as food>
                            <#if food_index gt 4>
                                <div class="foodList-body-card">
                                    <div class="card">
                                        <div class="card-header color-white no-border no-padding" style="height: 70%;">
                                            <div style="width: 100%;height: 100%; background-image:url(${food.imageUrl});background-position:center;background-size:cover"></div>
                                        </div>
                                        <div class="card-content">
                                            <div>
                                            ${food.name}<span class="food_price" style="float: right">￥${food.price / 100}</span>
                                            </div>
                                            <div>
                                                <a href="#" onclick="updateTemporaryOrders(this, -1)"><span
                                                        class="iconfont icon-jian"></span></a>&nbsp;
                                                <#assign temporaryOrderNumber = 0 />

                                                <#list orderedVO.temporaryOrderVOList as temporaryOrderVO>
                                                    <#if temporaryOrderVO.foodId == food.id >
                                                        <#assign temporaryOrderNumber = temporaryOrderVO.number/>
                                                        <span id="temporaryOrderId" hidden>${temporaryOrderVO.id}</span>
                                                        <span id="foodId" hidden>${food.id}</span>
                                                        <#if temporaryOrderVO.number != 0>
                                                            <span id="temporaryOrderNumber">${temporaryOrderVO.number}</span>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                                <#if temporaryOrderNumber == 0>
                                                    <span id="temporaryOrderNumber">0</span>
                                                    <span id="foodId" hidden>${food.id}</span>
                                                </#if>
                                                &nbsp;<a href="#"
                                                         onclick="updateTemporaryOrders(this, 1)"><span
                                                    class="iconfont icon-jia"></span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </#list>
                        </div>
                    <#--列表底部分页信息-->
                        <div class="foodList-foot">
                            <div>
                                <a id="nextPage" class="iconfont icon-you"
                                   onclick="location = '${commonBaseUrl}/index.html?foodTypeId=${(foodType.id)!}&foodName=${foodName!}&current=${page.current + 1}'"></a>
                                <span class="paging">${page.current}</span>
                                <a id="previousPage" class="iconfont icon-zuo"
                                   onclick="location = '${commonBaseUrl}/index.html?foodTypeId=${(foodType.id)!}&foodName=${foodName!}&current=${page.current - 1}'"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>

<script src="${commonBaseUrl}/js/common.js"></script>
<script>
    // TODO 获取当前登录用户绑定的餐桌 ID
    var dinnerTableId = 1;
    // 根据餐桌 ID 显示标题
    $(".bar.bar-nav .title").html(dinnerTableId + "&nbsp;号餐桌");

    // 重新设置左侧列表每一个元素的高度
    $("#foodTypeList .item-inner").css("min-height", (1 / $("#foodTypeList .item-inner").length * 100) + "%");
    // 根据食品分类显示选中效果
    var foodTypeNames = $(".item-title");
    foodTypeNames.each(function () {
        if ($(this).prev().text() == "${(foodType.id)!}") {
            $(".list-block .item-inner").css("background-color", "");
            $(this).parent().css("background-color", "#e7e7e7");
        }
    });
    // 分页跳转按钮的隐藏
    if (${page.current}== 1
    )
    {
        $("#previousPage").hide();
    }
    if (parseInt(${page.total} / ${page.size}) +1 == ${page.current}
    )
    {
        $("#nextPage").hide();
    }

    // 根据食品名称查询食品
    $("#searchName").bind("keypress", function (event) {
        if (event.keyCode == 13) {
            window.location = '${commonBaseUrl}/index.html?foodName=' + $(this).val();
        }
    });

    // 生成临时订单
    function updateTemporaryOrders(actionElement, actionNumber, actionFoodId) {
        // 当前页面改变数值
        var temporaryOrderNumberElement = $(actionElement).parent().children("#temporaryOrderNumber");
        var temporaryOrderId =  Number($(actionElement).parent().children("#temporaryOrderId").text());
        var foodId = Number($(actionElement).parent().children("#foodId").text());
        if (temporaryOrderId == null){
            temporaryOrderId = 0;
        }
        var temporaryOrderNumber = Number(temporaryOrderNumberElement.text()) + actionNumber;
        if (temporaryOrderNumber < 0) {
            temporaryOrderNumber = 0;
        }
        temporaryOrderNumberElement.text(temporaryOrderNumber);

        // 将改变后的值传递到后台
        request("${commonBaseUrl}/temporaryOrders/" + temporaryOrderId, requestType.PUT, dataType.JSON,
                {"number": temporaryOrderNumber,"foodId": foodId}, function () {
                    location.reload();
        });
    }

</script>
</body>
</html>
