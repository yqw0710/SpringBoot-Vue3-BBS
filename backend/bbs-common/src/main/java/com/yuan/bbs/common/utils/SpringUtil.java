package com.yuan.bbs.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Primary
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 解析SpEL表达式,配合注解和Aop切面处理使用
     *
     * @param key    SpEL表达式
     * @param method 通过反射得到的方法 Method
     * @param args   通过反射得到的方法参数 Object[]
     * @param clazz  需要返回的类型 String Boolean之类的
     * @param <T>    返回泛型
     * @return 解析后得到的值
     */
    public static <T> T parseSpEL(String key, Method method, Object[] args, Class<T> clazz) {
        // SpEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 获取被拦截方法参数名列表(使用Spring支持类库)
        String[] paraNameArr = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        // 把方法参数放入SpEL上下文中
        for (int i = 0; i < Objects.requireNonNull(paraNameArr).length; i++) {
            // 使用setVariable方法来注册自定义变量
            context.setVariable(paraNameArr[i], args[i]);
        }
        // 使用SpEL进行key的解析
        return new SpelExpressionParser().parseExpression(key).getValue(context, clazz);
    }

}
