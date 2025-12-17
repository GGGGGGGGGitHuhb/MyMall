## 创建订单
POST /api/v1/orders

请求：
```json
{
  "addressId": 6
}
```
订单商品来自购物车

## 查看订单列表
GET /api/v1/orders

返回
```json
{
  "code": 0,
  "data":
  [
      {
        "id": 12,
        "totalPrice": 199.00,
        "status": 1,
        "createTime": "2025-10-01 16:55:00"
      }
  ]
}
```


## 查看订单详情
GET /api/v1/orders/{orderId}

## 取消订单
PUT /api/v1/orders/{orderId}/cancel