package com.linhuanjie.mapstruct;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author : linhuanjie
 * @email : lhuanjie@qq.com
 * @create : 2020.05.05 17:53
 **/
@Data
@Accessors(chain = true)
@ToString
public class UserDemoAddDTO {
    private String name;
    private String email;
    private String address;
    private String phone;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    @Override
//    public String toString() {
//        return "UserDemoAddDTO{" +
//                "name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", address='" + address + '\'' +
//                ", phone='" + phone + '\'' +
//                '}';
//    }
}
