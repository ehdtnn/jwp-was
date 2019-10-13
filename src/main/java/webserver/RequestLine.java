package webserver;

public class RequestLine {

    private String method;
    private String uri;
    private String version;

    public RequestLine(String s) {
        String[] tokens = s.split(" ");
        method = tokens[0];
        uri = tokens[1];
        version = tokens[2];
    }

    public static RequestLine parse(String s) {
        return new RequestLine(s);
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getVersion() {
        return version;
    }
}
