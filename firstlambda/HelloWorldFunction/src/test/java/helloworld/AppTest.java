package helloworld;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AppTest {
    @Test
    public void successfulResponse() {
        App app = new App();
        String result = app.hello("Sanjay");
        assertNotNull(result);
        assertTrue(result.contains("Lambda Functions are super easy and awesome! Sanjay"));
    }
}
