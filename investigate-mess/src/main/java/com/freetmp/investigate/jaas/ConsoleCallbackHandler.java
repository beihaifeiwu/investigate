package com.freetmp.investigate.jaas;

import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by LiuPin on 2015/4/3.
 */
public class ConsoleCallbackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for(Callback callback : callbacks){
            if(callback instanceof NameCallback){
                NameCallback nameCallback = (NameCallback) callback;
                System.out.println(nameCallback.getPrompt());
                System.out.flush();
                String name = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                nameCallback.setName(name);
            }else if(callback instanceof PasswordCallback){
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                System.out.println(passwordCallback.getPrompt());
                System.out.flush();
                String password = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                passwordCallback.setPassword(password.toCharArray());
            }else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }
}
