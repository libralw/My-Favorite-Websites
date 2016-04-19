/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.FavoriteBean;
import databeans.UserBean;
import formbeans.IdForm;

/*
 * Removes a photo.  Given an "id" parameter.
 * Checks to see that id is valid number for a photo and that
 * the logged user is the owner.
 * 
 * Sets up the "userList" and "photoList" request attributes
 * and if successful, forwards back to to "manage.jsp".
 */
public class UpdateClickAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;

    public UpdateClickAction(Model model) {
    	favoriteDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "update.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList", userDAO.getUsers());

	    	IdForm form = formBeanFactory.create(request);
	    	
			int id = form.getIdAsInt();
    		favoriteDAO.updateClickCount(id);
    		
    		UserBean user = (UserBean) request.getSession().getAttribute("user");
    		// Be sure to get the favList after the delete
        	FavoriteBean[] favList = favoriteDAO.getUserFavorites(user.getUserId());
	        request.setAttribute("favList", favList);

	        return favoriteDAO.getFavoriteURL(id);
//	        return "manage.do";
	        
		} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
		} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
