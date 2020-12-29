package gameVariants;

public interface VariantFactory {
  /** GameVariant getter
   * @param gameVariant specifies which game variant should be returned
   * @return game variant with rules chosen by user.
   */
  GameVariant getGameVariant(String gameVariant);
}
