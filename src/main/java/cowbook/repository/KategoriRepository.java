package cowbook.repository;

import cowbook.domain.Kategori;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Kategori entity.
 */
public interface KategoriRepository extends JpaRepository<Kategori,Long> {

}
