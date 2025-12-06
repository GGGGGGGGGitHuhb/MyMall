## 新增地址
POST /api/v1/addresses
### 请求
```json
{
  "receiverName": "张三",
  "receiverPhone": "18800001111",
  "fullAddress": "北京市海淀区xxx号"
}
```
## 删除地址
DELETE /api/v1/addresses/{id}

## 修改地址
PUT /api/v1/addresses/{id}

## 查询全部地址
GET /api/v1/addresses