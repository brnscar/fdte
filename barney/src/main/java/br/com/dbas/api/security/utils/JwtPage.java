package br.com.dbas.api.security.utils;

import br.com.dbas.api.security.enums.HttpMethod;

import java.util.List;

public class JwtPage {

    private String page;
    private List<HttpMethod> methods;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<HttpMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<HttpMethod> methods) {
        this.methods = methods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JwtPage jwtPage = (JwtPage) o;

        if (page != null ? !page.equals(jwtPage.page) : jwtPage.page != null) return false;
        return methods != null ? methods.equals(jwtPage.methods) : jwtPage.methods == null;
    }

    @Override
    public int hashCode() {
        int result = page != null ? page.hashCode() : 0;
        result = 31 * result + (methods != null ? methods.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JwtPage{" +
                "page='" + page + '\'' +
                ", methods=" + methods +
                '}';
    }
}
