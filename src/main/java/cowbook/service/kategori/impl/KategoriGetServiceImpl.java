package cowbook.service.kategori.impl;

import cowbook.domain.Kategori;
import cowbook.repository.KategoriRepository;
import cowbook.service.kategori.KategoriGetService;
import cowbook.web.rest.dto.KategoriDTO;
import cowbook.web.rest.mapper.KategoriMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Service Implementation for managing Kategori.
 */
@Service
@Transactional
public class KategoriGetServiceImpl implements KategoriGetService {

    private final Logger log = LoggerFactory.getLogger(KategoriGetServiceImpl.class);

    @Inject
    private KategoriRepository kategoriRepository;

    @Inject
    private KategoriMapper kategoriMapper;

    /**
     *  get all the kategoris.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Kategori> findAll(Pageable pageable) {
        log.debug("Request to get all Kategoris");
        Page<Kategori> result = kategoriRepository.findAll(pageable);
        return result;
    }

    /**
     *  get one kategori by id.
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public KategoriDTO findOne(Long id) {
        log.debug("Request to get Kategori : {}", id);
        Kategori kategori = kategoriRepository.findOne(id);
        KategoriDTO kategoriDTO = kategoriMapper.kategoriToKategoriDTO(kategori);
        return kategoriDTO;
    }
}
