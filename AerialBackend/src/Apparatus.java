public class Apparatus {

  private int apparatusID;
  private String name;
  private boolean spins;

  public Apparatus(int apparatusID, String name, boolean spins) {
    this.apparatusID = apparatusID;
    this.name = name;
    this.spins = spins;
  }

  public int getApparatusID() {
    return apparatusID;
  }

  public String getName() {
    return name;
  }

  public boolean isSpins() {
    return spins;
  }

  @Override
  public String toString() {
    return "apparatus_id: " + apparatusID + "\n"
        + "name: " + name + "\n"
        + "spins: " + spins + "\n";
  }
}
