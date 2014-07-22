package employ;

import user.parts.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RegInfCheckTest.class,OutLogTest.class,RegInfDAOTest.class })
public class AllTests {

}
