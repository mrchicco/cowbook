package cowbook.service.kategori;

import cowbook.domain.Kategori;
import cowbook.web.rest.dto.KategoriDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Kategori.
 */
public interface KategoriService {

    /**
     * Save a kategori.
     * @return the persisted entity
     */
    public KategoriDTO save(KategoriDTO kategoriDTO);

    /**
     *  delete the "id" kategori.
     */
    public void delete(Long id);
}
