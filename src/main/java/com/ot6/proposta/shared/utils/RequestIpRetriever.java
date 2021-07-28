package com.ot6.proposta.shared.utils;

import javax.servlet.http.HttpServletRequest;

public interface RequestIpRetriever {

    static String retrieve(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forward-For");

        if(ipAddress == null || ipAddress.equals("")){
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }
}
