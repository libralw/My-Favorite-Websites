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
import formbeans.FavoriteForm;

public class AddAction extends Action {
	private FormBeanFactory<FavoriteForm>  formBeanFactory  = FormBeanFactory.getInstance(FavoriteForm.class);
	
	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

	public AddAction(Model model) {
		userDAO = model.getUserDAO();
		favoriteDAO = model.getFavoriteDAO();
	}

	public String getName() { return "add.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
        try {
            // Set up user list for nav bar
			request.setAttribute("userList", userDAO.getUsers());

//			Integer userId = (Integer) request.getSession().getAttribute("userId");
			UserBean user = (UserBean) request.getSession(false).getAttribute("user");
        	FavoriteBean[] favList = favoriteDAO.getUserFavorites(user.getUserId());
	        request.setAttribute("favList", favList);

			FavoriteForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) 
	        	return "manage.jsp";
	        
	        FavoriteBean fav = new FavoriteBean();
	        fav.setUserId(user.getUserId());
	        fav.setLink(form.getLink());
	        fav.setComment(form.getComment());
	        fav.setClickCount(0);
	        
	        if (form.getAction().equals("Add Favorites")) {
        		favoriteDAO.create(fav);
        	} else {
        		errors.add("Invalid action: " + form.getAction());
        	}
       		
	        request.setAttribute("favList", favoriteDAO.getUserFavorites(user.getUserId()));
	        
	        return "manage.do";
	       
	 	} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "manage.jsp";
	 	} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "manage.jsp";
		}
    }
}

