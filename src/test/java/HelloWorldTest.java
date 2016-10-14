import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldTest {

    @Test
    public void shouldFail() {
        String hello = new HelloWorld().sayIt();

        assertThat(hello).isEqualTo("Hello");
    }
}
