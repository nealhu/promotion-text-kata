package promotiontext.before;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import promotiontext.model.Product;


/**
 * Tests for PromotionTextDecorator before refactoring. You should not modify this class when
 * completing the kata
 */
public class PromotionTextDecoratorTest {
  @Test
  public void constructor() {
    // for 100% coverage
    new PromotionTextDecorator();
  }

  @Test
  public void decorate_notPromotedNoFreeShippingNoDiscount() {
    String description = "Pre-owned vase";
    Product product = new Product();
    product.setPromoted(false);
    product.setOldCentPrice(10000);
    product.setCentPrice(10000);
    product.setFreeShipping(false);
    product.setDescription(description);
    product.setPromotionText("Some old text");

    String promotionText = PromotionTextDecorator.decorate(product);

    assertEquals(promotionText, description,
        "Returned promotion text should be identical to its description");
    assertEquals(product.getPromotionText(), description,
        "product's promotion text should be identical to its description");
  }

  @Test
  public void decorate_notPromotedFreeShippingAndNoHideDiscount() {
    String description = "Pre-owned vase";
    Product product = new Product();
    product.setPromoted(false);
    product.setOldCentPrice(10000);
    product.setCentPrice(9000);
    product.setFreeShipping(true);
    product.setHideDiscount(false);
    product.setDescription(description);
    product.setPromotionText("Some old text");

    String promotionText = PromotionTextDecorator.decorate(product);

    assertEquals(promotionText, description,
        "Returned promotion text should be identical to its description");
    assertEquals(product.getPromotionText(), description,
        "product's promotion text should be identical to its description");
  }

  @Test
  public void decorate_promotedFreeShippingNoDiscount() {
    String description = "Pre-owned vase";
    Product product = new Product();
    product.setPromoted(true);
    product.setOldCentPrice(10000);
    product.setCentPrice(10000);
    product.setFreeShipping(true);
    product.setDescription(description);
    product.setPromotionText("Some old text");

    String promotionText = PromotionTextDecorator.decorate(product);

    String expected = String.format("Free shipping!\n%s\nFree shipping!", description);
    assertEquals(promotionText, expected,
        "Returned promotion text should have free shipping header and footer");
    assertEquals(product.getPromotionText(), expected,
        "product's promotion text should have free shipping header and footer");
  }

  @Test
  public void decorate_promotedNoFreeShippingNoHideDiscount() {
    String description = "Pre-owned vase";
    Product product = new Product();
    product.setPromoted(true);
    product.setOldCentPrice(10000);
    product.setCentPrice(9000);
    product.setFreeShipping(false);
    product.setDescription(description);
    product.setHideDiscount(false);
    product.setPromotionText("Some old text");

    String promotionText = PromotionTextDecorator.decorate(product);

    int offInDollar = (product.getOldCentPrice() - product.getCentPrice()) / 100;
    String expected =
        String.format("Don't miss the deal, $%d off!\n%s\nDon't miss the deal, $%d off!",
            offInDollar, description, offInDollar);
    assertEquals(promotionText, expected,
        "Returned promotion text should have discount header and footer");
    assertEquals(product.getPromotionText(), expected,
        "product's promotion text should have discount header and footer");
  }

  @Test
  public void decorate_promotedFreeShippingNoHideDiscount() {
    String description = "Pre-owned vase";
    Product product = new Product();
    product.setPromoted(true);
    product.setOldCentPrice(10000);
    product.setCentPrice(9000);
    product.setFreeShipping(true);
    product.setDescription(description);
    product.setHideDiscount(false);
    product.setPromotionText("Some old text");

    String promotionText = PromotionTextDecorator.decorate(product);

    int offInDollar = (product.getOldCentPrice() - product.getCentPrice()) / 100;
    String offText = String.format("Don't miss the deal, $%d off!", offInDollar);
    String freeShippingText = "Free shipping!";
    String expected =
        String.join("\n", offText, freeShippingText, description, freeShippingText, offText);
    assertEquals(promotionText, expected,
        "Returned promotion text should have both discount and free shipping headers and footers");
    assertEquals(product.getPromotionText(), expected,
        "product's promotion text should have both discount and free shipping headers and footers");
  }

  @Test
  public void decorate_promotedNoFreeShippingHideDiscount() {
    String description = "Pre-owned vase";
    Product product = new Product();
    product.setPromoted(true);
    product.setOldCentPrice(10000);
    product.setCentPrice(9000);
    product.setFreeShipping(false);
    product.setDescription(description);
    product.setHideDiscount(true);
    product.setPromotionText("Some old text");

    String promotionText = PromotionTextDecorator.decorate(product);

    assertEquals(promotionText, description,
        "Returned promotion text should be identical to its description");
    assertEquals(product.getPromotionText(), description,
        "product's promotion text should be identical to its description");
  }

  @Test
  public void decorate_promotedNoFreeShippingNoDiscount() {
    String description = "Pre-owned vase";
    Product product = new Product();
    product.setPromoted(true);
    product.setOldCentPrice(10000);
    product.setCentPrice(10000);
    product.setFreeShipping(false);
    product.setDescription(description);
    product.setPromotionText("Some old text");

    String promotionText = PromotionTextDecorator.decorate(product);

    assertEquals(promotionText, description,
        "Returned promotion text should be identical to its description");
    assertEquals(product.getPromotionText(), description,
        "product's promotion text should be identical to its description");
  }
}
