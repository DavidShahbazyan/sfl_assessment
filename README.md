# sfl_assessment
### API Endpoints and user roles

Path:    /api/v1/auth
Role(s): ANY

[Post] : /login

---
Path:    /api/v1/manager
Role(s): ROLE_MANAGER

[Get ] : /users
[Get ] : /users/{id}
[Post] : /users/create
[Get ] : /tables
[Get ] : /tables/waiter/{id}
[Get ] : /tables/{id}
[Post] : /tables/create
[Post] : /tables/assign
[Get ] : /products
[Get ] : /products/{id}
[Post] : /products/create

---
Path:    /api/v1/waiter
Role(s): ROLE_WAITER

[Get ] : /tables
[Get ] : /tables/{id}
[Post] : /tables/free/{id}
[Post] : /tables/reserve/{id}
[Post] : /tables/occupy/{id}
[Get ] : /orders
[Get ] : /orders/{id}
[Post] : /orders/create
[Post] : /orders/close/{id}
[Post] : /orders/cancel/{id}
[Post] : /orders/products/add
[Post] : /orders/products/remove

---
[ ROLE_USER ] - IT IS SIMPLY A REGISTERED USER WITH NO PERMISSIONS


