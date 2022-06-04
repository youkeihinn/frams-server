
package com.projects.modular.api.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.projects.config.properties.GunsProperties;
import com.projects.config.web.ResponseData;
import com.projects.core.common.exception.BizExceptionEnum;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.util.FaceSpot;
import com.projects.core.util.JwtTokenUtil;
import com.projects.modular.api.entity.RegisterUser;
import com.projects.modular.api.model.params.RegisterUserParam;
import com.projects.modular.api.model.result.RegisterUserResult;
import com.projects.modular.api.service.RegisterUserService;
import com.projects.modular.attendance.entity.AttendTime;
import com.projects.modular.attendance.entity.Attendance;
import com.projects.modular.attendance.entity.Dept;
import com.projects.modular.attendance.model.params.AttendTimeParam;
import com.projects.modular.attendance.model.params.AttendanceParam;
import com.projects.modular.attendance.model.params.LeaveParam;
import com.projects.modular.attendance.model.result.AttendTimeResult;
import com.projects.modular.attendance.model.result.Result;
import com.projects.modular.attendance.service.AttendTimeService;
import com.projects.modular.attendance.service.AttendanceService;
import com.projects.modular.attendance.service.DeptService;
import com.projects.modular.attendance.service.LeaveService;
import com.projects.modular.system.mapper.UserMapper;
import com.projects.modular.system.model.Login;
import com.projects.modular.system.service.UserService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;


