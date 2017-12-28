<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Bootstrap 实例 - 折叠面板</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_488981_gsrfz4w9muslwhfr.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        a:hover{
            text-decoration: none;
        }
        a{
            text-decoration:none;
        }
        a:-webkit-any-link {
            text-decoration: none;
        }
        .panel-heading{
            text-align: center;
        }
        .panel-body{
            text-align: center;
        }
    </style>
</head>
<body>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <div class="row">
                    <div class="col-md-4">
                        <a data-toggle="collapse" data-parent="#accordion" style="text-decoration: none; color: black;" href="#">菜名</a>
                    </div>
                    <div class="col-md-4">数量</div>
                    <div class="col-md-4">总价</div>
                </div>
            </h4>
        </div>
    </div>
<#list temporaryOrderList as temporaryOrder>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <div class="row">
                    <div class="col-md-4">
                        <#list foodList as food>
                        <#if temporaryOrder.foodId == food.id>
                        <a data-toggle="collapse" data-parent="#accordion" style="text-decoration: none; color: black;" href="#${temporaryOrder.id}">
                            ${food.name}
                        </a>
                        </#if>
                        </#list>
                    </div>
                    <div class="col-md-4"><div>
                        <a href="#" onclick="updateTemporaryOrders(this, -1)"><span
                                class="iconfont icon-jian"></span></a>&nbsp;
                        <#assign temporaryOrderNumber = 0 />
                                <#assign temporaryOrderNumber = temporaryOrder.number/>
                                <span id="temporaryOrderId" hidden>${temporaryOrder.id}</span>
                                <#if temporaryOrder.number != 0>
                                    <span id="temporaryOrderNumber">${temporaryOrder.number}</span>
                                </#if>
                        <#if temporaryOrderNumber == 0>
                            <span id="temporaryOrderNumber">0</span>
                        </#if>
                        &nbsp;<a href="#"
                                 onclick="updateTemporaryOrders(this, 1)"><span
                            class="iconfont icon-jia"></span></a>
                    </div></div>
                    <div class="col-md-4">￥${temporaryOrder.totalPrice / 100}</div>
                </div>
            </h4>
        </div>
        <div id="${temporaryOrder.id}" class="panel-collapse collapse">
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-4">单号：${temporaryOrder.id}</div>
                    <div class="col-md-4">更新时间：${(temporaryOrder.updatedTime?string("yyyy-MM-dd HH:mm:ss"))!}</div>
                    <#list foodList as food>
                    <#if temporaryOrder.foodId == food.id>
                    <div class="col-md-4">单价:￥${food.price / 100}</div>
                    </#if>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</#list>

</div>

</body>
<script>
    // 生成临时订单
    function updateTemporaryOrders(actionElement, actionNumber) {
        // 当前页面改变数值
        var temporaryOrderNumberElement = $(actionElement).parent().children("#temporaryOrderNumber");
        var temporaryOrderId = Number($(actionElement).parent().children("#temporaryOrderId").text());
        if (temporaryOrderId == null) {
            temporaryOrderId = 0;
        }
        var temporaryOrderNumber = Number(temporaryOrderNumberElement.text()) + actionNumber;
        if (temporaryOrderNumber < 0) {
            temporaryOrderNumber = 0;
        }
        temporaryOrderNumberElement.text(temporaryOrderNumber);

        // 将改变后的值传递到后台
        request("${commonBaseUrl}/temporaryOrders/" + temporaryOrderId, requestType.PUT, dataType.JSON, {"number": temporaryOrderNumber,}, function () {

        });
    }
</script>
</html>