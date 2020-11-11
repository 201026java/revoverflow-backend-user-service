package com.revature;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserControllerTest.class, UserServiceTest.class })
public class AllTests {

}
