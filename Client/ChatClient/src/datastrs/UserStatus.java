package datastrs;

public class UserStatus {
    public boolean isOnline;
    public String lastSeen;
    public boolean isValid;

    public UserStatus () {
        isValid = true;
    }

    public void setOnlineStatus(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void setLastSeenStatus(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getStatusDisplayString() {
        if (isOnline) {
            return "Online";
        } else {
            return lastSeen;
        }
    }

    public void setToValid () {
        isValid = true;
    }

    public void setToInvalid () {
        isValid = false;
    }
}
