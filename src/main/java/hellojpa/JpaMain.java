package hellojpa;

import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        // 트랜잭션 얻어오기
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 회원 등록
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);

            // 회원 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = "+findMember.getId());
//            System.out.println("findMember.name = "+findMember.getName());

            // 회원 삭제
            //em.remove(findMember);

            // 회원 수정
            //findMember.setName("HelloJPA");

//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)  // 페이징 처리
//                    .setMaxResults(10) // 5번부터 10번까지
//                    .getResultList();
//            for(Member member : result){
//                System.out.println("member.name = "+member.getName());
//            }

            // (1) 1차 캐시에서 조회
//            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//            
//            // 영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");
//
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.id = "+findMember.getId());
//            System.out.println("findMember.name = "+findMember.getName());

            // (2) 데이터베이스에서 조회
            // 영속 엔티티의 동일성 보장
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println("result = "+(findMember1 == findMember2));

            // (3) 엔티티 등록 - 트랜잭션을 지원하는 쓰기 지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
            // 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
//            System.out.println("=====================");
            // 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.

            // (4) 엔티티 수정 - 변경 감지
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");

            // (5) 엔티티 삭제
            // 삭제 대상 엔티티 조회
//            Member member = em.find(Member.class, 150L);
//            em.remove(member);

            // 영속
//            Member member = em.find(Member.class, 100L);
//            member.setName("AAAAA");

            // em.detach(member); // 영속성 컨텍스트에서 더 이상 관리하지 못 하게 끄집어 냄
//            em.clear(); // 영속성 컨텍스트를 통째로 지워버림
//            Member member1 = em.find(Member.class, 100L); // clear()로 1차 캐시 내용을 지워버렸기 때문에 select문 실행해서 데이터 가져옴
//            System.out.println("===============");

//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.changeTeam(team); // setTeam()을 changeTeam()으로 변경함으로써 getter/setter 관례에 의한 것이 아니라 중요한 일을 하는 것을 강조
//            em.persist(member);

            // 양방향 매핑시 가장 많이 하는 실수 (연관관계의 주인에 값을 입력하지 않음)
//            Member member2 = new Member();
//            member2.setName("member2");
//
//            // 역방향(주인이 아닌 방향)만 연관관계 설정
//           team.getMembers().add(member2); // Member 테이블의 team_id에는 null 들어감
//            em.persist(member2);

//            em.flush();
//            em.clear();
//
//            Team findTeam = em.find(Team.class, team.getId());
//            List<Member> members = findTeam.getMembers();
//
//            for(Member m : members){
//                System.out.println("m = "+m.getUsername());
//            }

//            Member findMember = em.find(Member.class, member.getId());

            // 단방향 연관관계
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam.getName());
//
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam); // 팀 정보 수정


            // 양방향 연관관계의 주인
//            List<Member> members = findMember.getTeam().getMembers();
//
//            for(Member m : members){
//                System.out.println("m = "+m.getName());
//            }

            // JPQL
            List<Member> result = em.createQuery(
                    "select  m from Member as m where m.username like '%kim%'"
                    , Member.class
            ).getResultList();

            // Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            // 쿼리 생성
            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            em.createQuery(cq);

            tx.commit(); // DB에 반영하자. 이거 안 쓰면 Connection leak detected 에러남.
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
