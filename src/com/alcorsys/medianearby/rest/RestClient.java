package com.alcorsys.medianearby.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

public class RestClient
{
    private String host = "10.0.2.2";
    private String port = "8080";
    private String applicationPath = "/";
    private String apiPath = "/";
    private String loginPath = "j_spring_security_check";
    private String logoutPath = "medianearby_logout";
    private final String usernameInputFieldName = "j_username";
    private final String passwordInputFieldName = "j_password";
    private final StatefulRestTemplate template = new StatefulRestTemplate();

    /**
     * This method logs into a service by doing an standard http using the configuration in this class.
     *
     * @param username
     *            the username to log into the application with
     * @param password
     *            the password to log into the application with
     *
     * @return the url that the login redirects to
     */
    public String login(String username, String password)
    {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add(usernameInputFieldName, username);
        form.add(passwordInputFieldName, password);
        String myLoginUrl = loginUrl();
        URI location = this.template.postForLocation(myLoginUrl, form);
        return location.toString();
    }

    /**
     * Logout by doing an http get on the logout url
     *
     * @return result of the get as ResponseEntity
     */
    public ResponseEntity<String> logout()
    {
        return this.template.getForEntity(logoutUrl(), String.class);
    }

    public String applicationUrl(String relativePath)
    {
        return applicationUrl() + "/" + checkNotNull(relativePath);
    }

    public String apiUrl(String relativePath)
    {
        return applicationUrl(apiPath + "/" + checkNotNull(relativePath));
    }

    public StatefulRestTemplate template()
    {
        return template;
    }

    public String serverUrl()
    {
        return "http://" + host + ":" + port;
    }

    public String applicationUrl()
    {
        return serverUrl() + "/" + checkNotNull(applicationPath);
    }

    public String loginUrl()
    {
        return applicationUrl(loginPath);
    }

    public String logoutUrl()
    {
        return applicationUrl(logoutPath);
    }

    public String apiUrl()
    {
        return applicationUrl(apiPath);
    }

    public void setLogoutPath(String logoutPath)
    {
        this.logoutPath = logoutPath;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getApplicationPath()
    {
        return applicationPath;
    }

    public void setApplicationPath(String contextPath)
    {
        this.applicationPath = contextPath;
    }

    public String getApiPath()
    {
        return apiPath;
    }

    public void setApiPath(String apiPath)
    {
        this.apiPath = apiPath;
    }

    public String getLoginPath()
    {
        return loginPath;
    }

    public void setLoginPath(String loginPath)
    {
        this.loginPath = loginPath;
    }

    public String getLogoutPath()
    {
        return logoutPath;
    }

    private String checkNotNull(String path){
        if(path != null) return path;
        return "";
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("RestClient [\n serverUrl()=");
        builder.append(serverUrl());
        builder.append(", \n applicationUrl()=");
        builder.append(applicationUrl());
        builder.append(", \n loginUrl()=");
        builder.append(loginUrl());
        builder.append(", \n logoutUrl()=");
        builder.append(logoutUrl());
        builder.append(", \n apiUrl()=");
        builder.append(apiUrl());
        builder.append("\n]");
        return builder.toString();
    }
}