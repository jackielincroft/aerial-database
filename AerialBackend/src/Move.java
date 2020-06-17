public class Move {

  private int moveID;
  private String name;
  private DifficultyLevel difficultyLevel;
  // private int apparatusID;
  private String apparatus;
  // private int moveTypeID;
  private String moveType;
  private boolean usesInversion;
  private boolean isDynamic;
  // private int buildsOff;
  private String buildsOffMoveName;
  // private int addedBy;
  private String addedByUsername;

  public Move(int moveID, String name, DifficultyLevel difficultyLevel, String apparatus,
      String moveType, boolean usesInversion, boolean isDynamic, String buildsOffMoveName,
      String addedByUsername) {
    this.moveID = moveID;
    this.name = name;
    this.difficultyLevel = difficultyLevel;
    // this.apparatusID = apparatusID;
    this.apparatus = apparatus;
    // this.moveTypeID = moveTypeID;
    this.moveType = moveType;
    this.usesInversion = usesInversion;
    this.isDynamic = isDynamic;
    // this.buildsOff = buildsOff;
    this.buildsOffMoveName = buildsOffMoveName;
    // this.addedBy = addedBy;
    this.addedByUsername = addedByUsername;
  }

  public int getMoveID() {
    return moveID;
  }

  public String getName() {
    return name;
  }

  public DifficultyLevel getDifficultyLevel() {
    return difficultyLevel;
  }
  public boolean usesInversion() {
    return usesInversion;
  }

  public boolean isDynamic() {
    return isDynamic;
  }

  public String getApparatus() {
    return apparatus;
  }

  public String getMoveType() {
    return moveType;
  }
  public String getBuildsOffMoveName() {
    return buildsOffMoveName;
  }

  public String getAddedByUsername() {
    return addedByUsername;
  }

  @Override
  public String toString() {
    return "move_id: " + moveID + "\n"
        + "name: " + name + "\n"
        + "difficulty_level: " + difficultyLevel + "\n"
        + "apparatus: " + apparatus + "\n"
        + "move_type: " + moveType + "\n"
        + "uses_inversion: " + usesInversion + "\n"
        + "is_dynamic: " + isDynamic + "\n"
        + "builds_off: " + buildsOffMoveName + "\n"
        + "added_by: " + addedByUsername + "\n";
  }
}
