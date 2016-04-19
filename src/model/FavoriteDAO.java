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

import databeans.FavoriteBean;

public class FavoriteDAO extends GenericDAO<FavoriteBean> {
	
	public FavoriteDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FavoriteBean.class, tableName, cp);
	}
	
	public void create(FavoriteBean fav) throws RollbackException {
		try {
			Transaction.begin();
			createAutoIncrement(fav);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public FavoriteBean[] getUserFavorites(int userId) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		FavoriteBean[] favorites = match(MatchArg.equals("userId", userId));
		return favorites;

	}
	
	public String getFavoriteURL(int favId) throws RollbackException {
		FavoriteBean[] theOne = match(MatchArg.equals("favoriteId", favId));
		return theOne[0].getLink();
	}
	
	public void updateClickCount(int favId) throws RollbackException {
		try {
			Transaction.begin();
			FavoriteBean favWeb = read(favId);
			if (favWeb == null) 
				throw new IllegalStateException("Impossible condition for updateClickCount(int)");
			int temp = favWeb.getClickCount() + 1;
			favWeb.setClickCount(temp);
			
			this.update(favWeb);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void delete(int id, int userId) throws RollbackException {
		try {
			Transaction.begin();
    		FavoriteBean fav = read(id);

    		if (fav == null) {
				throw new RollbackException("The web link does not exist: id=" + id);
    		}

    		if (!(userId == fav.getUserId())) {
				throw new RollbackException("Photo not owned by " + userId );
    		}

			delete(id);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

}
