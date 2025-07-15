$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/stClub/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: 'id', width: 20 },
            { label: '社团名称', name: 'clubname', index: 'clubname', width: 60 },
            { label: '创建时间', name: 'establishTime', index: 'establishTime', width: 60 },
            { label: '社团类型', name: 'sort', index: 'sort', width: 80 },
            { label: '社团介绍', name: 'clubDesc', index: 'clubDesc', width: 120 },
            { label: '社团人数', name: 'memberNum', index: 'memberNum', width: 80 },
            { label: '社团当前积分', name: 'jf', index: 'jf', width: 80 },
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
            clubname: null
        },
        showList: true,
        title: null,
        stClub: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },


        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.stClub = {};
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
            var url = vm.stClub.id == null ? "sys/stClub/save" : "sys/stClub/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.stClub),
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
                    url: baseURL + "sys/stClub/delete",
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
            $.get(baseURL + "sys/stClub/info/"+id, function(r){
                vm.stClub = r.stClub;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'clubname': vm.q.clubname},
                page:page
            }).trigger("reloadGrid");
        }
    }
});