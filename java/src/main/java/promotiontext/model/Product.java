package promotiontext.model;

/**
 * Product stores properties of a product. You should not modify this class when completing the
 * kata.
 */
public class Product {
  private boolean promoted;
  private int oldCentPrice;
  private int centPrice;
  private boolean freeShipping;
  private String description;
  private String promotionText;
  private boolean hideDiscount;

  public boolean isPromoted() {
    return promoted;
  }

  public void setPromoted(boolean promoted) {
    this.promoted = promoted;
  }

  public int getOldCentPrice() {
    return oldCentPrice;
  }

  public void setOldCentPrice(int oldCentPrice) {
    this.oldCentPrice = oldCentPrice;
  }

  public int getCentPrice() {
    return centPrice;
  }

  public void setCentPrice(int centPrice) {
    this.centPrice = centPrice;
  }

  public boolean isFreeShipping() {
    return freeShipping;
  }

  public void setFreeShipping(boolean freeShipping) {
    this.freeShipping = freeShipping;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPromotionText() {
    return promotionText;
  }

  public void setPromotionText(String promotionText) {
    this.promotionText = promotionText;
  }

  public boolean isHideDiscount() {
    return hideDiscount;
  }

  public void setHideDiscount(boolean hideDiscount) {
    this.hideDiscount = hideDiscount;
  }
}
