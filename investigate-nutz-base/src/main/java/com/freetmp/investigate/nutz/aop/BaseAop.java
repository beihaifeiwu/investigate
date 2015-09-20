package com.freetmp.investigate.nutz.aop;

import org.nutz.aop.*;
import org.nutz.aop.asm.AsmClassAgent;
import org.nutz.aop.matcher.MethodMatcherFactory;

/**
 * Created by LiuPin on 2015/2/28.
 */
public class BaseAop {  //被AOP的类,必须是public的非abstract类!

    /*将要被AOP的方法*/
    public boolean login(String username, String password) throws Throwable {
        if ("wendal".equals(username) && "qazwsxedc".equals(password)) {
            System.out.println("登陆成功");
            return true;
        }
        System.out.println("登陆失败");
        return false;
    }

    private static ClassDefiner cd = new DefaultClassDefiner();

    public static void main(String ... args) throws Throwable{
        //无AOP的时候
        BaseAop ua = new BaseAop(); //直接new,将按原本的流程执行
        ua.login("wendal", "qazwsxedc");

        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");

        //有AOP的时候
        ClassAgent agent = new AsmClassAgent();
        LogInterceptor log = new LogInterceptor();
        agent.addInterceptor(MethodMatcherFactory.matcher("^login$"),log);
        //返回被AOP改造的Class实例
        Class<? extends BaseAop> ba = agent.define(cd,BaseAop.class);
        BaseAop ua2 = ba.newInstance();
        ua2.login("wendal", "qazwsxedc");//通过日志,可以看到方法执行前后有额外的日志

    }
}

class LogInterceptor implements MethodInterceptor {
    @Override
    public void filter(InterceptorChain chain) throws Throwable {
        System.out.println("方法即将执行 -->" + chain.getCallingMethod());
        chain.doChain();// 继续执行其他拦截器,如果没有,则执行原方法
        System.out.println("方法执行完毕 -->" + chain.getCallingMethod());
    }
}