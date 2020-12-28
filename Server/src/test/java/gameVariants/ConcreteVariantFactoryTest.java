package gameVariants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteVariantFactoryTest {
  @Test
  public void testVariantFactory() {
    VariantFactory variantFactory = new ConcreteVariantFactory();
    assertNotNull(variantFactory.getGameVariant("BASIC"));
  }

  @Test
  public void shouldBeNull() {
    VariantFactory variantFactory = new ConcreteVariantFactory();
    assertNull(variantFactory.getGameVariant("blablabla"));
  }
}