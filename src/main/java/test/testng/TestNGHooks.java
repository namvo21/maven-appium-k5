package test.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNGHooks {
    @Test(priority=1)
    public void testB(){
        System.out.println("Test B");
    }

    @Test(priority=3)
    public void testA(){
        System.out.println("Test A");
        String actualValue = "testA";
        String expectedValue = "testA ";

        // Soft assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualValue, expectedValue, "[ERR] Err msg 01");
        softAssert.assertTrue(actualValue.equalsIgnoreCase(expectedValue),"[ERR] Err msg 02");

        //
        softAssert.assertAll();

        // Hard assertion
        //Assert.assertEquals(actualValue, expectedValue, "[ERR] Login Dialog Title is incorrect!");
        //Assert.assertTrue(actualValue.equalsIgnoreCase(expectedValue),"[ERR] Login Dialog Title is incorrect!");
    }

    @Test(priority = 3, dependsOnMethods = {"testA"})
    public void testC(){
        System.out.println("Test C");
    }
}
