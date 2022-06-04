layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 注册用户管理
     */
    var RegisterUser = {
        tableId: "registerUserTable"
    };

    /**
     * 初始化表格的列
     */
    RegisterUser.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'userName', sort: true, title: '用户名'},
            {field: 'userPass', sort: true, title: '密码'},
            {field: 'name', sort: true, title: '姓名'},

            {field: 'headImage',sort: true,title: '人脸照片', templet: function(d){              
                return "<img lay-event='img' src='"+d.headImage+"' />";
              
             } },
           
     		{field: 'no', sort: true, title: '工号'},
         	{field: 'adrress', sort: true, title: '部门'}, 
         	{field: 'tel', sort: true, title: '联系方式'}, 
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    RegisterUser.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(RegisterUser.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    RegisterUser.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加注册用户',
            content: Feng.ctxPath + '/registerUser/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(RegisterUser.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    RegisterUser.exportExcel = function () {
        var checkRows = table.checkStatus(RegisterUser.tableId);
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
    RegisterUser.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改注册用户',
            content: Feng.ctxPath + '/registerUser/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(RegisterUser.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    RegisterUser.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/registerUser/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(RegisterUser.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + RegisterUser.tableId,
        url: Feng.ctxPath + '/registerUser/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: RegisterUser.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        RegisterUser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        RegisterUser.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        RegisterUser.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + RegisterUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            RegisterUser.openEditDlg(data);
        } else if (layEvent === 'delete') {
            RegisterUser.onDeleteItem(data);
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
