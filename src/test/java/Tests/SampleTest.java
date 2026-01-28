package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

    public class SampleTest {

        @Test
        public void passingTest() {
            Assert.assertTrue(true);
        }

        @Test
        public void failingTest() {
            Assert.assertFalse(false);
        }
    }

