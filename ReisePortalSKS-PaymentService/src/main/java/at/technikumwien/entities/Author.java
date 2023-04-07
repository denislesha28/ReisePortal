package at.technikumwien.entities;


import at.technikumwien.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_paymentservice_author")
@NamedEntityGraph(
        name = "Author.fetchData",
        attributeNodes = {
                @NamedAttributeNode("firstName"),
                @NamedAttributeNode("lastName"),
                @NamedAttributeNode("clicks"),
                @NamedAttributeNode("sex")
        }
)
public class Author {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Sex sex;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private int clicks = 0;

    public Author(Sex sex, String firstName, String lastName, int clicks) {
        this(null, sex, firstName, lastName,clicks);
    }

    public void addClick(){
        this.clicks++;
    }
}
