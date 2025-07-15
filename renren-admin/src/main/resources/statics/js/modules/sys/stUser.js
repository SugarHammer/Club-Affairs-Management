$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/stUser/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: 'id', width: 20 },
            { label: '姓名', name: 'name', index: 'name', width: 60 },
            { label: '手机号', name: 'phone', index: 'phone', width: 100 },
            { label: '邮箱地址', name: 'email', index: 'email', width: 100 },
            { label: '所在社团', name: 'clubs', index: 'clubs', width: 100 },
            { label: '学校名称', name: 'school', index: 'school', width: 100 },
            { label: '班级', name: 'clas', index: 'clas', width: 100 },
            { label: '创建时间', name: 'createTime', index: 'createTime', width: 100 },
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            title: null
        },
        showList: true,
        title: null,
        stUser: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },


        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.stUser = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },

        saveOrUpdate: function (event) {
            var url = vm.stUser.id == null ? "sys/stUser/save" : "sys/stUser/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.stUser),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/stUser/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function(id){
            $.get(baseURL + "sys/stUser/info/"+id, function(r){
                vm.stUser = r.stUser;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
        }
    }
});