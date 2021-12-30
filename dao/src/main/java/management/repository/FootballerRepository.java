package management.repository;

import management.entity.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballerRepository extends JpaRepository<Footballer, Long> {
}
