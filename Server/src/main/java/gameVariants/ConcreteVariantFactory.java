package gameVariants;

public class ConcreteVariantFactory implements VariantFactory {
  @Override
  public GameVariant getGameVariant(String gameVariant) {
    return switch (gameVariant) {
      case "BASIC" -> new BasicGameVariant();
      default -> null;
    };
  }
}
