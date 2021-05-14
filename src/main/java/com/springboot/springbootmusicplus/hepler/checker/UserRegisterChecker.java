package com.springboot.springbootmusicplus.hepler.checker;

import com.springboot.springbootmusicplus.model.request.UserRegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/14 17:21
 */
@Component
public class UserRegisterChecker {

    public String checkUserRegisterReq(UserRegisterRequest req) {
        if (req == null) {
            return "请求参数不能为空";
        }
        if (StringUtils.isBlank(req.getUserName())) {
            return "用户名不能为空";
        }
        if (StringUtils.isBlank(req.getUserPassword())) {
            return "密码不能为空";
        }
        return null;
    }

}
