package webserver.http;

import com.google.common.base.Strings;

import java.io.IOException;

public class HttpRequest {
    private static final String HEADER_COOKIE_KEY = "Cookie";

    private RequestLine requestLine;
    private HttpHeaders httpHeaders;
    private Cookie cookie;
    private RequestBody requestBody;

    private HttpRequest(RequestLine requestLine, HttpHeaders httpHeaders, RequestBody requestBody) {
        this.requestLine = requestLine;
        this.httpHeaders = httpHeaders;
        this.requestBody = requestBody;
        this.cookie = Cookie.parse(httpHeaders.get(HEADER_COOKIE_KEY));
    }

    public HttpRequest(RequestLine requestLine, HttpHeaders httpHeaders) {
        this.requestLine = requestLine;
        this.httpHeaders = httpHeaders;
        this.cookie = Cookie.parse(httpHeaders.get(HEADER_COOKIE_KEY));
    }

    public static HttpRequest parse(RequestStream requestStream) throws IOException {
        RequestLine requestLine = RequestLine.parse(requestStream.requestLine());
        HttpHeaders httpHeaders = HttpHeaders.parse(requestStream.header());

        if (!Strings.isNullOrEmpty(requestStream.body())) {
            RequestBody requestBody = RequestBody.parse(requestStream.body());
            return new HttpRequest(requestLine, httpHeaders, requestBody);
        }

        return new HttpRequest(requestLine, httpHeaders);
    }

    public HttpMethod getMethod() {
        return this.requestLine.getMethod();
    }

    public URI getUri() {
        return this.requestLine.getUri();
    }

    public String getHeaderValue(String key) {
        return this.httpHeaders.get(key);
    }

    public RequestLine getRequestLine() {
        return this.requestLine;
    }

    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public String cookieValue(String key) {
        return this.cookie.get(key);
    }

    public String bodyValue(String key) {
        return this.requestBody.get(key);
    }

    public String getPath() {
        return getUri().getPath();
    }
}