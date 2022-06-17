package tailup.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("0")
@Getter
@Setter
public class MenuComponent {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Set<MenuComponent> childrenMenus;

    private MenuComponent parentMenu;


    @Transient()
    public boolean isLeaf()
    {
        return (childrenMenus == null || childrenMenus.size() == 0);
    }

    @Transient()
    public boolean isRoot()
    {
        return (parentMenu == null);
    }

    @OneToMany(mappedBy =  "parentMenu", cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    public Set<MenuComponent> getChildrenMenus()
    {
        return childrenMenus;
    }

    @ManyToOne()
    @JoinColumn()
    public MenuComponent getParentMenu()
    {
        return parentMenu;
    }




}
