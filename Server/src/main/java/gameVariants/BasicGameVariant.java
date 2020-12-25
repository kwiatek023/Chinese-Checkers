package gameVariants;

public class BasicGameVariant extends GameVariant {

  public BasicGameVariant() {
    this.blockAllowed = true;
  }


  @Override
  public boolean validMove() {
//    w board stworzyć trójkąty dla czerwonego -> trójkąt zielonego
// sprawdzić czy pionek jest w trójkącie
    return true;
  }
}
