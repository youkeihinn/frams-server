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
package com.projects.core.interceptor;

import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.util.RenderUtil;
import io.jsonwebtoken.JwtException;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.projects.core.common.constant.JwtConstants;
import com.projects.core.common.constant.factory.ConstantFactory;
import com.projects.core.common.exception.BizExceptionEnum;
import com.projects.core.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Rest Api接口鉴权
 *
 * @author stylefeng
 * @Date 2018/7/20 23:11
 */
public class RestApiInteceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
			return true;
		}
		return check(request, response);
	}

	private boolean check(HttpServletRequest request, HttpServletResponse response) {
		
		
		return true;
	}

}
