package test.testng;

import org.testng.annotations.*;

public class TestNGHooksEx {

    @Test
    public void testA(){
        System.out.println("Test A");
    }

    @Test
    public void testB(){
        System.out.println("Test B");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }
}
