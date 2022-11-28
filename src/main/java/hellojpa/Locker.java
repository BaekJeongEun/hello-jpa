package hellojpa;

import javax.persistence.*;
import javax.swing.plaf.metal.MetalMenuBarUI;

@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String name;

    // 1:1 단방향
    @OneToOne(mappedBy = "locker")
    private Member member;
}
