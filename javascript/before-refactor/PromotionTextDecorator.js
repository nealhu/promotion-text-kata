class PromotionTextDecorator {
  static decorate(product) {
    const list = [];
    if (product.promoted) {
      if (!product.hideDiscount) {
        const discount = product.oldCentPrice > product.centPrice;
        if (discount > 0) {
          list.push(`Don't miss the deal, $${(product.oldCentPrice - product.centPrice) / 100} off!`);
        }
      }
      if (product.freeShipping) {
        list.push('Free shipping!');
      }
    }
    list.push(product.description);
    if (product.promoted) {
      if (product.freeShipping) {
        list.push('Free shipping!');
      }
      if (!product.hideDiscount) {
        const discount = product.oldCentPrice > product.centPrice;
        if (discount > 0) {
          list.push(`Don't miss the deal, $${(product.oldCentPrice - product.centPrice) / 100} off!`);
        }
      }
    }
    product.promotionText = list.join('\n');
    return product.promotionText;
  }
}

module.exports = {
  PromotionTextDecorator,
};
