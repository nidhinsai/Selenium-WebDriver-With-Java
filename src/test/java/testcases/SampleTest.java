package testcases;

import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test()
    public void test_sample() {
        openUrl("https://demoqa.com");
    }

}
