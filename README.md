# Promotion Text Kata

## Why this Kata?
The Promotion Text [Kata](https://en.wikipedia.org/wiki/Kata_(programming)) offers an hour-long hands-on practice on readable and maintainable programming. When practicing alone, it improves a developer's craft. When practicing as a team, it educates and aligns the team on a standard set of best practices and coding styles.

Users' goal is to improve the readability of the `PromotionTextDecorator` class without breaking its functionalities.

This Kata does not aim to improve OOP and design pattern skills.

While heavily rewritten, it's inspired by an example (`HtmlUtil.java`) in Chapter 3 of the book "Clean Code: A Handbook of Agile Software Craftsmanship" by Robert C. Martin.

## How to use this Kata
- Read the requirements (the next section) and implementation of the requirements in `before-refactor/`
- Rewrite the implementation in a more readable and maintainable way
- Your rewrite should pass all the unit tests provided in `tests/`
- While unit tests are provided, during the refactor, it's recommended to write your unit tests
- Please don't modify the provided unit tests
- Please don't modify the signature of `PromotionTextDecorator.decorate(product)`, i.e. # of arguments, their types as well as the return type
- Since this Kata doesn't aim to improve OOP and design pattern skills, there is no need to turn `PromotionTextDecorator` into smaller classes

## PromotionTextDecorator Requirements
Given a product, `PromotionTextDecorator.decorate` should generate a promotion description:
- Every product has these properties:
  - `promoted`: boolean, whether the product is being promoted
  - `oldCentPrice`: integer, the original price of the product **in cents**
  - `centPrice`: integer, the current price of the product **in cents**
  - `hideDiscount`: boolean, whether to hide discount text in description
  - `freeShipping`: boolean, whether the product is eligible for free shipping
  - `description`: string, original description of the product
  - `promotionText`: string, promotion description generated
- Set `promotionText` to `description` if the product is not being promoted
- Set `promotionText` to `description` with some additional texts. The rules of additional texts are:
  - If the product is discounted (`centPrice` < `oldCentPrice`) and its `hideDiscount` is false, add the text "Don't miss the deal, $X off!" to the beginning and the end of `promotionText`, where X is `(oldCentPrice - centPrice)/100`
  - If the product is eligible for free shipping, add the text "Free shipping!" to the beginning and the end of `promotionText`, after and before discount texts if those exist
  - Use `\n` as newline character
- Return the generated `promotionText`

Examples:
```javascript
const product = {
  promoted: true,
  hideDiscount: false,
  oldCentPrice: 10000,
  centPrice: 9000,
  freeShipping: true,
  description: 'Pre-owned vase',
  promotionText: 'Some old text',
}
```
becomes
```javascript
const product = {
  promoted: true,
  hideDiscount: false,
  oldCentPrice: 10000,
  centPrice: 9000,
  freeShipping: true,
  description: 'Pre-owned vase',
  promotionText: 'Don't miss the deal, $X off!\nFree shipping!\nPre-owned vase\nFree shipping!\nDon't miss the deal, $X off!',
}
```
Please see the unit tests for more details.

## Solution
There is a solution provided in `after-refactor/`. It is one of the many ways to improve the code. It follows the practices mentioned in the Clean Code book, to name a few:
- Keep functions small
- Reduce nested `if` and `while` statements
- One function should do one thing: statements within a function should all be at the same level of abstraction
- Use descriptive names
- Fewer function arguments
- DRY (Don't repeat yourself)
- Concepts that are closely related should be kept vertically close to each other
- Explain yourself in readable code instead of comments
