layui.use(['table', 'admin','laydate', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;
    getList();
    /**
     * 请假管理管理
     */
    var Leave = {
        tableId: "leaveTable"
    };
  
  //常规用法
    laydate.render({
      elem: '#condition'
    });
    /**
     * 初始化表格的列
     */
    Leave.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'leaveId', hide: true, title: '主键'},
            {field: 'name', sort: true, title: '员工'},
            {field: 'startTime', sort: true, title: '开始时间'},
                
            {field: 'reason', sort: true, title: '事由'},

            {field: 'status', sort: true, title: '审批状态', templet: function(d){
                
           	 if(d.status == 0 ){
           		
           		 return "<a  style='color:red' >待审核</a>";
           	 }else if(d.status == 1){
           		
           		 return "<a  style='color:red' >已通过</a>";
           	 }else if(d.status == 2){
           		
           		 return "<a  style='color:red' >拒绝</a>";
           	 }
              
            
             } },
          
            {field: 'mark', sort: true, title: '审批回复'},
            {align: 'center', minWidth:200,   toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Leave.search = function () {
        var queryData = {};
        queryData['times'] = $("#condition").val();
        queryData['userId'] = $("#userId").val();
        table.reload(Leave.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Leave.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加请假管理',
            content: Feng.ctxPath + '/leave/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Leave.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Leave.exportExcel = function () {
        var checkRows = table.checkStatus(Leave.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Leave.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改请假管理',
            content: Feng.ctxPath + '/leave/edit?leaveId=' + data.leaveId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Leave.tableId);
            }
        });
    };
    Leave.audit = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '审批',
            content: Feng.ctxPath + '/leave/audit?leaveId=' + data.leaveId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Leave.tableId);
            }
        });
    };

    
    
    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Leave.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/leave/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Leave.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("leaveId", data.leaveId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Leave.tableId,
        url: Feng.ctxPath + '/leave/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Leave.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Leave.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Leave.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Leave.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Leave.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Leave.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Leave.onDeleteItem(data);
        }else if (layEvent === 'audit') {
            Leave.audit(data);
        }
    });
});
