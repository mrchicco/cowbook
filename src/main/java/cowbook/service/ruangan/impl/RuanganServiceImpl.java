package cowbook.service.ruangan.impl;

import cowbook.domain.Ruangan;
import cowbook.repository.RuanganRepository;
import cowbook.service.ruangan.RuanganService;
import cowbook.web.rest.dto.RuanganDTO;
import cowbook.web.rest.mapper.RuanganMapper;
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
 * Service Implementation for managing Ruangan.
 */
@Service
@Transactional
public class RuanganServiceImpl implements RuanganService {

    private final Logger log = LoggerFactory.getLogger(RuanganServiceImpl.class);

    @Inject
    private RuanganRepository ruanganRepository;

    @Inject
    private RuanganMapper ruanganMapper;

    /**
     * Save a ruangan.
     * @return the persisted entity
     */
    public RuanganDTO save(RuanganDTO ruanganDTO) {
        log.debug("Request to save Ruangan : {}", ruanganDTO);
        Ruangan ruangan = ruanganMapper.ruanganDTOToRuangan(ruanganDTO);
        ruangan = ruanganRepository.save(ruangan);
        RuanganDTO result = ruanganMapper.ruanganToRuanganDTO(ruangan);
        return result;
    }

    /**
     *  delete the  ruangan by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ruangan : {}", id);
        ruanganRepository.delete(id);
    }
}
