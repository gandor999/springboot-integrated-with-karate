
package demo;

import com.intuit.karate.junit5.Karate;

class PersonTest {

    @Karate.Test
    Karate TestAll() {
        return Karate.run().relativeTo(getClass());
    }

}
