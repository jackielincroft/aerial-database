import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLAerialAPI implements AerialAPI {

  private Connection con;

  @Override
  public void connect(String url, String user, String pw) {
    if (con == null) {
      try {
        con = DriverManager.getConnection(url, user, pw);
        System.out.println("Connection successful");
      } catch (SQLException sqe) {
        System.err.println(sqe.getMessage());
        System.exit(1);
      }
    }
  }

  @Override
  public void close() {
    try {
      con.close();
    } catch (SQLException sqe) {
      System.err.println(sqe.getMessage());
      sqe.printStackTrace();
      System.exit(1);
    }
  }

  @Override
  public void postMove(Move m) {
    String sql = "INSERT INTO move "
        + "(name, difficulty_level, move_type_id, apparatus_id, "
        + "uses_inversion, is_dynamic, builds_off, added_by) "
        + "VALUES (" + m.getName() + ", " + m.getDifficultyLevel() + ", "
        + m.getMoveTypeID() + ", " + m.getApparatusID() + ", " + m.usesInversion() + ", "
        + m.isDynamic() + ", " + m.getBuildsOff() + ", "
        + m.getAddedBy() + ");";
    insertOneRecord(sql);
  }

  @Override
  public void postUser(User u) {
    String sql = "INSERT INTO user "
        + "(user_id, email, password, username, dob, level) "
        + "VALUES (" + u.getUserID() + ", " + u.getEmail() + ", " + u.getPassword() + ", "
        + u.getUsername() + ", " + u.getDob() + ", " + u.getLevel() + ");";
    insertOneRecord(sql);
  }

  @Override
  public List<Move> getAllMovesOfApparatus(String apparatusName) {
    return null;
  }

  @Override
  public List<Move> getAllMovesOfType(String moveType) {
    return null;
  }

  @Override
  public List<Move> getAllMovesAddedByUser(int userID) {
    return null;
  }

  private void insertOneRecord(String sql) {
    System.out.println(sql);
    try {
      Statement stnt = con.createStatement();
      stnt.executeUpdate(sql);
      stnt.close();
    } catch (SQLException sqe) {
      System.err.println(sqe.getMessage());
      sqe.printStackTrace();
    }
  }

  private List<Move> fetchMoves(String sql) {
    List<Move> tweets = new ArrayList<>();
    try {
      Statement stnt = con.createStatement();
      ResultSet rs = stnt.executeQuery(sql);
      while (rs.next()) {
        int TID = rs.getInt("tweet_id");
        int UID = rs.getInt("user_id");
        Date ts = rs.getTimestamp("timestamp");

      }
    } catch (SQLException sqe) {
      System.err.println(sqe.getMessage());
    }

    return tweets;
  }
}
