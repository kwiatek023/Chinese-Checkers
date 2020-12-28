package gameVariants;

/**
 * Factory of game variants. See also {@link VariantFactory}.
 */
public class ConcreteVariantFactory implements VariantFactory {
  @Override
  public GameVariant getGameVariant(String gameVariant) {
    return switch (gameVariant) {
      case "BASIC" -> new BasicGameVariant();
      default -> null;
    };
  }
}
