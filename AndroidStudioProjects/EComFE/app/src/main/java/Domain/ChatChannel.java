package Domain;

public class ChatChannel {
    private String username;
    private String userid;

    public ChatChannel() {
    }

    public ChatChannel(String username, String userid) {
        this.username = username;
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
