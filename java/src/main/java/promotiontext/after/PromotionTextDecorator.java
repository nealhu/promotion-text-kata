package promotiontext.after;

import java.util.ArrayList;
import java.util.List;
import promotiontext.model.Product;

/**
 * PromotionTextDecorator generates promotion text for a product.
 */
public class PromotionTextDecorator {
  private Product product;
  private List<String> promoTextLines;

  protected PromotionTextDecorator(Product product) {
    this.product = product;
    promoTextLines = new ArrayList<>();
  }

  /**
   * Generate and set promotion text for a product.
   *
   * @param product the product
   * @return the generated promotion text
   */
  public static String decorate(Product product) {
    return new PromotionTextDecorator(product).decorate();
  }

  private String decorate() {
    if (product.isPromoted()) {
      includePromotionText();
    } else {
      includeDescription();
    }
    updatePromotionText();
    return product.getPromotionText();
  }

  private void updatePromotionText() {
    product.setPromotionText(String.join("\n", promoTextLines));;
  }

  private void includeDescription() {
    promoTextLines.add(product.getDescription());
  }

  private void includePromotionText() {
    includeDiscountText();
    includeFreeShippingText();
    includeDescription();
    includeFreeShippingText();
    includeDiscountText();
  }

  private void includeDiscountText() {
    int discountDollar = getDiscountInDollar();
    if (product.isHideDiscount() || discountDollar <= 0) {
      return;
    }
    String discountText = getDiscountText(discountDollar);
    promoTextLines.add(discountText);
  }

  private String getDiscountText(int discountDollar) {
    return String.format("Don't miss the deal, $%d off!", discountDollar);
  }

  private int getDiscountInDollar() {
    return (product.getOldCentPrice() - product.getCentPrice()) / 100;
  }

  private void includeFreeShippingText() {
    if (!product.isFreeShipping()) {
      return;
    }
    String freeShippingText = getFreeShippingText();
    promoTextLines.add(freeShippingText);
  }

  private String getFreeShippingText() {
    return "Free shipping!";
  }
}
