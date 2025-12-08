## 查看购物车
GET /api/v1/cart/items

## 添加商品
POST /api/v1/cart/items

请求：
```json
{
  "productId": 3,
  "quantity": 1
}
```

## 修改数量
PUT /api/v1/cart/items/{cartItemId}

请求：
```json
{
  "quantity": 5
}
```

## 删除购物车项
DELETE /api/v1/cart/items/{cartItemId}