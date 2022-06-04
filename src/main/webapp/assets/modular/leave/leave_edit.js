/**
 * 详情对话框
 */
var LeaveInfoDlg = {
    data: {
        userId: "",
        startTime: "",
        endTime: "",
        mark: "",
        status: "",
        reason: "",
        type: ""
    }
};

layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    //让当前iframe弹层高度适应
    admin.iframeAuto();
    laydate.render({
        elem: '#startTime'
        ,type: 'time'
      });
    laydate.render({
        elem: '#endTime'
        ,type: 'time'
      });
    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/leave/detail?leaveId=" + Feng.getUrlParam("leaveId"));
    var result = ajax.start();
    form.val('leaveForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/leave/editItem", function (data) {
            Feng.success("更新成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});