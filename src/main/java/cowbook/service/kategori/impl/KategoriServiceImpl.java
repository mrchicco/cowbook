package cowbook.service.kategori.impl;

import cowbook.domain.Kategori;
import cowbook.repository.KategoriRepository;
import cowbook.service.kategori.KategoriService;
import cowbook.web.rest.dto.KategoriDTO;
import cowbook.web.rest.mapper.KategoriMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Kategori.
 */
@Service
@Transactional
public class KategoriServiceImpl implements KategoriService {

    private final Logger log = LoggerFactory.getLogger(KategoriServiceImpl.class);

    @Inject
    private KategoriRepository kategoriRepository;

    @Inject
    private KategoriMapper kategoriMapper;

    /**
     * Save a kategori.
     * @return the persisted entity
     */
    public KategoriDTO save(KategoriDTO kategoriDTO) {
        log.debug("Request to save Kategori : {}", kategoriDTO);
        Kategori kategori = kategoriMapper.kategoriDTOToKategori(kategoriDTO);
        kategori = kategoriRepository.save(kategori);
        KategoriDTO result = kategoriMapper.kategoriToKategoriDTO(kategori);
        return result;
    }

    /**
     *  delete the  kategori by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Kategori : {}", id);
        kategoriRepository.delete(id);
    }
}
