package management.repository;

import management.entity.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    @Query(nativeQuery = true,
            value = "select * from commands c "
                    + "join footballers f on c.id = f.command_id "
                    + "where f.id = :id")
    Optional<Footballer> findCommandByFootballers(@Param("id") Long id);
}
