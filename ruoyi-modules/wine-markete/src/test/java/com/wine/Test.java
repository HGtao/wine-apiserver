package com.wine;

import cn.dev33.satoken.secure.BCrypt;

/**
 * 调用加密算法生成密码
 *
 * @author Peng Li
 * @data 2024/1/19
 */
public class Test {
    public static void main(String[] args) {
        String a = "123456";
        System.out.println(BCrypt.hashpw(a));
    }

}
