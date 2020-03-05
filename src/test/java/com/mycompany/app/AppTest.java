package com.mycompany.app;

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
    public void testLogin()
    {
        assertTrue( true );
    }

    public void testWorkflow()
    {
        assertTrue( true );
    }

    public void testDocumentVersion()
    {
        assertTrue( true );
    }

    //com.mycompany.app.AppTest.testChandra - JIRA
    //package + AppTest + test method
    public void testRendition()
    {
        assertTrue( true );
    }
}
