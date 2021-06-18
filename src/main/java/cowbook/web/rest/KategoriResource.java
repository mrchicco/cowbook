package cowbook.web.rest;

import com.codahale.metrics.annotation.Timed;
import cowbook.domain.Kategori;
import cowbook.service.kategori.KategoriGetService;
import cowbook.service.kategori.KategoriService;
import cowbook.web.rest.util.HeaderUtil;
import cowbook.web.rest.util.PaginationUtil;
import cowbook.web.rest.dto.KategoriDTO;
import cowbook.web.rest.mapper.KategoriMapper;
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
 * REST controller for managing Kategori.
 */
@RestController
@RequestMapping("/api")
public class KategoriResource {

    private final Logger log = LoggerFactory.getLogger(KategoriResource.class);

    @Inject
    private KategoriService kategoriService;

    @Inject
    private KategoriGetService kategoriGetService;

    @Inject
    private KategoriMapper kategoriMapper;

    /**
     * POST  /kategoris -> Create a new kategori.
     */
    @RequestMapping(value = "/kategoris",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<KategoriDTO> createKategori(@RequestBody KategoriDTO kategoriDTO) throws URISyntaxException {
        log.debug("REST request to save Kategori : {}", kategoriDTO);
        if (kategoriDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("kategori", "idexists", "A new kategori cannot already have an ID")).body(null);
        }
        KategoriDTO result = kategoriService.save(kategoriDTO);
        return ResponseEntity.created(new URI("/api/kategoris/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("kategori", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /kategoris -> Updates an existing kategori.
     */
    @RequestMapping(value = "/kategoris",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<KategoriDTO> updateKategori(@RequestBody KategoriDTO kategoriDTO) throws URISyntaxException {
        log.debug("REST request to update Kategori : {}", kategoriDTO);
        if (kategoriDTO.getId() == null) {
            return createKategori(kategoriDTO);
        }
        KategoriDTO result = kategoriService.save(kategoriDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("kategori", kategoriDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /kategoris -> get all the kategoris.
     */
    @RequestMapping(value = "/kategoris",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<KategoriDTO>> getAllKategoris(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Kategoris");
        Page<Kategori> page = kategoriGetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/kategoris");
        return new ResponseEntity<>(page.getContent().stream()
            .map(kategoriMapper::kategoriToKategoriDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /kategoris/:id -> get the "id" kategori.
     */
    @RequestMapping(value = "/kategoris/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<KategoriDTO> getKategori(@PathVariable Long id) {
        log.debug("REST request to get Kategori : {}", id);
        KategoriDTO kategoriDTO = kategoriGetService.findOne(id);
        return Optional.ofNullable(kategoriDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /kategoris/:id -> delete the "id" kategori.
     */
    @RequestMapping(value = "/kategoris/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteKategori(@PathVariable Long id) {
        log.debug("REST request to delete Kategori : {}", id);
        kategoriService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("kategori", id.toString())).build();
    }
}
