package com.lixinming.test.controller;

import com.lixinming.test.util.AccessLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lixinming
 * @Date 2022/1/27 14:57
 * @Version 1.0
 */
@RestController
@RequestMapping("/")
public class CurLimitController {
    /**
     * 使用实例：
     * 测试限流注解，下面配置说明该接口 60秒内最多只能访问 10次，保存到redis的键名为 limit_test，
     * 即 prefix + "_" + key，也可以根据 IP 来限流，需指定limitType = LimitType.IP
     */
    @AccessLimit(key = "test", period = 60, count = 10, name = "resource", prefix = "limit")
    @GetMapping("/test")
    public String testLimit() {
        return "success";
    }
}
