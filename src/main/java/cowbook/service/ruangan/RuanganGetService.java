package cowbook.service.ruangan;

import cowbook.domain.Ruangan;
import cowbook.web.rest.dto.RuanganDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Ruangan.
 */
public interface RuanganGetService {

    /**
     *  get all the ruangans.
     *  @return the list of entities
     */
    public Page<Ruangan> findAll(Pageable pageable);

    /**
     *  get the "id" ruangan.
     *  @return the entity
     */
    public RuanganDTO findOne(Long id);
}
