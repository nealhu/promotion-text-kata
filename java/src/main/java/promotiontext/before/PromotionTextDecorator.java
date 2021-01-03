package promotiontext.before;

import java.util.ArrayList;
import java.util.List;
import promotiontext.model.Product;

/**
 * PromotionTextDecorator generates promotion text for a product.
 */
public class PromotionTextDecorator {
  /**
   * Generate and set promotion text for a product.
   *
   * @param product the product
   * @return the generated promotion text
   */
  public static String decorate(Product product) {
    List<String> list = new ArrayList<>();
    if (product.isPromoted()) {
      if (!product.isHideDiscount()) {
        int discount = product.getOldCentPrice() - product.getCentPrice();
        if (discount > 0) {
          list.add(String.format("Don't miss the deal, $%d off!",
              (product.getOldCentPrice() - product.getCentPrice()) / 100));
        }
      }
      if (product.isFreeShipping()) {
        list.add("Free shipping!");
      }
    }

    list.add(product.getDescription());

    if (product.isPromoted()) {
      if (product.isFreeShipping()) {
        list.add("Free shipping!");
      }

      if (!product.isHideDiscount()) {
        int discount = product.getOldCentPrice() - product.getCentPrice();
        if (discount > 0) {
          list.add(String.format("Don't miss the deal, $%d off!",
              (product.getOldCentPrice() - product.getCentPrice()) / 100));
        }
      }
    }

    product.setPromotionText(String.join("\n", list));
    return product.getPromotionText();
  }

  protected PromotionTextDecorator() {
  }
}
