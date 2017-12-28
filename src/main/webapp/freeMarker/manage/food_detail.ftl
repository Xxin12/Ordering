<link href="${manageBaseUrl}/resources/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
<link href="${manageBaseUrl}/resources/plugins/select2/css/select2.min.css" rel="stylesheet"/>
<link href="${manageBaseUrl}/resources/css/common.css" rel="stylesheet"/>

<style>
    .form-group{
        margin: 10px 0;
    }
    .foodlabels{
        margin: 15px 0;
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
        <div class="foodlabels">
        <label class="labels" for="name">名称</label>
        <input id="name" name="name" type="text" class="form-control" value="${(food.name)!}">
        </div>
        <div class="foodlabels">
            <label for="foodTypeId">分类</label>
            <div style="height: 20px"></div>
        </div>
        <div class="foodlabels">
        <select id="foodTypeId" name="foodTypeId" class="form-control">
            <option value="">全部</option>
            <#list foodTypeList as foodType>
                <option value="${foodType.id}" <#if (foodTypeId?c)! = (foodType.id?c)!>selected</#if>>${foodType.name}</option>
            </#list>
        </select>
        </div>
        <div class="foodlabels">
            <label class="labels" for="price">价格</label>
            <input id="price" name="price" type="text" class="form-control" value="${(food.price / 100)!}">
        </div>
        <div class="foodlabels">
            <label class="labels" for="imageUrl">展示图片地址</label>
            <input id="imageUrl" name="imageUrl" type="url" class="form-control" value="${(food.imageUrl)!}">
        </div>
        <div style="height: 20px">

        </div>
    </div>
</form>

<script src="${manageBaseUrl}/resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/select2/js/select2.min.js"></script>
<script src="${manageBaseUrl}/resources/js/common.js"></script>