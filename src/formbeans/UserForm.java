/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class UserForm extends FormBean {
	private String email = "";
	
	public String getEmail()  { return email; }
	
	public void setEmail(String s)  { email = trimAndConvert(s,"<>>\"]"); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (email == null || email.length() == 0) {
			errors.add("User Email is required");
		}
		
		return errors;
	}
}
