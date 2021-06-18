package cowbook.service.ruangan.impl;

import cowbook.domain.Ruangan;
import cowbook.repository.RuanganRepository;
import cowbook.service.ruangan.RuanganGetService;
import cowbook.web.rest.dto.RuanganDTO;
import cowbook.web.rest.mapper.RuanganMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Service Implementation for managing Ruangan.
 */
@Service
@Transactional
public class RuanganGetServiceImpl implements RuanganGetService {

    private final Logger log = LoggerFactory.getLogger(RuanganGetServiceImpl.class);

    @Inject
    private RuanganRepository ruanganRepository;

    @Inject
    private RuanganMapper ruanganMapper;

    /**
     *  get all the ruangans.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Ruangan> findAll(Pageable pageable) {
        log.debug("Request to get all Ruangans");
        Page<Ruangan> result = ruanganRepository.findAll(pageable);
        return result;
    }

    /**
     *  get one ruangan by id.
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public RuanganDTO findOne(Long id) {
        log.debug("Request to get Ruangan : {}", id);
        Ruangan ruangan = ruanganRepository.findOne(id);
        RuanganDTO ruanganDTO = ruanganMapper.ruanganToRuanganDTO(ruangan);
        return ruanganDTO;
    }
}
