package book_wizards.models;

public class PublicUser {

  private int appUserId;
  private String username;

  public PublicUser(int appUserId, String username) {
    this.appUserId = appUserId;
    this.username = username;
  }

  public PublicUser(){}

  public int getAppUserId() {
    return appUserId;
  }

  public void setAppUserId(int appUserId) {
    this.appUserId = appUserId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
