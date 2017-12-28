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

    <link rel="stylesheet" href="${commonBaseUrl}/css/demo.css">
    <link rel="stylesheet" href="${commonBaseUrl}/css/style.css">

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
            <a class="tab-item active" href="#" onclick="window.location='${commonBaseUrl}/ordered.html'">
                <span class="icon icon-me"></span>
                <span class="tab-label">已点</span>
            </a>
            <a class="tab-item" href="#">
                <span class="icon icon-star"></span>
                <span class="tab-label">结账</span>
            </a>
        </nav>
        <div style="width: 100%;  position:absolute; bottom:70px; z-index:99;">
            <div class="content-block" style="margin: 0;">
                <div class="row">
                    <div class="col-50"><a href="#" class="button button-big " style="height: 1.7rem; line-height: 1.8rem;">总价</a></div>
                    <div class="col-50"><a href="#" class="button button-big button-fill button-success" style="height: 1.7rem; line-height: 1.8rem;">提交订单</a></div>
                </div>
            </div>
        </div>
    <#--页面内容展示-->
        <div class="content" style="bottom: 4.6rem;">

            <div class="container">
                <section class="ac-container">
                    <div>
                        <input id="233" name="accordion-1" type="checkbox" />
                        <label for="233">
                            <div class="row">
                                <div class="col-33">菜名</div>
                                <div class="col-33">数量</div>
                                <div class="col-33">总价</div>
                            </div>
                        </label>
                        <article class="ac-small">
                            <div class="row">
                                <div class="col-33">单号:2017121120480000000</div>
                                <div class="col-33">数量+-</div>
                                <div class="col-33">时间:2017-12-11-20:48:00</div>
                            </div>
                        </article>
                    </div>
                    <div>
                        <input id="ac-2" name="accordion-1" type="checkbox" />
                        <label for="ac-2">How we work</label>
                        <article class="ac-medium">
                            <p>Like you, I used to think the world was this great place where everybody lived by the same standards I did, then some kid with a nail showed me I was living in his world, a world where chaos rules not order, a world where righteousness is not rewarded. That's Cesar's world, and if you're not willing to play by his rules, then you're gonna have to pay the price. </p>
                        </article>
                    </div>
                    <div>这个没用
                        <input id="ac-3" name="accordion-1" type="checkbox" />
                        <label for="ac-3">References</label>
                        <article class="ac-large">
                            <p>You think water moves fast? You should see ice. It moves like it has a mind. Like it knows it killed the world once and got a taste for murder. After the avalanche, it took us a week to climb out. Now, I don't know exactly when we turned on each other, but I know that seven of us survived the slide... and only five made it out. Now we took an oath, that I'm breaking now. We said we'd say it was the snow that killed the other two, but it wasn't. Nature is lethal but it doesn't hold a candle to man. </p>
                        </article>
                    </div>
                    <div>
                        <input id="ac-4" name="accordion-1" type="checkbox" />
                        <label for="ac-4">Contact us</label>
                        <article class="ac-large">
                            <p>You see? It's curious. Ted did figure it out - time travel. And when we get back, we gonna tell everyone. How it's possible, how it's done, what the dangers are. But then why fifty years in the future when the spacecraft encounters a black hole does the computer call it an 'unknown entry event'? Why don't they know? If they don't know, that means we never told anyone. And if we never told anyone it means we never made it back. Hence we die down here. Just as a matter of deductive logic. </p>
                        </article>
                    </div>
                    <div>
                        <input id="666" name="accordion-1" type="checkbox" />
                        <label for="666">
                            <div class="row">
                                <div class="col-33">菜数</div>
                                <div class="col-33">空</div>
                                <div class="col-33">总价</div>
                            </div>
                        </label>
                        <article class="ac-small">
                            <div class="row">
                                <div class="col-33">单号:2017121120480000000</div>
                                <div class="col-33">数量+-</div>
                                <div class="col-33">时间:2017-12-11-20:48:00</div>
                            </div>
                        </article>
                    </div>
                </section>
            </div>

        </div>

    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>

<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="${commonBaseUrl}/js/common.js"></script>
</body>
</html>
