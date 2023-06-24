package datastrs;

import java.util.ArrayList;

public class UserList{
    private ArrayList<String> users;
    
    public void append(String username) {
        users.add(username);
    }

    public ArrayList<String> getList() {
        return users;
    }
}
