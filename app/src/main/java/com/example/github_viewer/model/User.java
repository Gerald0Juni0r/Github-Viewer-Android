public class User {
    private String login;
    private String avatarUrl;
    private String htmlUrl;

    public String getLogin() {
        return login;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
    public void setLogin(String login) {
        this.login = login;
    }

}