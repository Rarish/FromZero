package com.frame.service;

import com.constant.Constants;
import com.entity.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.utils.uuid.IdUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component("tokenService")
public class TokenService {
    @Value("${token.privateKey}")
    private String secret;

    @Value("${token.expireTime}")
    private int expireTime;

    @Autowired
    private RedisTemplate redisTemplate;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 未登录时创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        if(loginUser.getUuid() == null){
            String uuid = IdUtils.fastUUID();
            loginUser.setUuid(uuid);
        }

        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, loginUser.getUuid());
        return createToken(claims);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(new Date().getTime());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getUuid());
        redisTemplate.opsForValue().set(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(String token) {
        Claims claims = parseToken(token);
        // 解析对应的权限以及用户信息
        String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
        String userKey = getTokenKey(uuid);
        LoginUser user = (LoginUser) redisTemplate.opsForValue().get(userKey);
        return user;
    }

    public Boolean deleteLoginUser(String token){
        Claims claims = parseToken(token);
        String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
        String userKey = getTokenKey(uuid);
        return redisTemplate.delete(userKey);
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace(Constants.TOKEN_PREFIX,""))
                .getBody();
    }

    /**
     * 验证令牌有效期
     *
     * @param loginUser
     * @return 令牌
     */
    public boolean verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = new Date().getTime();
        if (expireTime < currentTime) {
            return false;
        }
        return true;
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

    public String isLoginAlready(Long userId){
        Set<String> keys = redisTemplate.keys("*");
        for(String key : keys){
            LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get(key);
            if(loginUser.getUser().getUserId().equals(userId)){
               return loginUser.getUuid();
            }
        }
        return null;
    }
}
