package com.freetmp.investigate.jaas;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Iterator;

/**
 * Created by LiuPin on 2015/4/3.
 */
public class JaasDemo {

    public static void main(String... args) throws LoginException {

        System.setProperty("java.security.auth.login.config","C:\\Users\\Administrator\\git\\investigate\\investigate-mess\\src\\main\\resources\\simple.conf");

        LoginContext ctx = new LoginContext("SimpleLogin", new ConsoleCallbackHandler());
        ctx.login();
        Subject subj = ctx.getSubject();

        System.out.println("Login assigned these principals:");
        Iterator<Principal> it = subj.getPrincipals().iterator();
        while (it.hasNext()){
            Principal pl = it.next();
            System.out.println("\t" + pl.getName());
        }

        Subject.doAsPrivileged(subj, new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                System.out.println("I'm running");
                return null;
            }
        },null);

        ctx.logout();
    }
}
