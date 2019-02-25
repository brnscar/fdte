package br.com.dbas.api.security.entities;

import br.com.dbas.api.security.enums.HttpMethod;

import javax.persistence.*;

@Entity
@Table(name = "endpoint")
public class Endpoint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "httpMethod", nullable = false)
    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    private Page page;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }


    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endpoint endpoint = (Endpoint) o;

        if (id != null ? !id.equals(endpoint.id) : endpoint.id != null) return false;
        if (url != null ? !url.equals(endpoint.url) : endpoint.url != null) return false;
        if (description != null ? !description.equals(endpoint.description) : endpoint.description != null)
            return false;
        if (httpMethod != endpoint.httpMethod) return false;
        return page != null ? page.equals(endpoint.page) : endpoint.page == null;
    }


    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (httpMethod != null ? httpMethod.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        return result;
    }

}
