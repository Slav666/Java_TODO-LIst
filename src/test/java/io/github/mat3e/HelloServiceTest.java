package io.github.mat3e;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";

//    private HelloService SUT = new HelloService();

    @Test
    public void test_prepare_greeting_nullName_returnsGreetingWithFallbackName() {

        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        var result = SUT.prepareGreeting(null, "-1");
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME, result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        String name = "John";
        var result = SUT.prepareGreeting(name, "-1");
        assertEquals(WELCOME + " " + name, result);
    }

    @Test
    public void test_prepare_greeting_nullLang_returnsGreeting_withFallBackIdLang() {


        var mockRepository = fallbackLangIdRepository();

        var SUT = new HelloService(mockRepository);
        var result = SUT.prepareGreeting(null, null);
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME, result);
    }

    @Test
    public void test_prepare_greeting_textLang_returnsGreeting_withFallBackIdLang() {


        var mockRepository = fallbackLangIdRepository();

        var SUT = new HelloService(mockRepository);
        var result = SUT.prepareGreeting(null, "abc");
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME, result);
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

        private LangRepository alwaysReturningHelloRepository () {
            return new LangRepository() {
                @Override
                Optional<Lang> findById(Integer id) {
                    return Optional.of(new Lang(null, WELCOME, ""));
                }
            };
        }
}



