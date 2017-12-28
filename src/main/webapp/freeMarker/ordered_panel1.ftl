<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
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

<div class="container-fluid">
    <div class="accordion" id="accordion2">

        <div class="accordion-group">
            <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                    当前
                </a>
            </div>
            <div id="collapseOne" class="accordion-body collapse" style="height: 0px; ">
                <div class="accordion-inner">
                    <#list temporaryOrderList as temporaryOrder>
                        ${temporaryOrder.finished}
                        <#if temporaryOrder.finished = false>
                            ${temporaryOrder.id}<br>
                        </#if>
                    </#list>
                </div>
            </div>
        </div>
        <div class="accordion-group">
            <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                    已点
                </a>
            </div>
            <div id="collapseOne" class="accordion-body collapse" style="height: 0px; ">
                <div class="accordion-inner">

                </div>
            </div>
        </div>
    </div>
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