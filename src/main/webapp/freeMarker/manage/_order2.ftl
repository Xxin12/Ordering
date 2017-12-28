<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>餐桌订单</title>

    <link href="${manageBaseUrl}/resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${manageBaseUrl}/resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css"
          rel="stylesheet"/>
    <link href="${manageBaseUrl}/resources/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
    <link href="${manageBaseUrl}/resources/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="${manageBaseUrl}/resources/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
    <link href="${manageBaseUrl}/resources/plugins/select2/css/select2.min.css" rel="stylesheet"/>

    <link href="${manageBaseUrl}/resources/css/common.css" rel="stylesheet"/>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <#--<a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i>&nbsp;新增</a>-->
        <a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction(undefined)"><i
                class="zmdi zmdi-close"></i>&nbsp;删除</a>
    </div>
    <table id="table"></table>
</div>
<script src="${manageBaseUrl}/resources/plugins/jquery.1.12.4.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/select2/js/select2.min.js"></script>

<script src="${commonBaseUrl}/js/common.js"></script>
<script>
    var $table = $('#table');
    $(function () {
        $(document).on('focus', 'input[type="text"]', function () {
            $(this).parent().find('label').addClass('active');
        }).on('blur', 'input[type="text"]', function () {
            if ($(this).val() == '') {
                $(this).parent().find('label').removeClass('active');
            }
        });
        // bootstrap table初始化
        // http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
        $table.bootstrapTable({
            url: '${manageBaseUrl}/orders2',
            method: 'GET',
//            height: getHeight(),
            striped: true,
//            search: true,
            searchOnEnterKey: true,
            showRefresh: true,
            showToggle: true,
            showColumns: true,
            minimumCountColumns: 2,
            showPaginationSwitch: true,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            classes: 'table table-hover table-no-bordered',
            //sidePagination: 'server',
            //silentSort: false,
            smartDisplay: false,
            idField: 'id',
            sortName: 'id',
            // 排序方式
            sortOrder: 'asc',
            escape: true,
            maintainSelected: true,
            toolbar: '#toolbar',
            // 分页方式为服务器端分页，必须返回 total 总记录数参数
            sidePagination: 'server',
            // 数据对象名称
            dataField: 'data',
            columns: [
                {field: 'state', checkbox: true},
                {field: 'id', title: '编号', sortable: true, halign: 'center'},
                {field: 'dinnerTableId', title: '餐桌号', sortable: true, halign: 'center'},
                {field: 'totalPrice', title: '总价', sortable: true, halign: 'center', formatter: function (value, row, index) {return value / 100}},
                {field: 'updatedTime', title: '更新时间', sortable: true, halign: 'center'},
                {
                    title: '操作',
                    halign: 'center',
                    align: 'center',
                    formatter: 'actionFormatter',
                    events: 'actionEvents',
                    clickToSelect: false
                }
            ]
        }).on('all.bs.table', function (e, name, args) {
            $('[data-toggle="tooltip"]').tooltip();
            $('[data-toggle="popover"]').popover();
        });
    });
    function actionFormatter(value, row, index) {
        return [
//            '<a class="like" href="javascript:void(0)" data-toggle="tooltip" title="Like"><i class="glyphicon glyphicon-heart"></i></a>　',
            '<a class="edit ml10" href="javascript:void(0)" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
            '<a class="remove ml10" href="javascript:void(0)" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
        ].join('');
    }

    window.actionEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like icon, row: ' + JSON.stringify(row));
            console.log(value, row, index);
        },
        'click .edit': function (e, value, row, index) {
//            alert('You click edit icon, row: ' + JSON.stringify(row));
//            console.log(value, row, index);
            updateAction(row.id);
        },
        'click .remove': function (e, value, row, index) {
//            alert('You click remove icon, row: ' + JSON.stringify(row));
//            console.log(value, row, index);
            deleteAction(row.id);
        }
    };
    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }

    // 新增
    function createAction() {
        $.confirm({
            type: 'dark',
            animationSpeed: 300,
            title: '新增',
            content: "url:${manageBaseUrl}/.html",
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        request("${manageBaseUrl}/orders", requestType.POST,dataType.JSON,$("form").serializeArray(),function(result){
                            if (result.code != undefined && result.code != 0) {
                                $.alert(result.message);
                            } else {
                                $table.bootstrapTable('refresh');
                            }
                        });
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }
    // 编辑
    function updateAction(id) {
        $.confirm({
            type: 'dark',
            animationSpeed: 300,
            title: '编辑',
            content: "url:${manageBaseUrl}/.html?id="+id,
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        request("${manageBaseUrl}/orders/" + id, requestType.PUT,dataType.JSON,$("form").serializeArray(),function(result){
                            if (result.code != undefined && result.code != 0) {
                                $.alert(result.message);
                            } else {
                                $table.bootstrapTable('refresh');
                            }
                        });
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }

    // 删除
    function deleteAction(id) {
        var rows = $table.bootstrapTable('getSelections');
        // 删除多个时如果没有选择数据则提示
        if (!id && rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }else {

            $.confirm({
                type: 'red',
                animationSpeed: 300,
                title: false,
                content: '确认删除数据吗？',
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            if (!id) {
                                for (var i in rows) {
                                    ids.push(rows[i].id);
                                }
                            } else {
                                ids.push(id);
                            }

                            request('${commonBaseUrl}/batchOperation', requestType.POST, dataType.JSON, {
                                "ids": ids
                            }, function (result) {
                                if (result.code != undefined && result.code != 0) {
                                    $.alert(result.message);
                                } else {
                                    request('${manageBaseUrl}/orders/' + result.data, requestType.DELETE, dataType.JSON, undefined, function (result) {
                                        if (result.code != undefined && result.code != 0) {
                                            $.alert(result.message);
                                        } else {
                                            $table.bootstrapTable('refresh');
                                        }
                                    });
                                }
                            });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }
</script>
</body>
</html>