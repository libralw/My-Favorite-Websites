/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
	
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(UserBean.class, tableName, cp);
	}
	
	public UserBean read(String email) throws RollbackException {
		UserBean[] users =  match(MatchArg.equals("email", email));
		if (users.length == 0) 
			return null;
		return users[0];
	}
	
	public UserBean[] getUsers() throws RollbackException {
		UserBean[] users = match();
//		Arrays.sort(users);  // We want them sorted by last and first names (as per User.compareTo());
		return users;
	}
	
	public void create(UserBean user) throws RollbackException {
		try {
			Transaction.begin();
			createAutoIncrement(user);
			Transaction.commit();
		} finally {
				if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void setPassword(int userId, String password) throws RollbackException {
        try {
        	Transaction.begin();
			UserBean user = read(userId);
			
			if (user == null) {
				throw new RollbackException("User "+ userId + " no longer exists");
			}
			
			user.setPassword(password);
			
			update(user);
			Transaction.commit();
			
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
}
