import static org.junit.Assert.*;

import org.junit.Test;

public class junitTestcase {
	
	CheckInputValues c = new CheckInputValues();
	Boolean actual = c.Check("test");
	
	Boolean actualresult = c.CheckIfString("test");
	
	 

	@Test
	public void test() {
		assertEquals(true, actual);
	}
	
	@Test
	public void StringCheck(){
		assertEquals(true, actualresult);
	}

}
