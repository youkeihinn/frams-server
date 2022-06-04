layui.use(['table', 'admin','laydate', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    getList(); getList1();
    /**
     * 员工考勤管理
     */
    var Attendance = {
        tableId: "attendanceTable"
    };
  //常规用法
    laydate.render({
      elem: '#condition'
    });
    
    /**
     * 初始化表格的列
     */
    Attendance.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'attendId', hide: true, title: '主键'},
            {field: 'name', sort: true, title: '考勤员工'},
            {field: 'no', sort: true, title: '工号'},
            {field: 'time', sort: true, title: '考勤时间'},
            {field: 'type', sort: true, title: '类型'},
            {field: 'address', sort: true, title: '考勤地址'},
            
            {field: 'pic',sort: true,title: '照片', templet: function(d){              
                return "<img lay-event='img' src='"+d.pic+"' />";
              
             } },
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Attendance.search = function () {
        var queryData = {};
        queryData['times'] = $("#condition").val();
        queryData['userId'] = $("#userId").val();
        queryData['deptId'] = $("#deptId").val();
        queryData['no'] = $("#no").val();  
        table.reload(Attendance.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Attendance.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area : [ '650px', '600px' ], // 宽高
            title: '人脸考勤',
            content: Feng.ctxPath + '/attendance/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Attendance.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Attendance.exportExcel = function () {
        var checkRows = table.checkStatus(Attendance.tableId);
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
    Attendance.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area : [ '650px', '600px' ], // 宽高
            title: '修改员工考勤',
            content: Feng.ctxPath + '/attendance/edit?attendId=' + data.attendId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Attendance.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Attendance.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/attendance/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Attendance.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("attendId", data.attendId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Attendance.tableId,
        url: Feng.ctxPath + '/attendance/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Attendance.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Attendance.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Attendance.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Attendance.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Attendance.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Attendance.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Attendance.onDeleteItem(data);
        }else if(layEvent === 'img'){
        	console.log($(this).attr('src'))
    		var imgSrc=$(this).attr('src')
    		layer.open({
    			type:1
    			,title:false
    			,closeBtn:0
    			,skin:'layui-layer-nobg'
    			,shadeClose:true
    			,content:'<img src="'+imgSrc+'" style="max-height:100%;max-width:100%;">'
    			,scrollbar:false
    		})
        }
    });
});
