package com.github.mushanwb.shiro;

import com.github.mushanwb.entity.User;
import com.github.mushanwb.jwt.JWTToken;
import com.github.mushanwb.jwt.JWTUtil;
import com.github.mushanwb.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroRealm extends AuthorizingRealm {

    private final UserService userService;

    @Autowired
    public ShiroRealm(UserService userService) {
        this.userService = userService;
    }

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 拿到 token
        String token = (String) authenticationToken.getCredentials();

        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token 错误");
        }

        User user = userService.getUser(username);
        if (user == null) {
            throw new AuthenticationException("User 不存在");
        }

        if (! JWTUtil.verify(token, username, user.getPassword())) {
            throw new AuthenticationException("token 验证失败");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
