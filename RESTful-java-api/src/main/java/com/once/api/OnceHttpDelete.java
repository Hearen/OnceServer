package com.once.api;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class OnceHttpDelete extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";
    public String getMethod() {
        return METHOD_NAME;
    }
    public OnceHttpDelete(final String uri) {
        super();
        setURI(URI.create(uri));
    }
    public OnceHttpDelete(final URI uri) {
        super();
        setURI(uri);
    }
    public OnceHttpDelete() {
        super();
    }
}
