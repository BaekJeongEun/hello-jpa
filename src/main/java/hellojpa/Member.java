package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // JPA가 관리하는 객체이고, 데이터베이스 테이블과 매핑해서 사용할 것이구나!
// @Table(name = "MBR") // 매필할 테이블 이름
// @SequenceGenerator(name = "member_seq_generator", sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 1)
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member extends BaseEntity {

    @Id @GeneratedValue // (strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "MEMBER_ID")
    private Long id;

    // @Column(unique = true, length = 10) // unique 제약조건
    @Column(name = "USERNAME")// 테이블 칼럼명
    private String username;

    private int age;

    @ManyToOne // 다대일 Member: N , Team: 1
    @JoinColumn(name = "TEAM_ID") // join할 컬럼명
    private Team team;

    public Team getTeam() {
        return team;
    }

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

//    public void setTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }
    public void changeTeam(Team team) {
       this.team = team;
       team.getMembers().add(this);
    }

    //    @Enumerated(EnumType.STRING) // enum 이름을 데이터베이스에 저장
//    //@Enumerated(EnumType.ORDINAL) // enum 순서를 데이터베이스에 저장 (default ORDINAL)
//    private RoleType roleType;
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;
//
//    @Lob // varchar를 넘어서는 큰 컨텐츠
//    private String description;
//
//    public Member(){
//    }
//
//    public Member(Long id, String name) {
//        this.id = id;
//        this.username = name;
//    }
//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
//
//    public RoleType getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(RoleType roleType) {
//        this.roleType = roleType;
//    }
}
