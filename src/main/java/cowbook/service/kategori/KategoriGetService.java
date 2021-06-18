package cowbook.service.kategori;

import cowbook.domain.Kategori;
import cowbook.web.rest.dto.KategoriDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Kategori.
 */
public interface KategoriGetService {

    /**
     *  get all the kategoris.
     *  @return the list of entities
     */
    public Page<Kategori> findAll(Pageable pageable);

    /**
     *  get the "id" kategori.
     *  @return the entity
     */
    public KategoriDTO findOne(Long id);

}