/**
 * 接口控制器提供
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {
	 @Autowired
	 private RegisterUserService registerUserService;
	
	 @Autowired
	 private UserMapper userMapper;
	
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private AttendTimeService  attendTimeService;
	 @Autowired
	 private AttendanceService  attendanceService;
	 @Autowired
	 private LeaveService  leaveService;
	 
	 @Autowired
	 private DeptService  deptService;
	 
	    
    @PostMapping(value = "/registerUser")
	public ResponseData registerUser(@RequestBody RegisterUserParam registerUserParam) {

		try {
			QueryWrapper<RegisterUser> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("userName", registerUserParam.getUserName());
			RegisterUser registerUser = registerUserService.getOne(queryWrapper);
			if(null != registerUser){
				return	ResponseData.error("用户已注册");
			}
			
			registerUserService.add(registerUserParam);
			
			return ResponseData.success();
		} catch (Exception e) {
			return ResponseData.error(e.getMessage());
		}

	}
    @PostMapping(value = "/updateRegisterUser")
   	public ResponseData updateRegisterUser(@RequestBody RegisterUserParam registerUserParam) {
    	Long userId = JwtTokenUtil.getUserId(this.getHttpServletRequest());
   		try {
   			
   			registerUserParam.setId(userId);
   			
   			registerUserService.update(registerUserParam);			
   			return ResponseData.success();
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
    
    
   
    
    @PostMapping(value = "/editUser")
	public ResponseData editUser(@RequestBody RegisterUserParam registerUserParam) {

		try {
			RegisterUser user = new RegisterUser();
			UpdateWrapper<RegisterUser> queryWrapper = new UpdateWrapper<>();
			queryWrapper.eq("userName", registerUserParam.getUserName());
			ToolUtil.copyProperties(registerUserParam, user);
			registerUserService.update(user, queryWrapper);
			
			return ResponseData.success();
		} catch (Exception e) {
			return ResponseData.error(e.getMessage());
		}

	}
      
  

	
    @PostMapping(value = "/loginUser")
	public ResponseData loginrUser(@RequestBody RegisterUserParam registerUserParam) {

		try {
			QueryWrapper<RegisterUser> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("userName", registerUserParam.getUserName());
			RegisterUser registerUser = registerUserService.getOne(queryWrapper);
			if(null == registerUser){
				return	ResponseData.error("用户不存在");
			}
			
			if( !registerUser.getUserPass().equals(registerUserParam.getUserPass())){
				return	ResponseData.error("密码错误");
			}
			
			 Login  login = new Login();
	         login.setToken(JwtTokenUtil.generateToken(registerUser.getId()+""));      
	         login.setNickName(registerUser.getName());
	         login.setPhone("");
	     
	         return ResponseData.success(login);
		
		} catch (Exception e) {
			return ResponseData.error(e.getMessage());
		}

	}
    
    
   
 
    
    
    @GetMapping(value = "/getAttendTimeList")
   	public ResponseData getAttendTimeList() {

   		try {
   			AttendTimeParam param = new AttendTimeParam();
   	
   			LayuiPageInfo pageInfo = attendTimeService.findPageBySpec(param);
   			return ResponseData.success(pageInfo);
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}

    
    
    
    @GetMapping(value = "/getSignList")
   	public ResponseData getSignList(Long timeId) {

   		try {
   			
   			QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
   			queryWrapper.eq("time_id", timeId);
   			queryWrapper.orderByAsc("time");
   			List<Attendance> list = attendanceService.getBaseMapper().selectList(queryWrapper);
   			
   			AttendTime attendTime = attendTimeService.getById(timeId);
   			
   			
   			AttendTimeResult  result =  new AttendTimeResult();
   			ToolUtil.copyProperties(attendTime, result);
   			result.setList(list);
   			
   			return ResponseData.success(result);
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
    
    
    
    @GetMapping(value = "/getMyAttendance")
   	public ResponseData getMyAttendance() {
    	Long userId = JwtTokenUtil.getUserId(this.getHttpServletRequest());
   		try {
   			AttendanceParam param = new AttendanceParam();
   			param.setUserId(userId);
   			LayuiPageInfo pageInfo = attendanceService.findPageBySpec1(param);
   			
   			return ResponseData.success(pageInfo);
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
    
    
    
    @GetMapping(value = "/getLeaveList")
   	public ResponseData getLeaveList() {
    	Long userId = JwtTokenUtil.getUserId(this.getHttpServletRequest());
   		try {
   			LeaveParam param = new LeaveParam ();
   			param.setUserId(userId);
   			LayuiPageInfo pageInfo = leaveService.findPageBySpec1(param);
   			
   			return ResponseData.success(pageInfo);
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
    
    
    
    @GetMapping(value = "/getDeptList")
   	public ResponseData getDeptList() {
    	
   		try {
   		
   			QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
		
   			List<Dept> list = deptService.getBaseMapper().selectList(queryWrapper);
   			return ResponseData.success(list);
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
    
    
    
    @PostMapping(value = "/sendLeave")
   	public ResponseData sendLeave(@RequestBody LeaveParam leaveParam) {
    	Long userId = JwtTokenUtil.getUserId(this.getHttpServletRequest());
   		try {
   			leaveParam.setUserId(userId);
   			leaveParam.setStatus(0);
   			leaveService.add(leaveParam);
   		  return ResponseData.success();
   	   		
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
    
    @PostMapping(value = "/saveSign")
   	public ResponseData saveSign(@RequestBody AttendanceParam attendanceParam) {
    	Long userId = JwtTokenUtil.getUserId(this.getHttpServletRequest());
   		try {
   			attendanceParam.setUserId(userId);
   			attendanceParam.setTime(new Date());
   			
   			
   			RegisterUser registerUser = registerUserService.getById(userId);
   			attendanceParam.setName(registerUser.getName());
   			attendanceParam.setNo(registerUser.getNo());
   			attendanceParam.setTimeId(attendanceParam.getTimeId());
   		    String fileSavePath = "";
	    	String serverSavePath = "";
   		    fileSavePath = gunsProperties.getFileUploadPath();
			serverSavePath = gunsProperties.getServerUploadPath();
			String replace = attendanceParam.getPic().replace(serverSavePath, fileSavePath+"/");
   			JSONObject js = FaceSpot.searchFace(new File(replace), "face",userId+ "");
   			System.out.println(js.toString(2));
   			com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(js.toString());
   			Result data = com.alibaba.fastjson.JSONObject.toJavaObject(jsonObject, Result.class);
   			if(null == data.getResult()) {
   				//result.put("t", false);
   				return ResponseData.error("未能识别");
   			}else {
   				
   		    	String pictureName = UUID.randomUUID().toString() + ".jpeg" ;
   				double score = data.getResult().getUser_list().get(0).getScore();
   				if(score>60) {
   					
   				        
   				        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

   				        QueryWrapper<Attendance> objectQueryWrapper = new QueryWrapper<>();
   				        
   				        objectQueryWrapper.eq("user_id", userId);
   				        objectQueryWrapper.eq("time_id", attendanceParam.getTimeId());
   				        Integer count = attendanceService.getBaseMapper().selectCount(objectQueryWrapper);
   				        
   				        
   				        AttendTime attendTime = attendTimeService.getById(attendanceParam.getTimeId());			        			        
   				       
   				        attendanceParam.setTime(new Date());
   				       attendanceParam.setUserId(userId);
   				          
   				        if(count==0) {
   				        	attendanceParam.setType("正常打卡");
   				        	if(new Date().before(attendTime.getStartTime())) {
   				        		return ResponseData.error("未到打卡时间");
   				        	}else if(new Date().after(attendTime.getEndTime())) {
   				        		attendanceParam.setType("迟到");
   				        	}	        	
   				        	
   				        }else {
   				        	return ResponseData.error("今日已打卡");
   				        }
   				       
   				       
   				        attendanceService.add(attendanceParam);
   				        
   				}else {
   					return ResponseData.error("未能识别，和账号本人不匹配");
   				}
   	        
   	    }
   			   return ResponseData.success();
   			
   	
   		
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
    
    
    
    @PostMapping(value = "/reNewPass")
   	public ResponseData reNewPass(@RequestBody RegisterUserParam registerUserParam) {
    	Long userId = JwtTokenUtil.getUserId(this.getHttpServletRequest());
   		try {
   			UpdateWrapper<RegisterUser> queryWrapper = new UpdateWrapper<>();
   			queryWrapper.eq("id", userId);
   			RegisterUser registerUser = registerUserService.getOne(queryWrapper);
   			if(!registerUser.getUserPass().equals(registerUserParam.getJuserPass())) {
   				return ResponseData.error("旧密码不正确");
   			}
   			
   			RegisterUser user = new RegisterUser();
   			user.setUserPass(registerUserParam.getUserPass());
   			registerUserService.update(user, queryWrapper);			
   			return ResponseData.success();
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
    
    
    @PostMapping(value = "/setHeadImage")
	public ResponseData setHeadImage(@RequestBody RegisterUserParam registerUserParam) {

		try {
			UpdateWrapper<RegisterUser> queryWrapper = new UpdateWrapper<>();
			queryWrapper.eq("userName", registerUserParam.getUserName());
			RegisterUser user = new RegisterUser();
			user.setHeadImage(registerUserParam.getHeadImage());
			registerUserService.update(user, queryWrapper);			
			return ResponseData.success();
		} catch (Exception e) {
			return ResponseData.error(e.getMessage());
		}

	}
    
    @GetMapping(value = "/getPersonalData")
   	public ResponseData getPersonalData() {

   		try {
   		 Long userId = JwtTokenUtil.getUserId(this.getHttpServletRequest());
   			RegisterUser registerUser = registerUserService.getById(userId);
   			RegisterUserResult  result = new RegisterUserResult();
   			ToolUtil.copyProperties(registerUser, result);
   			Dept dept = deptService.getById(registerUser.getAdrress());
   			if(null !=dept) {
   				result.setDeptName(dept.getName());
   			}
   			return ResponseData.success(result);
   		} catch (Exception e) {
   			return ResponseData.error(e.getMessage());
   		}

   	}
  
    
    @Autowired
	private GunsProperties gunsProperties;
    
    @RequestMapping(value="/upload")
    @ResponseBody
    public ResponseData imgUpload(@RequestParam(value = "uploadfile", required = false)MultipartFile[] uploadfile) {
    	List<String> pics = new ArrayList<>();
    	for (MultipartFile file : uploadfile) { 		
    		String fileSavePath = "";
    		String serverSavePath = "";
    		String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(file.getOriginalFilename());
    		try {
    			fileSavePath = gunsProperties.getFileUploadPath();
    			serverSavePath = gunsProperties.getServerUploadPath();
    			file.transferTo(new File(fileSavePath + pictureName));
    		} catch (Exception e) {
    			throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
    		}
    		pics.add( serverSavePath + pictureName);	    		
    	}
    	
    	return ResponseData.success(200,"上传成功",pics);
    }
    
    
    
    @RequestMapping(value="/uploadPic")
    @ResponseBody
    public ResponseData uploadPic(@RequestParam(value = "uploadfile", required = false)MultipartFile[] uploadfile) {
    	Long userId = JwtTokenUtil.getUserId(this.getHttpServletRequest());
    	List<String> pics = new ArrayList<>();
    	for (MultipartFile file : uploadfile) { 		
    		String fileSavePath = "";
    		String serverSavePath = "";
    		String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(file.getOriginalFilename());
    		try {
    			fileSavePath = gunsProperties.getFileUploadPath();
    			serverSavePath = gunsProperties.getServerUploadPath();
    			file.transferTo(new File(fileSavePath + pictureName));
    			 FaceSpot.addUser(new File(fileSavePath + pictureName), "注册用户", userId+"", "face");
    		} catch (Exception e) {
    			throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
    		}
    		pics.add( serverSavePath + pictureName);	    		
    	}
    	
    	return ResponseData.success(200,"上传成功",pics);
    }
    
	
    
}
