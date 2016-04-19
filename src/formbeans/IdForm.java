/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class IdForm extends FormBean {
	
	private String id;

	public String getId() { return id; }
	
	public int getIdAsInt() {

		if (id == null) return -1;
		
		try {
			return Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return -1;
		}
		
	}
	
	public void setId(String id) { this.id = id; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (id == null || id.length() == 0) {
			errors.add("Id is required");
			return errors;
		}

		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			errors.add("Id is not an integer");
		}
		
		return errors;
	}
	
}
