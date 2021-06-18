package cowbook.service.ruangan;

import cowbook.domain.Ruangan;
import cowbook.web.rest.dto.RuanganDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Ruangan.
 */
public interface RuanganService {

    /**
     * Save a ruangan.
     * @return the persisted entity
     */
    public RuanganDTO save(RuanganDTO ruanganDTO);


    /**
     *  delete the "id" ruangan.
     */
    public void delete(Long id);
}
