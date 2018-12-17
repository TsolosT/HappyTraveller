package ctrlcctrlv.happytraveller.checkIfIsValid;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CheckIfNumberIsValidTest
{
    CheckIfNumberIsValidForTimePurpose checkIfNumberIsValidForTimePurpose;

    @Before
    public void setUp()
    {
        checkIfNumberIsValidForTimePurpose = new CheckIfNumberIsValidForTimePurpose();
    }

    @Test
    public void theNumberUserGaveIsCorrectTest1()
    {
        int numberExpected =120;

        assertEquals(numberExpected,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("2"));
    }

    @Test
    public void theNumberUserGaveIsCorrectTest2()
    {
        int numberExpected =61;

        assertEquals(numberExpected,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("1.1"));
    }

    @Test
    public void theNumberUserGaveIsCorrectTest3()
    {
        int numberExpected =1;

        assertEquals(numberExpected,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("0.1"));
    }

    @Test
    public void theNumberUserGaveIsCorrectTest4()
    {
        int numberExpected =179;

        assertEquals(numberExpected,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("2.59"));
    }

    @Test
    public void theNumberUserGaveIsCorrectTest5()
    {
        int numberExpected =1;

        assertEquals(numberExpected,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs(".1"));
    }

    @Test
    public void theNumberUserGaveIsCorrectTest6()
    {
        int numberExpected =383;

        assertEquals(numberExpected,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("6.23"));
    }

    @Test
    public void theNumberUserGaveIsCorrectTest7()
    {
        int numberExpected =779;

        assertEquals(numberExpected,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("12.59"));
    }

    @Test
    public void theNumberUserGaveIsCorrectTest8()
    {
        int numberExpected =0;

        assertEquals(numberExpected,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("234234"));
    }

    @Test
    public void theNumberUserGaveIsNotCorrectTest1()
    {
        int numberExpeted = 342534;

        assertNotEquals(numberExpeted,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("342534"));
    }

    @Test
    public void theNumberUserGaveIsNotCorrectTest2()
    {
        int numberExpeted = 11;

        assertNotEquals(numberExpeted,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("1.1"));
    }

    @Test
    public void theNumberUserGaveIsNotCorrectTest3()
    {
        int numberExpeted = 100;

        assertNotEquals(numberExpeted,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("0"));
    }

    @Test
    public void theNumberUserGaveIsNotCorrectTest4()
    {
        int numberExpeted = 0;

        assertNotEquals(numberExpeted,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("12.59"));
    }

    @Test
    public void theNumberUserGaveIsNotCorrectTest5()
    {
        int numberExpeted = 1259;

        assertNotEquals(numberExpeted,checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs("12.59"));
    }
}
