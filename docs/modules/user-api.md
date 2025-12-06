## 注册
POST /api/v1/users/register

请求参数
```json
{
  "username": "test",
  "password": "123456",
  "phone": "18800001111"
}
```
返回
```json
{
  "code": 0,
  "data": { "userId": 1 }
}
```

## 登录
POST /api/v1/users/login

请求参数
```json
{
  "username": "test",
  "password": "123456"
}
```

返回
```json
{
  "code": 0,
  "data": { "token": "xxxx.yyyy.zzzz" }
}
```

## 登出
POST /api/v1/users/logout

Header: `Authorization: Bearer <token>`