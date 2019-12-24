package io.github.mat3e;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloServiceTest {

    private HelloService SUT = new HelloService();

    @Test
    public void test_null_prepare_greeting_returnsFallBackValue(){
        var result = SUT.prepareGreeting(null);

        assertEquals("Hello " + HelloService.FALLBACK_NAME, result);
    }

    @Test
    public void test_name_prepare_greeting_returnsGreeting_with_name(){
        var name = "John";
        var result = SUT.prepareGreeting(name);

        assertEquals("Hello " + name, result);
    }
}


