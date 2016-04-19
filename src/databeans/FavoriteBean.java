/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("favoriteId")
public class FavoriteBean {
	private int favoriteId;
	private int userId;
	private String link;
	private String comment;
	private int clickCount;
	
	public int getFavoriteId()      { return favoriteId; }
    public int getUserId()          { return userId; }
    public String getLink()          { return link; }
    public String getComment()      { return comment; }
    public int getClickCount()      { return clickCount; }

    public void setFavoriteId(int i)     { favoriteId = i; }
	public void setUserId(int i)         { userId = i; }
	public void setLink(String s)         { link = s; }
	public void setComment(String s)     { comment = s; }
	public void setClickCount(int i)  	 { clickCount = i; }
	
	public String toString()
	{
		return link + ";" + comment + ";" + clickCount + ";" + favoriteId;
	}
}
