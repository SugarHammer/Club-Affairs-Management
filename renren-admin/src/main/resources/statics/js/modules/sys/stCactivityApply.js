$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/stCactivityApply/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: 'id', width: 20 },
            { label: '活动名称', name: 'name', index: 'name', width: 60 },
            { label: '社团名称', name: 'club', index: 'club', width: 80 },
            { label: '场地', name: 'place', index: 'place', width: 100 },
            { label: '活动开始时间', name: 'starttime', index: 'starttime', width: 100 },
            { label: '活动结束时间', name: 'endtime', index: 'endtime', width: 100 },
            { label: '类型', name: 'sort', index: 'sort', width: 80 },
            { label: '活动介绍', name: 'dc', index: 'dc', width: 120 },
            { label: '注意事项', name: 'matters', index: 'matters', width: 100 },
            { label: '审核状态', name: 'state', index: 'state', width: 80 },
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
        stCactivityApply: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },


        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.stCactivityApply = {};
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
            var url = vm.stCactivityApply.id == null ? "sys/stCactivityApply/save" : "sys/stCactivityApply/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.stCactivityApply),
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
                    url: baseURL + "sys/stCactivityApply/delete",
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
            $.get(baseURL + "sys/stCactivityApply/info/"+id, function(r){
                vm.stCactivityApply = r.stCactivityApply;
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