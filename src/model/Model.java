/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	
	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;

	public Model(ServletConfig config) throws ServletException {
		
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
//			String jdbcDriverName = "com.mysql.jdbc.Driver";
//			String jdbcURL = "jdbc:mysql://localhost:3306/test";
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			
			userDAO  = new UserDAO(pool, "weiliu1_user");
			favoriteDAO = new FavoriteDAO(pool, "weiliu1_favorite");
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public FavoriteDAO getFavoriteDAO() { return favoriteDAO; }
	public UserDAO  getUserDAO()  { return userDAO;  }
	
}
