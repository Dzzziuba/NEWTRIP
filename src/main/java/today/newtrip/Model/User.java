package today.newtrip.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by romachka on 31.07.17.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id","password"})
@ToString(of = {"login","role"})
@Entity
public class User {
    @Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "USER_LOGIN")
    private String login;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_ROLE")
    private String role;
}