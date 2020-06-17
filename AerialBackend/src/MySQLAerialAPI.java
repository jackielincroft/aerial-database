import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    String sql = "CALL add_move_details(" + m.getName() + ", " + m.getDifficultyLevel() + ", "
        + m.getApparatus() + ", " + m.getMoveType() + ", " + m.usesInversion() + ", "
        + m.isDynamic() + ", " + m.getBuildsOffMoveName() + ", " + m.getAddedByUsername() + ");";
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
  public List<Move> getAllMoves() {
    String sql = "SELECT m.move_id, m.name, m.difficulty_level, a.name, mt.name, m.uses_inversion, m.is_dynamic, m2.name, u.username "
        + "FROM move m JOIN apparatus a USING (apparatus_id) JOIN move_type mt USING (move_type_id) "
        + "LEFT JOIN user u ON (m.added_by = u.user_id) LEFT JOIN move m2 ON (m.builds_off = m2.move_id);";
    return getMoves(sql);
  }

  @Override
  public List<Move> getAllMovesOfApparatus(String apparatusName) {
    String sql = "SELECT m.move_id, m.name, m.difficulty_level, a.name, mt.name, m.uses_inversion, m.is_dynamic, m2.name, u.username "
        + "FROM move m JOIN apparatus a USING (apparatus_id) JOIN move_type mt USING (move_type_id) "
        + "LEFT JOIN user u ON (m.added_by = u.user_id) LEFT JOIN move m2 ON (m.builds_off = m2.move_id) "
        + "WHERE a.name = " + apparatusName + ";";
    return getMoves(sql);
  }

  @Override
  public List<Move> getAllMovesOfType(String moveType) {
    String sql = "SELECT m.move_id, m.name, m.difficulty_level, a.name, mt.name, m.uses_inversion, m.is_dynamic, m2.name, u.username "
      + "FROM move m JOIN apparatus a USING (apparatus_id) JOIN move_type mt USING (move_type_id) "
      + "LEFT JOIN user u ON (m.added_by = u.user_id) LEFT JOIN move m2 ON (m.builds_off = m2.move_id) "
      + "WHERE mt.name = " + moveType + ";";
    return getMoves(sql);
  }

  @Override
  public List<Move> getAllMovesAddedByUser(String username) {
    String sql = "SELECT m.move_id, m.name, m.difficulty_level, a.name, mt.name, m.uses_inversion, m.is_dynamic, m2.name, u.username "
        + "FROM move m JOIN apparatus a USING (apparatus_id) JOIN move_type mt USING (move_type_id) "
        + "LEFT JOIN user u ON (m.added_by = u.user_id) LEFT JOIN move m2 ON (m.builds_off = m2.move_id) "
        + "WHERE u.username = " + username + ";";
    return getMoves(sql);
  }

  @Override
  public List<Apparatus> getAllApparatuses() {
    String sql = "SELECT * FROM apparatus;";
    return getApparatuses(sql);
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
        int MID = rs.getInt("m.move_id");
        String name = rs.getString("m.name");
        String dl = rs.getString("m.difficulty_level");
        String a = rs.getString("a.name");
        String mt = rs.getString("mt.name");
        boolean ui = rs.getBoolean("uses_inversion");
        boolean id = rs.getBoolean("is_dynamic");
        String bo = rs.getString("m2.name");
        String ab = rs.getString("u.username");

        Move t = new Move(MID, name, DifficultyLevel.valueOf(dl), a, mt, ui, id, bo, ab);
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

  private List<Apparatus> getApparatuses(String sql) {
    List<Apparatus> apparatuses = new ArrayList<Apparatus>();
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int AID = rs.getInt("apparatus_id");
        String name = rs.getString("name");
        boolean spins = rs.getBoolean("spins");

        Apparatus t = new Apparatus(AID, name, spins);
        apparatuses.add(t);
      }
      rs.close();
      stmt.close();
      return apparatuses;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      return null;
    }
  }
}
