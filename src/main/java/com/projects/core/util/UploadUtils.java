package com.projects.core.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UploadUtils {
	
	/**
	 * 得到真实文件名
	 * @param fileName
	 * @return
	 */
    public static String subFileName(String fileName){
        //查找最后一个 \ (文件分隔符)位置
        int index = fileName.lastIndexOf(File.separator);
        if(index == -1){
            //没有分隔符，说明是真实名称
            return fileName;
        }else {
            return fileName.substring(index+1);
        }
    }
 
    /**
     * 获得随机UUID文件名
     * @param fileName
     * @return
     */
    public static String generateRandonFileName(String fileName){
        //首先获得扩展名，然后生成一个UUID码作为名称，然后加上扩展名
        String ext = fileName.substring(fileName.lastIndexOf("."));
        return DateUtil.getNowPlusTimeMill()+"_"+UUID.randomUUID().toString().replaceAll("-", "")+ext;
    }
    
    /**
     * 获得U会标签文件名
     * @param fileName
     * @return
     */
    public static String generateUmeetingRandonFileName(String fileName){
        //ummeting + 时间 
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String ext = fileName.substring(fileName.lastIndexOf("."));
        return "Umeeting" + df.format(new Date()) + "_" + new Random().nextInt(1000)  + ext;
    }
 
    /**
     * 获得hashcode 生成二级目录
     * @param uuidFileName
     * @return
     */
    public static String generateRandomDir(String uuidFileName){
        int hashCode = uuidFileName.hashCode();//得到它的hashcode编码
        //一级目录
        int d1 = hashCode & 0xf;
        //二级目录
        int d2 = (hashCode >> 4) & 0xf;
        return "/"+d1+"/"+d2+"/"+DateUtil.getNowPlusTimeMill();
    }
    
    public static String generateRandomDir1(String uuidFileName){
    	 int hashCode = uuidFileName.hashCode();//得到它的hashcode编码
         //一级目录
         int d1 = hashCode & 0xf;
         //二级目录
         int d2 = (hashCode >> 4) & 0xf;
         return "/"+d1+"/"+d2;
    }
}
