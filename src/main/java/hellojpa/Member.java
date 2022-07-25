package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // JPA가 관리하는 객체이고, 데이터베이스 테이블과 매핑해서 사용할 것이구나!
// @Table(name = "MBR") // 매필할 테이블 이름
// @SequenceGenerator(name = "member_seq_generator", sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 1)
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")

    private Long id;

    // @Column(unique = true, length = 10) // unique 제약조건
    @Column(name = "name")// 테이블 칼럼명
    private String username;
    private Long age;
    @Enumerated(EnumType.STRING) // enum 이름을 데이터베이스에 저장
    //@Enumerated(EnumType.ORDINAL) // enum 순서를 데이터베이스에 저장 (default ORDINAL)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob // varchar를 넘어서는 큰 컨텐츠
    private String description;

    public Member(){
    }

    public Member(Long id, String name) {
        this.id = id;
        this.username = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
