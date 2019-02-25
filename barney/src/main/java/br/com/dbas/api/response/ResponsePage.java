package br.com.dbas.api.response;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ResponsePage<T> {

    private Page<T> data;
    private List<String> errors;

    public ResponsePage() {
    }

    public Page<T> getData() {
        return data;
    }

    public void setData(Page<T> data) {
        this.data = data;
    }

    public List<String> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList<String>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}