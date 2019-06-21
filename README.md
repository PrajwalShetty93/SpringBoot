# SpringBoot
User registration Web Application with Spring Boot and JPA.

# User Details Web Application 


## SCREENSHOTS

## 1) Login Page.
The password is first encoded and then compared with the encoded one saved against the user from the database. The BCryptPasswordEncoder does all the encoding and comparing.
![](https://github.com/PrajwalShetty93/SpringBoot/blob/master/images/Login.png)

## 2) Register User
The password is not stored directly into the database. It is encoded using a BCryptPasswordEncoder and the encoded password is stored in the database.
![](https://github.com/PrajwalShetty93/SpringBoot/blob/master/images/Register.png)

## 3) Tasks Page
![](https://github.com/PrajwalShetty93/SpringBoot/blob/master/images/ViewUser.png)

## 4) Users Page
The users with role as 'Admin' will be allowed to update and delete all users.
![](https://github.com/PrajwalShetty93/SpringBoot/blob/master/images/GetAllDetailsForAdmin.png)

The users with role 'User' can only view other users.
![](https://github.com/PrajwalShetty93/SpringBoot/blob/master/images/GetUserDetailsforUser.png)

## 5) Update User
![](https://github.com/PrajwalShetty93/SpringBoot/blob/master/images/UpdateUser.png)

## 6) Logout
![](https://github.com/PrajwalShetty93/SpringBoot/blob/master/images/Logout.png)



