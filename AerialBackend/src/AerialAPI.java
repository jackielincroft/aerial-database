import java.util.List;

public interface AerialAPI {

  void connect(String url, String user, String password);
  void close();

  void postMove(Move m);
  void postUser(User u);
  List<Move> getAllMoves();
  List<Move> getAllMovesOfApparatus(String apparatusName);
  List<Move> getAllMovesOfType(String moveType);
  List<Move> getAllMovesAddedByUser(String username);
  List<Apparatus> getAllApparatuses();
}
