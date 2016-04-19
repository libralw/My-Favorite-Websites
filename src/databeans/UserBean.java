/*
 * Wei Liu (weiliu1)
 * Nov.24, 2014
 * 08-600
 */

package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("userId")
public class UserBean {
    private int userId;
    private String email;
    private String firstName;
	private String lastName;
    private String password;

    public int getUserId()        		{ return userId; }
    public String getEmail()     		{ return email; }
    public String getFirstName()        { return firstName; }
    public String getLastName()         { return lastName; }
    public String getPassword()         { return password; }

    public void setUserId(int i)              { userId = i; }
    public void setEmail(String s)     		  { email = s; }
    public void setFirstName(String s)        { firstName = s; }
    public void setLastName(String s)         { lastName = s; }
    public void setPassword(String s)         { password = s; }
    
}
