package cowbook.web.rest;

import com.codahale.metrics.annotation.Timed;
import cowbook.domain.Ruangan;
import cowbook.service.ruangan.RuanganGetService;
import cowbook.service.ruangan.RuanganService;
import cowbook.web.rest.util.HeaderUtil;
import cowbook.web.rest.util.PaginationUtil;
import cowbook.web.rest.dto.RuanganDTO;
import cowbook.web.rest.mapper.RuanganMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Ruangan.
 */
@RestController
@RequestMapping("/api")
public class RuanganResource {

    private final Logger log = LoggerFactory.getLogger(RuanganResource.class);

    @Inject
    private RuanganService ruanganService;

    @Inject
    private RuanganMapper ruanganMapper;

    @Inject
    private RuanganGetService ruanganGetService;

    /**
     * POST  /ruangans -> Create a new ruangan.
     */
    @RequestMapping(value = "/ruangans",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RuanganDTO> createRuangan(@RequestBody RuanganDTO ruanganDTO) throws URISyntaxException {
        log.debug("REST request to save Ruangan : {}", ruanganDTO);
        if (ruanganDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ruangan", "idexists", "A new ruangan cannot already have an ID")).body(null);
        }
        RuanganDTO result = ruanganService.save(ruanganDTO);
        return ResponseEntity.created(new URI("/api/ruangans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("ruangan", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ruangans -> Updates an existing ruangan.
     */
    @RequestMapping(value = "/ruangans",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RuanganDTO> updateRuangan(@RequestBody RuanganDTO ruanganDTO) throws URISyntaxException {
        log.debug("REST request to update Ruangan : {}", ruanganDTO);
        if (ruanganDTO.getId() == null) {
            return createRuangan(ruanganDTO);
        }
        RuanganDTO result = ruanganService.save(ruanganDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("ruangan", ruanganDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ruangans -> get all the ruangans.
     */
    @RequestMapping(value = "/ruangans",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<RuanganDTO>> getAllRuangans(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Ruangans");
        Page<Ruangan> page = ruanganGetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ruangans");
        return new ResponseEntity<>(page.getContent().stream()
            .map(ruanganMapper::ruanganToRuanganDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /ruangans/:id -> get the "id" ruangan.
     */
    @RequestMapping(value = "/ruangans/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RuanganDTO> getRuangan(@PathVariable Long id) {
        log.debug("REST request to get Ruangan : {}", id);
        RuanganDTO ruanganDTO = ruanganGetService.findOne(id);
        return Optional.ofNullable(ruanganDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /ruangans/:id -> delete the "id" ruangan.
     */
    @RequestMapping(value = "/ruangans/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRuangan(@PathVariable Long id) {
        log.debug("REST request to delete Ruangan : {}", id);
        ruanganService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("ruangan", id.toString())).build();
    }
}
