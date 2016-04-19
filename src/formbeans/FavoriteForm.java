/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FavoriteForm extends FormBean{
	private String link;
	private String comment;
	private String action;
	
	public String getLink()    	   { return link;     }
	public String getComment()     { return comment; }
	public String getAction()      { return action;  }
	
	public void setLink(String s) 	  { link = s.trim();     }
	public void setComment(String s)  { comment = trimAndConvert(s,"<>\""); }
	public void setAction(String s)   { action = s;         }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (link == null || link.length() == 0) {
			errors.add("URL is required");
		}
		
		if (!link.startsWith("http://")) {
			errors.add("Please start your URL with 'http://'");
		}
		
		if (comment == null || comment.length() == 0) {
			errors.add("Comment is required");
		}
		
		if (action == null || action.length() == 0) {
			errors.add("Action is required");
		}
        
        if (!action.equals("Add Favorites")) errors.add("Invalid action");

		return errors;
	}

}
