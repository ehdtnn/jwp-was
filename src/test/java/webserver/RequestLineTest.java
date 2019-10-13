package webserver;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestLineTest {
    @Test
    public void parse() {
        assertThat(RequestLine.parse("GET /users HTTP/1.1").getUri()).isEqualTo("/users");
        assertThat(RequestLine.parse("POST /users HTTP/1.1").getUri()).isEqualTo("/users");
    }
}
