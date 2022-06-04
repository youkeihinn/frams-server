/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.projects.core.common.constant;

import java.util.List;

import cn.hutool.core.collection.CollectionUtil;

/**
 * jwt相关配置
 *
 * @author fengshuonan
 * @date 2017-08-23 9:23
 */
public interface JwtConstants {

	String AUTH_HEADER = "Authorization";

	String SECRET = "a3733008aff2bc50fbfcaf276316796a";

	Long EXPIRATION = 604800L;
	/**
	 * rest不需要鉴权
	 */

	List<String> AUTH_PATH = CollectionUtil.newLinkedList("/api/auth","/api/authToken", "/api/register", "/api/getAddressById",
			"/api/getDeptByAddressId", "/api/SAAuth", "/api/getRegisterCode", "/api/getClassRoomlist", "/api/upload",
			"/api/forgetPwd", "/api/getForgetCode", "/api/getForgetEmailCode", "/api/forgetEmailPwd",
			"/api/validateEmail", "/api/apkVersionCompare", "/api/apkUpload", "/api/getTeacherCreate",
			"/api/getTeacherDetail", "/api/getNewApkFile","/api/getExercisesCatalogTree","/api/getAllExercisesInCatalog"
			,"/api/getNewsTopList","/api/searchTeacherList","/api/getNewsListNew",
			"/api/getVideoResourseListDetailed","/api/getTeacherVideoResourseList",
			"/api/getNewsDetail","/api/getTopClassRoom","/api/getTopTeacher","/api/getNewsChannelList","/api/getClassRoomDetail","/api/getGradeAll","/api/getCourseAll","/api/getVideoList");
	
	
	
	
	List<String> AUTH_TEACHER = CollectionUtil.newLinkedList("/api/creatingClassroom","/api/modifyClassroom","/api/createExercises");

}
