/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.UserBean;
import formbeans.LoginForm;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private UserDAO userDAO;

	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	
        	LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user", null);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "login.jsp";
	        }
	        
	        // this form is from a visitor
	        if (form.getEmail().equals("public")) {
	        	UserBean user = new UserBean();
	        	user.setFirstName("Public");
	        	user.setLastName("visitor");
	        	session = request.getSession();
		        session.setAttribute("user", user);
		        return "list.do?email=potus@whitehouse.gov";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        // Look up the user
	        UserBean user = userDAO.read(form.getEmail());
	        
	        if (user == null) {
	            errors.add("User Name not found");
	            return "login.jsp";
	        }

	        // Check the password
	        if (!user.getPassword().equals(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        session = request.getSession();
	        session.setAttribute("user", user);

	        return "manage.do";
	        
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}

