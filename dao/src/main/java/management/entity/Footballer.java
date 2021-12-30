package management.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "command_id",nullable = false)
    Command command;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
