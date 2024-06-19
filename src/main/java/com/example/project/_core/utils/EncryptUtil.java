package com.example.project._core.utils;

import org.mindrot.jbcrypt.BCrypt;
//모든 종류의 암호화를 위한 유틸리티
public class EncryptUtil {
    //사용자가 입력한 비밀번호를 암호화 하기 위한 매소드
    public static String hashPw(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    //암호화된 비밀번호와 평문 암호를 비교하기 위한 매소드
    public static boolean checkPw(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
