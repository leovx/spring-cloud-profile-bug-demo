package com.jinghao.demo.auth;

public class LocalBypassAuthManager implements AuthManager {

    @Override
    public boolean auth() {
        // due to local environment restrictions,
        // bypass and always return true for other irrelevant functional testing purpose
        return true;
    }

}
