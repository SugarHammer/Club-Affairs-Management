$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/stZx/auditList',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: 'id', width: 20 },
            { label: '社团名称', name: 'stName', index: 'stName', width: 60 },
            { label: '负责人', name: 'user', index: 'user', width: 60 },
            { label: '招新地址', name: 'place', index: 'place', width: 100 },
            { label: '开始时间', name: 'startTime', index: 'startTime', width: 80 },
            { label: '招新流程', name: 'zxlc', index: 'zxlc', width: 200 },
            { label: '操作', name: 'state', index: 'state', width: 50, edittype:"button", formatter: function (value, grid, rows, state) {
                return '<a href="javascript:void(0);" style="color:#3cbd58" onclick="modify(\''+ rows.id+ '\');">同意</a>'+"&nbsp;&nbsp;"+
                    '<a href="javascript:void(1);" style="color:#f60" onclick="modify2(\''+ rows.id+ '\');">拒绝</a>';
            }}
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
        stZx: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },


        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.stZx = {};
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
            var url = vm.stZx.id == null ? "sys/stZx/save" : "sys/stZx/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.stZx),
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
                    url: baseURL + "sys/stZx/delete",
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
            $.get(baseURL + "sys/stZx/info/"+id, function(r){
                vm.stZx = r.stZx;
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