import java.util.List;

public class AerialTester {

  public static void main(String[] args) {
    AerialAPI api = new MySQLAerialAPI();
    api.connect("jdbc:mysql://localhost:3306/aerial?serverTimezone=EST5EDT",
        "aerial_user", "secret");

    System.out.println("All Moves:");
    List<Move> allMoves = api.getAllMoves();
    for (Move m : allMoves)
      System.out.println(m.toString());
  }

}
