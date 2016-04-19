/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {
    
	private String emailAddress;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword ;
    private String action;
    
    public String getEmail()  		 		 { return emailAddress; 	 		}
    public String getFirstName()  	 		 { return firstName; 		}
    public String getLastName()  	 		 { return lastName;  		}
    public String getPassword()  	 		 { return password;  		}
    public String getConfirmPassword()  	 { return confirmPassword;  }
    public String getAction()    	 		 { return action;    		}
    
    public void setEmail(String s)  		  	  { emailAddress= trimAndConvert(s,"<>\"");      }
    public void setFirstName(String s)     		  { firstName = trimAndConvert(s,"<>\""); 		 }
    public void setLastName(String s)      		  { lastName = trimAndConvert(s,"<>\"");  		 }
    public void setPassword(String s)      		  { password = s.trim();  		 }
    public void setConfirmPassword(String s)      { confirmPassword = s.trim();  }
    public void setAction(String s)        		  { action = s;           		 }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (emailAddress == null || emailAddress.length() == 0) errors.add("Email Address is required");
        if (firstName == null || firstName.length() == 0) {
        	errors.add("First Name is required");
        } else if (firstName.matches(".*[<>\"].*")) {
			errors.add("User Name cannot contain angle brackets or quotes");
		}
        
        if (lastName == null || lastName.length() == 0) {
        	errors.add("Last Name is required");
        } else if (lastName.matches(".*[<>\"].*")) {
			errors.add("User Name cannot contain angle brackets or quotes");
		}
        
        if (password == null || password.length() == 0) errors.add("Password is required");
        
        if (confirmPassword == null || confirmPassword.length() == 0) errors.add("Confirm Password is required");
        
        if (action == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!action.equals("Register")) errors.add("Invalid action");
        
        if (!password.equals(confirmPassword)) {
			errors.add("Password and Confirm Password do not match");
		}
		
        return errors;
    }
    
}
