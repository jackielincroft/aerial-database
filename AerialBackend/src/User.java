import java.sql.Date;

public class User {

  private int userID;
  private String email;
  private String password;
  private String username;
  private Date dob;
  private DifficultyLevel level;

  public User(int userID, String email, String password, String username, Date dob,
      DifficultyLevel level) {
    this.userID = userID;
    this.email = email;
    this.password = password;
    this.username = username;
    this.dob = dob;
    this.level = level;
  }

  public int getUserID() {
    return userID;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public Date getDob() {
    return dob;
  }

  public DifficultyLevel getLevel() {
    return level;
  }
}
