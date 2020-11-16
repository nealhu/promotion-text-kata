const { PromotionTextDecorator } = require('../../before-refactor/PromotionTextDecorator');

describe('#decorate before refactoring', () => {
  describe('Given a product that is not promoted', () => {
    describe('When the product has no free shipping nor discount', () => {
      it('Should generate a promotion description identical to its description', () => {
        const description = 'Pre-owned vase';
        const product = {
          promoted: false,
          oldCentPrice: 10000,
          centPrice: 10000,
          freeShipping: false,
          description,
          promotionText: 'Some old text',
        };

        const promotionText = PromotionTextDecorator.decorate(product);

        expect(promotionText).toEqual(description);
        expect(product.promotionText).toEqual(promotionText);
      });
    });
    describe('When the product has free shipping and discount', () => {
      it('Should still generate a promotion description identical to its description', () => {
        const description = 'Pre-owned vase';
        const product = {
          promoted: false,
          oldCentPrice: 10000,
          centPrice: 9000,
          freeShipping: true,
          description,
          promotionText: 'Some old text',
        };

        const promotionText = PromotionTextDecorator.decorate(product);

        expect(promotionText).toEqual(description);
        expect(product.promotionText).toEqual(promotionText);
      });
    });
  });
  describe('Given a product that is promoted', () => {
    describe('When the product has free shipping but no discount', () => {
      it('Should generate a promotion description with free shipping text', () => {
        const description = 'Pre-owned vase';
        const product = {
          promoted: true,
          hideDiscount: false,
          oldCentPrice: 10000,
          centPrice: 10000,
          freeShipping: true,
          description,
          promotionText: 'Some old text',
        };

        const promotionText = PromotionTextDecorator.decorate(product);

        const expected = `Free shipping!\n${description}\nFree shipping!`;
        expect(promotionText).toEqual(expected);
        expect(product.promotionText).toEqual(promotionText);
      });
    });
    describe('When the product is discounted, does not hide discount and has no free shipping', () => {
      it('Should generate a promotion description with discount text', () => {
        const description = 'Pre-owned vase';
        const product = {
          promoted: true,
          hideDiscount: false,
          oldCentPrice: 10000,
          centPrice: 9000,
          freeShipping: false,
          description,
          promotionText: 'Some old text',
        };

        const promotionText = PromotionTextDecorator.decorate(product);

        const offInDollar = (product.oldCentPrice - product.centPrice) / 100;
        const expected = `Don't miss the deal, $${offInDollar} off!\n${description}\nDon't miss the deal, $${offInDollar} off!`;
        expect(promotionText).toEqual(expected);
        expect(product.promotionText).toEqual(promotionText);
      });
    });
    describe('When the product is discounted and has no free shipping but hides discount', () => {
      it('Should generate a promotion description with discount text', () => {
        const description = 'Pre-owned vase';
        const product = {
          promoted: true,
          hideDiscount: true,
          oldCentPrice: 10000,
          centPrice: 9000,
          freeShipping: false,
          description,
          promotionText: 'Some old text',
        };

        const promotionText = PromotionTextDecorator.decorate(product);

        expect(promotionText).toEqual(description);
        expect(product.promotionText).toEqual(promotionText);
      });
    });
    describe('When the product has free shipping and discount', () => {
      it('Should generate a promotion description with both free shipping and discount texts', () => {
        const description = 'Pre-owned vase';
        const product = {
          promoted: true,
          hideDiscount: false,
          oldCentPrice: 10000,
          centPrice: 9000,
          freeShipping: true,
          description,
          promotionText: 'Some old text',
        };

        const promotionText = PromotionTextDecorator.decorate(product);

        const offInDollar = (product.oldCentPrice - product.centPrice) / 100;
        const expected = `Don't miss the deal, $${offInDollar} off!\nFree shipping!\n${description}\nFree shipping!\nDon't miss the deal, $${offInDollar} off!`;
        expect(promotionText).toEqual(expected);
        expect(product.promotionText).toEqual(promotionText);
      });
    });
    describe('When the product has no free shipping and no discount', () => {
      it('Should generate a promotion description identical to its description', () => {
        const description = 'Pre-owned vase';
        const product = {
          promoted: true,
          hideDiscount: false,
          oldCentPrice: 10000,
          centPrice: 10000,
          freeShipping: false,
          description,
          promotionText: 'Some old text',
        };

        const promotionText = PromotionTextDecorator.decorate(product);

        expect(promotionText).toEqual(description);
        expect(product.promotionText).toEqual(promotionText);
      });
    });
  });
});
