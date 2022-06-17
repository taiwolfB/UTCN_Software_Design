package tailup.entity;

import org.hibernate.annotations.GenericGenerator;
import tailup.enums.FoodType;

import javax.persistence.*;

//@Table (name = "food")
@Entity
@Inheritance()
@DiscriminatorValue("F")
public class Food extends MenuComponent{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private FoodType foodType;

    private String name;

    private double price;

    @Column(name =  "description")
    private String description;

}
