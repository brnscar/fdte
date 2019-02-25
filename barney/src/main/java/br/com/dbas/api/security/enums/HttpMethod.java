package br.com.dbas.api.security.enums;

public enum HttpMethod {
    PUT,
    GET,
    POST,
    DELETE;

    public String getHttpMethod() {
        return super.toString();
    }
}
