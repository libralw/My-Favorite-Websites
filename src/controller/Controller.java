/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databeans.FavoriteBean;
import databeans.UserBean;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

		Model model = new Model(getServletConfig());

		Action.add(new LoginAction(model));
		// Action.add(new VisitorLoginAction(model));
		Action.add(new RegisterAction(model));
		Action.add(new LogoutAction(model));
		Action.add(new AddAction(model));
		Action.add(new ListAction(model));
		Action.add(new DeleteAction(model));
		Action.add(new ChangePwdAction(model));
		Action.add(new ManageAction(model));
		Action.add(new UpdateClickAction(model));

		checkInitUsers(model.getUserDAO(), model.getFavoriteDAO());

	}

	private void checkInitUsers(UserDAO userDAO, FavoriteDAO favoriteDAO) {
		// TODO Auto-generated method stub
		try {
			if (userDAO.getCount() == 0) {
				UserBean u1 = new UserBean();
				u1.setEmail("potus@whitehouse.gov");
				u1.setFirstName("Barack");
				u1.setLastName("Obama");
				u1.setPassword("Michelle");
				userDAO.createAutoIncrement(u1);

				FavoriteBean f1 = new FavoriteBean();
				f1.setLink("http://www.whitehouse.gov");
				f1.setComment("My home");
				f1.setUserId(u1.getUserId());
				favoriteDAO.createAutoIncrement(f1);
				
				FavoriteBean f2 = new FavoriteBean();
				f2.setLink("http://www.whitehouse.gov");
				f2.setComment("My home");
				f2.setUserId(u1.getUserId());
				favoriteDAO.createAutoIncrement(f2);
				
				FavoriteBean f3 = new FavoriteBean();
				f3.setLink("http://www.whitehouse.gov");
				f3.setComment("My home");
				f3.setUserId(u1.getUserId());
				favoriteDAO.createAutoIncrement(f3);
				
				FavoriteBean f4 = new FavoriteBean();
				f4.setLink("http://www.whitehouse.gov");
				f4.setComment("My home");
				f4.setUserId(u1.getUserId());
				favoriteDAO.createAutoIncrement(f4);
				
				UserBean u2 = new UserBean();
				u2.setEmail("pat");
				u2.setFirstName("Zhipeng");
				u2.setLastName("Luo");
				u2.setPassword("lulu");
				userDAO.createAutoIncrement(u2);

				FavoriteBean f5 = new FavoriteBean();
				f5.setLink("http://www.baidu.com");
				f5.setComment("Baidu");
				f5.setUserId(u2.getUserId());
				favoriteDAO.createAutoIncrement(f5);
				
				FavoriteBean f6 = new FavoriteBean();
				f6.setLink("http://www.whitehouse.gov");
				f6.setComment("My home");
				f6.setUserId(u2.getUserId());
				favoriteDAO.createAutoIncrement(f6);
				
				FavoriteBean f7 = new FavoriteBean();
				f7.setLink("http://www.whitehouse.gov");
				f7.setComment("My home");
				f7.setUserId(u2.getUserId());
				favoriteDAO.createAutoIncrement(f7);
				
				FavoriteBean f8 = new FavoriteBean();
				f8.setLink("http://www.whitehouse.gov");
				f8.setComment("My home");
				f8.setUserId(u2.getUserId());
				favoriteDAO.createAutoIncrement(f8);
				
				UserBean u3 = new UserBean();
				u3.setEmail("lulu");
				u3.setFirstName("Wei");
				u3.setLastName("Liu");
				u3.setPassword("luoluo");
				userDAO.createAutoIncrement(u3);

				FavoriteBean f9 = new FavoriteBean();
				f9.setLink("http://www.pnc.com");
				f9.setComment("PNC Bank");
				f9.setUserId(u3.getUserId());
				favoriteDAO.createAutoIncrement(f9);
				
				FavoriteBean f10 = new FavoriteBean();
				f10.setLink("http://www.whitehouse.gov");
				f10.setComment("My home");
				f10.setUserId(u3.getUserId());
				favoriteDAO.createAutoIncrement(f10);
				
				FavoriteBean f11 = new FavoriteBean();
				f11.setLink("http://www.whitehouse.gov");
				f11.setComment("My home");
				f11.setUserId(u3.getUserId());
				favoriteDAO.createAutoIncrement(f11);
				
				FavoriteBean f12 = new FavoriteBean();
				f12.setLink("http://www.whitehouse.gov");
				f12.setComment("My home");
				f12.setUserId(u3.getUserId());
				favoriteDAO.createAutoIncrement(f12);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		UserBean user = (UserBean) session.getAttribute("user");
		String action = getActionName(servletPath);

		if (action.equals("register.do") || action.equals("login.do")) {
			// Allow these actions without logging in
			return Action.perform(action, request);
		}

		if (user == null) {
			// If the user hasn't logged in, so login is the only option
			return Action.perform("login.do", request);
		}

		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}
		
		if (nextPage.endsWith("gov")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		if (nextPage.startsWith("http://")) {
			response.sendRedirect(nextPage);
			return;
		}

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
