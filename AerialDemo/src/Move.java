public class Move {

  private int moveID;
  private String name;
  private DifficultyLevel difficultyLevel;
  private int apparatusID;
  private int moveTypeID;
  private boolean usesInversion;
  private boolean isDynamic;
  private int buildsOff;
  private int addedBy;

  public Move(int moveID, String name, DifficultyLevel difficultyLevel, int apparatusID,
      int moveTypeID, boolean usesInversion, boolean isDynamic, int buildsOff,
      int addedBy) {
    this.moveID = moveID;
    this.name = name;
    this.difficultyLevel = difficultyLevel;
    this.apparatusID = apparatusID;
    this.moveTypeID = moveTypeID;
    this.usesInversion = usesInversion;
    this.isDynamic = isDynamic;
    this.buildsOff = buildsOff;
    this.addedBy = addedBy;
  }

  public int getMoveID() {
    return moveID;
  }

  public String getName() { return name; }

  public DifficultyLevel getDifficultyLevel() {
    return difficultyLevel;
  }

  public int getApparatusID() {
    return apparatusID;
  }

  public int getMoveTypeID() {
    return moveTypeID;
  }

  public boolean usesInversion() {
    return usesInversion;
  }

  public boolean isDynamic() {
    return isDynamic;
  }

  public int getBuildsOff() {
    return buildsOff;
  }

  public int getAddedBy() {
    return addedBy;
  }
}
