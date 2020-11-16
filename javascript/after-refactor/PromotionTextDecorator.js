class PromotionTextDecorator {
  constructor(product) {
    this.product = product;
    this.promoTextLines = [];
  }

  static decorate(product) {
    return new PromotionTextDecorator(product).decorate();
  }

  decorate() {
    if (this.product.promoted) {
      this.includePromotionText();
    } else {
      this.includeDescription();
    }
    this.updatePromotionText();
    return this.product.promotionText;
  }

  updatePromotionText() {
    this.product.promotionText = this.promoTextLines.join('\n');
  }

  includeDescription() {
    this.promoTextLines.push(this.product.description);
  }

  includePromotionText() {
    this.includeDiscountText();
    this.includeFreeShippingText();
    this.includeDescription();
    this.includeFreeShippingText();
    this.includeDiscountText();
  }

  includeDiscountText() {
    const discountDollar = this.getDiscountInDollar();
    if (this.product.hideDiscount || discountDollar <= 0) {
      return;
    }
    const discountText = this.getDiscountText(discountDollar);
    this.promoTextLines.push(discountText);
  }

  getDiscountText(discount) {
    return `Don't miss the deal, $${discount} off!`;
  }

  getDiscountInDollar() {
    return (this.product.oldCentPrice - this.product.centPrice) / 100;
  }

  includeFreeShippingText() {
    if (!this.product.freeShipping) {
      return;
    }
    const freeShippingText = this.getFreeShippingText();
    this.promoTextLines.push(freeShippingText);
  }

  getFreeShippingText() {
    return 'Free shipping!';
  }
}

module.exports = {
  PromotionTextDecorator,
};
