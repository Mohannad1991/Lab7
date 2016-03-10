
public class CheckInputValues {
	
	public Boolean Check(String test){
		if(test!=null){
			return true;
		}
		else
			return false;
	}
	
	public Boolean CheckIfString(String test){
		
		try { 
	        Integer.parseInt(test); 
	    }
		catch(NumberFormatException  e){
			return true;
		}
		return false;
	}

}
