package com.projects.core.shiro.jwt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.projects.core.shiro.ShiroUser;
import com.projects.core.shiro.service.UserAuthService;
import com.projects.core.shiro.service.impl.UserAuthServiceServiceImpl;
import com.projects.core.util.JwtTokenUtil;
import com.projects.modular.system.entity.User;

import cn.stylefeng.roses.core.util.ToolUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

/**
 * 基于JWT的认证域
 *
 * @author fengshuonan
 * @Date 2019/4/21 11:01
 */
public class JwtRealm extends AuthorizingRealm {

	public Class<?> getAuthenticationTokenClass() {
		return JwtToken.class;
	}

	/**
	 * 认证
	 *
	 * @author fengshuonan
	 * @Date 2019/4/21 11:01
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		JwtToken jwtToken = (JwtToken) token;
		String jwt = (String) jwtToken.getPrincipal();
		ShiroUser shiroUser;

		UserAuthService userAuthService = UserAuthServiceServiceImpl.me();
		try {
			Claims claimFromToken = JwtTokenUtil.getClaimFromToken(jwt);
			String account = (String) claimFromToken.get("account");
			User user = userAuthService.user(account);
			shiroUser = userAuthService.shiroUser(user);
		} catch (JwtException e) {
			throw new AuthenticationException("JWT令牌无效:" + e.getMessage());
		}

		return new SimpleAuthenticationInfo(shiroUser, Boolean.TRUE, getName());
	}

	/**
	 * 授权,JWT已包含访问主张只需要解析其中的主张定义就行了
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserAuthService shiroFactory = UserAuthServiceServiceImpl.me();
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		List<Long> roleList = shiroUser.getRoleList();

		Set<String> permissionSet = new HashSet<>();
		Set<String> roleNameSet = new HashSet<>();

		for (Long roleId : roleList) {
			List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
			if (permissions != null) {
				for (String permission : permissions) {
					if (ToolUtil.isNotEmpty(permission)) {
						permissionSet.add(permission);
					}
				}
			}
			String roleName = shiroFactory.findRoleNameByRoleId(roleId);
			roleNameSet.add(roleName);
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissionSet);
		info.addRoles(roleNameSet);
		return info;
	}
}