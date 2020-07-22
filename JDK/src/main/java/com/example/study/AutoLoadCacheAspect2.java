package com.example.study;

import com.google.common.hash.Hashing;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 扫描@Cache注解, 仅当配置文件中 cache.enable=true 时才进行拦截
 * @author jiayu.qiu
 */
@Aspect
@Configuration
public class AutoLoadCacheAspect2 {
    private Logger logger = LoggerFactory.getLogger(AutoLoadCacheAspect2.class);

    private static Map<String,Object> cache = new ConcurrentHashMap();
    @Around("@annotation(localcache)")
    public Object cacheAble(ProceedingJoinPoint pjp, LocalCache localcache) throws Throwable {
        Object[] args = pjp.getArgs();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String key = className+"."+methodName;
        key = createKey(key, args);
        Object result = cache.getOrDefault(key,null);
        if(result==null){
            result = pjp.proceed();
            cache.put(key,result);
        }
        return result;
    }

    private String createKey(String methodName, Object[] objects) {
        StringBuilder sbs = new StringBuilder();
        sbs.append(methodName + "_");
        for (Object object : objects) {
            if (object instanceof Double) {
                sbs.append(object);
            } else if (object instanceof String) {
                sbs.append(object.hashCode());
            } else if (object instanceof Integer) {
                sbs.append(object);
            } else if (object instanceof Long) {
                sbs.append(object);
            } else {
                sbs.append(Hashing.murmur3_128().hashString(JsonKit.toJson(object), Charset.defaultCharset()));
            }
            sbs.append("_");
        }
        return sbs.toString();
    }

    

    
}
