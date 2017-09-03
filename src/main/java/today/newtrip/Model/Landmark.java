package today.newtrip.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romachka on 31.07.17.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id","authors"})
@ToString(of = {"name","date"})
@Entity
public class Landmark {
    @Column(name = "L_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "L_NAME")
    private String name;
    @Column(name = "L_DESCRIPTION")
    private String description;
    @Column(name = "L_AUTHORS")
    private String authors;
    @Column(name = "L_DATE")
    private String date;


    public List<String> splitAuthors(String authorsString){
        List<String> authorsList = new ArrayList<String>();
        if(authorsString!=null) {
            for (String a : authorsString.split(", ")) {
                authorsList.add(a);
            }
        }
        return authorsList;
    }

}
