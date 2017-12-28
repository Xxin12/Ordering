<link href="${manageBaseUrl}/resources/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
<link href="${manageBaseUrl}/resources/plugins/select2/css/select2.min.css" rel="stylesheet"/>
<link href="${manageBaseUrl}/resources/css/common.css" rel="stylesheet"/>
<style>
    .form-group{
        padding: 10px 0;
    }
    .form-group .labels{
        transform: translateY(-65%);
        font-size: 12px;
        color: #337ab7;
    }
</style>
<#--编辑菜品分类-->
<form>
    <div class="form-group">
        <label for="name">名称</label>
        <input id="name" name="name" type="text" class="form-control" value="${(foodType.name)!}" autofocus="autofocus">
    </div>
</form>
<script src="${manageBaseUrl}/resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/select2/js/select2.min.js"></script>
<script src="${manageBaseUrl}/resources/js/common.js"></script>