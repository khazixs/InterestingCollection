package com.ic.learn.service.design.removeIfElse;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
@Lazy(value = false)
@Component
public class PolicyFactory {
    private static final Map<String,Handler> strategyMap = new HashMap<>();

    public static Handler getInvokeStrategy(String name){
        return strategyMap.get(name);
    }

    public static void register(String name,Handler handler){
        if (StringUtils.isEmpty(name)||handler == null){
            return;
        }
        strategyMap.put(name,handler);
    }
}
