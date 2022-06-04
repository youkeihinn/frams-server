package com.projects.modular.attendance.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.config.properties.GunsProperties;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.shiro.ShiroKit;
import com.projects.core.util.FaceSpot;
import com.projects.modular.attendance.entity.AttendTime;
import com.projects.modular.attendance.entity.Attendance;
import com.projects.modular.attendance.model.params.AttendanceParam;
import com.projects.modular.attendance.model.result.Result;
import com.projects.modular.attendance.service.AttendTimeService;
import com.projects.modular.attendance.service.AttendanceService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import sun.misc.BASE64Decoder;

/**
 * 员工考勤控制器
 *
 * @author demo
 * @Date 2022-03-01 15:53:22
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController extends BaseController {

    private String PREFIX = "/modular/attendance";

    @Autowired
    private AttendanceService attendanceService;
	@Autowired
	private GunsProperties gunsProperties;
	@Autowired
	private AttendTimeService  attendTimeService;
	
    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/attendance.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/attendance_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/attendance_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(AttendanceParam attendanceParam) {
        this.attendanceService.add(attendanceParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(AttendanceParam attendanceParam) {
        this.attendanceService.update(attendanceParam);
        return ResponseData.success();
    }
    
    
    /**
     * 人脸识别打卡
     * @param attendanceParam
     * @return
     */
    @RequestMapping("/searchFace")
    @ResponseBody
    public ResponseData searchFace(String img) {
    	JSONObject js = FaceSpot.searchFace(img, "face", "1");
		System.out.println(js.toString(2));
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(js.toString());
		Result data = com.alibaba.fastjson.JSONObject.toJavaObject(jsonObject, Result.class);
		if(null == data.getResult()) {
			//result.put("t", false);
			return ResponseData.error("未能识别");
		}else {
			 String fileSavePath = "";
	    	String serverSavePath = "";
	    	String pictureName = UUID.randomUUID().toString() + ".jpeg" ;
			double score = data.getResult().getUser_list().get(0).getScore();
			if(score>1) {
				//result.put("t", true);
				
				   BASE64Decoder decoder = new BASE64Decoder();  
			        try   
			        {  
			            //Base64解码  
			            byte[] b = decoder.decodeBuffer(img);  
			          //  System.out.println("解码完成");
			            for(int i=0;i<b.length;++i)  
			            {  
			                if(b[i]<0)  
			                {//调整异常数据  
			                    b[i]+=256;  
			                }  
			            }
			           
			    		
			           // System.out.println("开始生成图片");
			            //生成jpeg图片  
			            fileSavePath = gunsProperties.getFileUploadPath();
						serverSavePath = gunsProperties.getServerUploadPath();
			          
			            //String savepath = request.getServletContext().getRealPath(picpath);  
			            OutputStream out = new FileOutputStream(fileSavePath + pictureName);      
			            out.write(b);  
			            out.flush();  
			            out.close(); 
			           
			        } catch (Exception e) {
						e.printStackTrace();
						
					}
			        
			        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        QueryWrapper<Attendance> objectQueryWrapper = new QueryWrapper<>();
			        objectQueryWrapper.like("time", format.format(new Date()));
			        Integer count = attendanceService.getBaseMapper().selectCount(objectQueryWrapper);
			        
			        
			        AttendTime attendTime = attendTimeService.getById(1369945101967503362L);			        			        
			        AttendanceParam  param  =new AttendanceParam();
			        param.setTime(new Date());
			        param.setUserId(ShiroKit.getUser().getId());
			        param.setPic(serverSavePath + pictureName);
			        if(count==0) {
			        	 String format2 = format.format(new Date());
			        	  String time =  format2 +" "+attendTime.getStartTime() ;
			        	 try {
							if(new Date().after(format1.parse(time))) {
								  param.setType("迟到");
							 }else {
								  param.setType("上班签到");
							 }
						} catch (ParseException e) {							
							e.printStackTrace();
						}
			        }else {
			        	 String format2 = format.format(new Date());
			        	  String time =  format2 +" "+attendTime.getEndTime() ;
			        	 try {
							if(new Date().after(format1.parse(time))) {
								  param.setType("下班签到");
							 }else {
								  param.setType("早退");
							 }
						} catch (ParseException e) {							
							e.printStackTrace();
						}
			        }
			       
			        attendanceService.add(param);
			        
			}else {
				return ResponseData.error("未能识别，和账号本人不匹配");
			}
        
    }
		   return ResponseData.success();
		
    }
    
    

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(AttendanceParam attendanceParam) {
        this.attendanceService.delete(attendanceParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(AttendanceParam attendanceParam) {
        Attendance detail = this.attendanceService.getById(attendanceParam.getAttendId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2022-03-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(AttendanceParam attendanceParam) {
        return this.attendanceService.findPageBySpec(attendanceParam);
    }

}


