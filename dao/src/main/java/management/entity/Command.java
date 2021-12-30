package management.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "commands")
@Entity
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String name;

    @Column
    public Integer commission;

    @Column
    public Long budget;

    @OneToMany(mappedBy = "command",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public List<Footballer> footballers;
}