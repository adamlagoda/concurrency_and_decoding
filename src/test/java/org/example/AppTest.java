package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testExample1() {
        // given
        final var encoded = "3[a]2[bc]";
        final var decoded = "aaabcbc";

        // when
        final var result = App.decode(encoded);

        // then
        assertEquals(decoded, result);
    }

    public void testExample3() {
        // given
        final var encoded = "gf120[abc]hn3[cd]ef";

        // when
        final var result = App.decode(encoded);
        System.out.println(result);

        // then
        assertNotNull(result);
    }
}
