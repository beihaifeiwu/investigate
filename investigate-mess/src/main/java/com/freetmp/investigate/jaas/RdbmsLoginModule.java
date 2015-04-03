package com.freetmp.investigate.jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * Created by LiuPin on 2015/4/3.
 */
public class RdbmsLoginModule implements LoginModule {

    Subject subject;

    CallbackHandler callbackHandler;

    Map<String,?> sharedState;

    Map<String,?> options;

    String url;
    String driverClass;
    boolean debug;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {

        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

        url = (String) options.get("url");
        driverClass = (String) options.get("driver");
        debug = "true".equalsIgnoreCase((String) options.get("debug"));

    }

    @Override
    public boolean login() throws LoginException {
        if(callbackHandler == null) throw new LoginException("no handler");
        NameCallback nameCallback = new NameCallback("user: ");
        PasswordCallback passwordCallback = new PasswordCallback("password: ",true);
        Callback[] callbacks = new Callback[]{nameCallback,passwordCallback};
        try {
            callbackHandler.handle(callbacks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        String username = nameCallback.getName();
        String password = new String(passwordCallback.getPassword());
        boolean success = validate(username,password);
        return success;
    }

    private boolean validate(String username, String password) {

        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }
}
