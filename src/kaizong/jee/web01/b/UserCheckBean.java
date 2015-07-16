
package kaizong.jee.web01.b;

public class UserCheckBean {

    private UserBean user;

    public UserCheckBean() {
        // TODO Auto-generated constructor stub
    }

    public UserCheckBean(UserBean user) {
        this.user = user;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
    
    public boolean validate(){
        String name = user.getName();
        String password = user.getPassword();
        
        if("zhangsan".equals(name) && "1234".equals(password)){
            return true;
        }else{
            return false;
        }
    }

}
