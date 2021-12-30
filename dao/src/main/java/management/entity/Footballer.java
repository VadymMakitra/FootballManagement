package management.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "footballers")
@Entity
public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column
    public String firstName;
    @Column
    public String lastName;
    @Column
    public Integer age;
    @Column
    public Integer experience;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
