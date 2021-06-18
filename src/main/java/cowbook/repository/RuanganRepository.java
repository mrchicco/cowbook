package cowbook.repository;

import cowbook.domain.Ruangan;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ruangan entity.
 */
public interface RuanganRepository extends JpaRepository<Ruangan,Long> {

}
