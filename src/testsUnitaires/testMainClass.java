package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jdbc.main.MainClass;

class testMainClass {

	 private final InputStream systemIn = System.in;
	    private final PrintStream systemOut = System.out;
	    private ByteArrayInputStream testIn;
	    private ByteArrayOutputStream testOut;

	    @Before
	    public void setUpOutput() {
	        testOut = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(testOut));
	    }

	    @After
	    public void restoreSystemInputOutput() {
	        System.setIn(systemIn);
	        System.setOut(systemOut);
	    }

	    @Test
	    public void testValidEmail() {
	        assertTrue(MainClass.isValidEmail("test@example.com"));
	    }

	    @Test
	    public void testInvalidEmail() {
	        assertFalse(MainClass.isValidEmail("test@example"));
	        assertFalse(MainClass.isValidEmail("testexample.com"));
	    }

	    @Test
	    public void testValidPassword() {
	        assertTrue(MainClass.isValidPassword("Test@12345"));
	    }

	    @Test
	    public void testInvalidPassword() {
	        assertFalse(MainClass.isValidPassword("password"));
	        assertFalse(MainClass.isValidPassword("test12345"));
	        assertFalse(MainClass.isValidPassword("TEST@12345"));
	    }

	    @Test
	    public void testHashPassword() {
	        String hashedPassword = MainClass.hashPassword("Test@12345");
	        assertEquals("fa51a6c0ebc94a6b5ae454787e5f83a894ebef3eb3f44f8f84e6ce77bce5a9e4", hashedPassword);
	    }

	    @Test
	    public void testInsertEmployee() {
	        // Simulate user input
	        String userInput = "John\nDoe\n1234567890\n01/01/1990\njohn.doe@example.com\nTest@12345\n";
	        testIn = new ByteArrayInputStream(userInput.getBytes());
	        System.setIn(testIn);

	        // Test insertEmployee method
	        MainClass.main(null);

	        // Verify the output
	        String expectedOutput = "Saisissez votre Nom : \nSaisissez votre Prénom : \nSaisissez votre Numéro Tel (10 chiffres) : \n" +
	                "Entrez votre date de naissance (format dd/MM/yyyy) : \nSaisissez votre Mail : \nSaisissez votre mot de passe : \n";
	        assertEquals(expectedOutput, testOut.toString());
	    

	}
}
