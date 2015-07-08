
package kaizong.jee.web01.online;

import java.util.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {

    private String name;
    private UserList ul = UserList.getInstance();

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ul.addUser(name);
        Date date = new Date(System.currentTimeMillis());
        System.out.println("valueBound, " + name + ", " + date);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        ul.removeUser(name);
        Date date = new Date(System.currentTimeMillis());
        System.out.println("valueUnbound, " + name + ", " + date);

    }

}
