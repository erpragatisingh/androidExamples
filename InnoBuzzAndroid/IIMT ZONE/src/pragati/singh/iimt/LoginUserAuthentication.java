package pragati.singh.iimt;

import pragati.singh.iimt.User;


public class LoginUserAuthentication {
	private User currentUser = null;
	//private static Hashtable USERS  = new Hashtable();
	
	
	public LoginUserAuthentication() {
		currentUser = new User();
		
	}
	
	public void setUserDetails(String p_user_name,String p_password) {
		currentUser.setUserName(p_user_name);
		currentUser.setPassword(p_password);
		
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	
	public boolean checkLogin() {
		 String l_user_name = currentUser.getUserName();
		 String l_password  = currentUser.getPassword();
		 
		/* if(l_user_name == null) {
			 tv3.setText("LOGIN FAILED "+count+" TIMES");
			 
			 
			 edt1	=	(EditText)findViewById(R.id.editText1);
		        edt2	=	(EditText)findViewById(R.id.editText2);
		        
			
		 }*/
		
		if(l_user_name.equals("iimtp")&& l_password.equals("iimtp")) {
			return true;
		} 
    	return false;
		
	}

}
