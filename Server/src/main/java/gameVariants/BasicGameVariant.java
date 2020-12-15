package gameVariants;

public class BasicGameVariant extends GameVariant {

  public BasicGameVariant() {
    this.blockAllowed = true;
  }


  @Override
  public boolean validMove() {

    return true;
  }
}
