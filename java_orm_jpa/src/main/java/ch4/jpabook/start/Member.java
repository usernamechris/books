package ch4.jpabook.start;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="MEMBER",
        // 유니크제약조건 추가
        // DDL 자동생성할 때만 사용.
        uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"})})
/* db에서 시퀀스 지원할 시
@SequenceGenerator(name="BOARD_SEQ_GENERATOR", // 식별자 생성기 이름
                    sequenceName="BOARD_SEQ", // 매핑할 디비 시퀀스 이름
                    initialValue=1, // 기본값 1
                    allocationSize = 1 // 기본값 50
                    )
*/
@Access(AccessType.FIELD) // 필드에 직접접근. AccessType.PROPERTY는 getter사용. @Id있으면 FIELD로 자동세팅
public class Member {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY) //db에 값을 저장하고나서야 기본키 값을 구할 수 있을 경우 ex:mysql auto_increment
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR") // 시퀀스 사용시
    private Long id;
    
    @Column(name="NAME", nullable = false, length = 10)
    private String username;
    
    private Integer age;
    
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    
    @Lob
    private String description;

    @Transient // 매핑하지 않는다. db저장, 조회하지 않는다. 객체에 임시로 어떤값 보관시 사용
    private String firstName;

    @Transient
    private String lastName;
    
    @Access(AccessType.PROPERTY) // 기본은 필드접근. getFullName만 프로퍼티 접근. 테이블 FULLNAME컬럼에 first + last name이 저장
    public String getFullName() {
        return firstName + lastName;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
