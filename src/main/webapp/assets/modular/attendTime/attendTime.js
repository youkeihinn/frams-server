layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 公司考勤时间管理
     */
    var AttendTime = {
        tableId: "attendTimeTable"
    };

    /**
     * 初始化表格的列
     */
    AttendTime.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'time', hide: true, title: ''},
            {field: 'startTime', sort: true, title: '开始时间'},
            {field: 'endTime', sort: true, title: '结束时间'},
            {field: 'address', sort: true, title: '考勤地点'},
            {field: 'mark', sort: true, title: '打卡说明'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    AttendTime.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(AttendTime.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    AttendTime.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area : [ '550px', '600px' ], // 宽高
            title: '添加考勤信息',
            content: Feng.ctxPath + '/attendTime/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(AttendTime.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    AttendTime.exportExcel = function () {
        var checkRows = table.checkStatus(AttendTime.tableId);
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
    AttendTime.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area : [ '550px', '600px' ], // 宽高
            title: '修改考勤信息',
            content: Feng.ctxPath + '/attendTime/edit?time=' + data.time,
            end: function () {
                admin.getTempData('formOk') && table.reload(AttendTime.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    AttendTime.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/attendTime/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(AttendTime.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("time", data.time);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + AttendTime.tableId,
        url: Feng.ctxPath + '/attendTime/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: AttendTime.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        AttendTime.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        AttendTime.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        AttendTime.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + AttendTime.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            AttendTime.openEditDlg(data);
        } else if (layEvent === 'delete') {
            AttendTime.onDeleteItem(data);
        }
    });
});
