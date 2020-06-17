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

  private List<Move> getMoves(String sql) {

    List<Move> moves = new ArrayList<Move>();
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int MID = rs.getInt("move_id");
        String name = rs.getString("name");
        String dl = rs.getString("difficulty_level");
        int AID = rs.getInt("apparatus_id");
        int MTID = rs.getInt("move_type_id");
        boolean ui = rs.getBoolean("uses_inversion");
        boolean id = rs.getBoolean("is_dynamic");
        int bo = rs.getInt("builds_off");
        int ab = rs.getInt("added_by");

        Move t = new Move(MID, name, DifficultyLevel.valueOf(dl), AID, MTID, ui, id, bo, ab);
        moves.add(t);
      }
      rs.close();
      stmt.close();
      return moves;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      return null;
    }
  }
}
