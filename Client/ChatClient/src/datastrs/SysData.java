package datastrs;

import utils.Message;
import java.util.concurrent.ConcurrentHashMap;

public class SysData {
    ConcurrentHashMap<String, UserStatus> userDict = new ConcurrentHashMap<String, UserStatus>();

    // Update sysData with new data.
    public void update(Message message) {
        
    }

    // Get a null UserStatus object.
    public static UserStatus getNullUser() {
        UserStatus res = new UserStatus();
        res.setLastSeenStatus(null);
        res.setOnlineStatus(false);
        res.setToInvalid();
        return res;
    }

    // Get the status info about a single user.
    public UserStatus getStatus(String username) {
        if (userDict.containsKey(username)) {
            return userDict.get(username);
        } else {
            return getNullUser();
        }
    }

    // Get a list of every user.
    public UserList getUserList() {
        UserList lst = new UserList();
        for (String key : userDict.keySet()) {
            lst.append(key);
        }
        return lst;
    }
}
