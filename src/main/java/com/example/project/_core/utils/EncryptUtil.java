package com.example.project._core.utils;

import org.mindrot.jbcrypt.BCrypt;
//모든 종류의 암호화를 위한 유틸리티
public class EncryptUtil {
    //사용자가 입력한 비밀번호를 암호화 하기 위한 유틸
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
}
