package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.enums.UserProviderEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    // 활동 회원 리스트 조회 (관리자)
    @Query("select u from User u where u.status = 'NORMAL' AND u.role = :role ORDER BY u.nickname ASC")
    List<User> findByRole(@Param("role") UserEnum role);

    @Query("select u from User u where u.email = :email and u.provider is null")
    User findByEmail(String email);

    @Query("select u from User u where u.providerId = :providerId and u.provider =:provider")
    User findByIdAndProvider(UserProviderEnum provider, String providerId);

    // 활동 회원 전체 갯수 조회 (관리자)
    @Query("select count(*) from User u where u.status = 'NORMAL' AND u.role = :role")
    Integer findAllNormalUser(@Param("role") UserEnum role);
}
/*
로그인시 입력받은 이메일과 비밀번호로 쿼리로 날려 로그인 검증하는 방식을 사용하지 말아야 하는 이유 (출처:ChatGPT 4, 2024.06.19)
데이터베이스에서 직접 이메일과 해시된 비밀번호를 매개변수로 사용하여 사용자를 조회하는 방식에는 몇 가지 잠재적 위험과 보안 문제가 있습니다:

	1.	비밀번호 해시 노출: 일반적으로 비밀번호의 해시를 데이터베이스에서 조회하는 것은 피해야 합니다. 이는 해시된 비밀번호가 노출되거나, 로그에 기록될 수 있는 상황을 만들 수 있기 때문입니다. 이는 비밀번호 해시를 악의적인 목적으로 사용할 수 있는 기회를 제공할 수 있습니다.
	2.	타이밍 공격 취약성: 데이터베이스 쿼리로 직접 비밀번호를 검증할 경우, 타이밍 공격에 취약할 수 있습니다. 이메일과 해시된 비밀번호가 쿼리에서 사용될 때, 응답 시간 차이를 통해 유효한 이메일 주소나 해시 조합을 유추할 수 있는 정보를 제공할 수 있습니다.
	3.	로그 기록: 쿼리가 로깅되는 시스템에서는 사용자의 이메일과 해시된 비밀번호가 로그에 기록될 위험이 있습니다. 이는 민감한 사용자 정보의 불필요한 노출을 의미하며, 이 정보가 악의적인 목적으로 사용될 수 있습니다.
	4.	SQL 인젝션 공격: 비록 JPQL과 같은 쿼리 언어는 SQL 인젝션 공격에 상대적으로 안전할 수 있지만, 모든 동적 쿼리는 잠재적으로 SQL 인젝션 공격에 취약할 수 있습니다. 사용자 입력을 쿼리에 포함할 때는 항상 주의가 필요합니다.
	5.	비밀번호 해시의 재사용성 문제: 사용자가 동일한 비밀번호를 사용하여 다른 계정에서 로그인 시도할 경우, 해시된 비밀번호가 같게 되므로 이를 재사용하는 공격에 취약할 수 있습니다.

안전한 접근 방법

	•	애플리케이션 레벨에서 비밀번호 검증: 사용자 인증은 데이터베이스에서 직접 비밀번호를 검증하는 것이 아니라, 데이터베이스에서 사용자 정보를 가져온 후, 애플리케이션 레벨에서 비밀번호 해시를 검증하는 것이 안전합니다.
	•	해시 함수의 사용: 비밀번호는 반드시 해시 함수를 사용하여 데이터베이스에 저장되어야 하며, 이 과정에서 솔트를 사용하는 것이 중요합니다. 이를 통해 같은 비밀번호라도 다른 해시 값을 생성할 수 있습니다.
	•	최소 권한의 원칙: 데이터베이스 쿼리는 최소한의 정보만을 요청하고 반환해야 하며, 이를 통해 정보 노출을 최소화할 수 있습니다.

안전한 접근 방법 (자세히)
	1.	사용자 검색: 먼저 사용자의 이메일이나 ID를 기반으로 데이터베이스에서 해당 사용자의 레코드를 검색합니다. 이 검색은 사용자의 비밀번호를 제외하고 이메일 또는 ID만 사용하여 수행됩니다.
	2.	비밀번호 검증: 데이터베이스에서 사용자의 레코드를 성공적으로 가져온 후, 저장된 해시된 비밀번호와 사용자가 로그인 시 입력한 비밀번호의 해시를 비교합니다. 이 비교는 애플리케이션 서버에서 수행됩니다. 사용자가 입력한 비밀번호는 절대 평문 형태로 저장되거나 전송되지 않으며, 서버에 도착하자마자 즉시 해시 처리됩니다.
	3.	인증 처리: 해시된 비밀번호가 일치하면 사용자 인증이 성공적으로 처리됩니다. 만약 비밀번호가 일치하지 않는다면, 인증 실패로 처리합니다.

이런 방식으로 사용자 인증을 처리하면, 시스템의 보안을 강화하고 민감한 정보의 노출 위험을 줄일 수 있습니다.
 */