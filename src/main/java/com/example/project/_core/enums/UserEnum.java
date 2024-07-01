package com.example.project._core.enums;

public enum UserEnum {

    // 상태 (MANAGER : 관리자, USER : 일반 유저)
    MANAGER, USER;

    //UserEnum.fromString("USER") 과 같이 호출하면 UserName.USER를 반환한다.
    //HTML 레벨에서 구분할 때 사용한다. ex) /join 시에 USER,MANAGER를 구분하여 가입받음
    public static UserEnum fromString(String role) {
        for (UserEnum userRole : UserEnum.values()) {
            if (userRole.name().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Unknown user role: " + role);
    }
}
