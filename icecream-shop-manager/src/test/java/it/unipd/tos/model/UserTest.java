////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void setup() {
        user = new User("Davide", 21);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNullUsernameParam() {
        new User(null, 21);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNonPositiveAgeParam() {
        new User("Davide", 0);
    }

    @Test
    public void testUsernameGetter() {
        assertEquals("Davide", user.getUsername());
    }

    @Test
    public void testAgeGetter() {
        assertEquals(21, user.getAge());
    }

}