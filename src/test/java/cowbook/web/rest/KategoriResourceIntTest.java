package cowbook.web.rest;

import cowbook.Application;
import cowbook.domain.Kategori;
import cowbook.repository.KategoriRepository;
import cowbook.service.kategori.KategoriService;
import cowbook.web.rest.dto.KategoriDTO;
import cowbook.web.rest.mapper.KategoriMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the KategoriResource REST controller.
 *
 * @see KategoriResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class KategoriResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_NAMA_KATEGORI = "AAAAA";
    private static final String UPDATED_NAMA_KATEGORI = "BBBBB";

    private static final Integer DEFAULT_KAPASITAS = 1;
    private static final Integer UPDATED_KAPASITAS = 2;
    private static final String DEFAULT_DIBUAT_OLEH = "AAAAA";
    private static final String UPDATED_DIBUAT_OLEH = "BBBBB";

    private static final ZonedDateTime DEFAULT_TANGGAL_DIBUAT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_TANGGAL_DIBUAT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_TANGGAL_DIBUAT_STR = dateTimeFormatter.format(DEFAULT_TANGGAL_DIBUAT);
    private static final String DEFAULT_DIPERBAHARUI_OLEH = "AAAAA";
    private static final String UPDATED_DIPERBAHARUI_OLEH = "BBBBB";

    private static final ZonedDateTime DEFAULT_TANGGAL_DIPERBAHARUI = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_TANGGAL_DIPERBAHARUI = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_TANGGAL_DIPERBAHARUI_STR = dateTimeFormatter.format(DEFAULT_TANGGAL_DIPERBAHARUI);

    @Inject
    private KategoriRepository kategoriRepository;

    @Inject
    private KategoriMapper kategoriMapper;

    @Inject
    private KategoriService kategoriService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restKategoriMockMvc;

    private Kategori kategori;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        KategoriResource kategoriResource = new KategoriResource();
        ReflectionTestUtils.setField(kategoriResource, "kategoriService", kategoriService);
        ReflectionTestUtils.setField(kategoriResource, "kategoriMapper", kategoriMapper);
        this.restKategoriMockMvc = MockMvcBuilders.standaloneSetup(kategoriResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        kategori = new Kategori();
        kategori.setNamaKategori(DEFAULT_NAMA_KATEGORI);
        kategori.setKapasitas(DEFAULT_KAPASITAS);
        kategori.setDibuatOleh(DEFAULT_DIBUAT_OLEH);
        kategori.setTanggalDibuat(DEFAULT_TANGGAL_DIBUAT);
        kategori.setDiperbaharuiOleh(DEFAULT_DIPERBAHARUI_OLEH);
        kategori.setTanggalDiperbaharui(DEFAULT_TANGGAL_DIPERBAHARUI);
    }

    @Test
    @Transactional
    public void createKategori() throws Exception {
        int databaseSizeBeforeCreate = kategoriRepository.findAll().size();

        // Create the Kategori
        KategoriDTO kategoriDTO = kategoriMapper.kategoriToKategoriDTO(kategori);

        restKategoriMockMvc.perform(post("/api/kategoris")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(kategoriDTO)))
                .andExpect(status().isCreated());

        // Validate the Kategori in the database
        List<Kategori> kategoris = kategoriRepository.findAll();
        assertThat(kategoris).hasSize(databaseSizeBeforeCreate + 1);
        Kategori testKategori = kategoris.get(kategoris.size() - 1);
        assertThat(testKategori.getNamaKategori()).isEqualTo(DEFAULT_NAMA_KATEGORI);
        assertThat(testKategori.getKapasitas()).isEqualTo(DEFAULT_KAPASITAS);
        assertThat(testKategori.getDibuatOleh()).isEqualTo(DEFAULT_DIBUAT_OLEH);
        assertThat(testKategori.getTanggalDibuat()).isEqualTo(DEFAULT_TANGGAL_DIBUAT);
        assertThat(testKategori.getDiperbaharuiOleh()).isEqualTo(DEFAULT_DIPERBAHARUI_OLEH);
        assertThat(testKategori.getTanggalDiperbaharui()).isEqualTo(DEFAULT_TANGGAL_DIPERBAHARUI);
    }

    @Test
    @Transactional
    public void getAllKategoris() throws Exception {
        // Initialize the database
        kategoriRepository.saveAndFlush(kategori);

        // Get all the kategoris
        restKategoriMockMvc.perform(get("/api/kategoris?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(kategori.getId().intValue())))
                .andExpect(jsonPath("$.[*].namaKategori").value(hasItem(DEFAULT_NAMA_KATEGORI.toString())))
                .andExpect(jsonPath("$.[*].kapasitas").value(hasItem(DEFAULT_KAPASITAS)))
                .andExpect(jsonPath("$.[*].dibuatOleh").value(hasItem(DEFAULT_DIBUAT_OLEH.toString())))
                .andExpect(jsonPath("$.[*].tanggalDibuat").value(hasItem(DEFAULT_TANGGAL_DIBUAT_STR)))
                .andExpect(jsonPath("$.[*].diperbaharuiOleh").value(hasItem(DEFAULT_DIPERBAHARUI_OLEH.toString())))
                .andExpect(jsonPath("$.[*].tanggalDiperbaharui").value(hasItem(DEFAULT_TANGGAL_DIPERBAHARUI_STR)));
    }

    @Test
    @Transactional
    public void getKategori() throws Exception {
        // Initialize the database
        kategoriRepository.saveAndFlush(kategori);

        // Get the kategori
        restKategoriMockMvc.perform(get("/api/kategoris/{id}", kategori.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(kategori.getId().intValue()))
            .andExpect(jsonPath("$.namaKategori").value(DEFAULT_NAMA_KATEGORI.toString()))
            .andExpect(jsonPath("$.kapasitas").value(DEFAULT_KAPASITAS))
            .andExpect(jsonPath("$.dibuatOleh").value(DEFAULT_DIBUAT_OLEH.toString()))
            .andExpect(jsonPath("$.tanggalDibuat").value(DEFAULT_TANGGAL_DIBUAT_STR))
            .andExpect(jsonPath("$.diperbaharuiOleh").value(DEFAULT_DIPERBAHARUI_OLEH.toString()))
            .andExpect(jsonPath("$.tanggalDiperbaharui").value(DEFAULT_TANGGAL_DIPERBAHARUI_STR));
    }

    @Test
    @Transactional
    public void getNonExistingKategori() throws Exception {
        // Get the kategori
        restKategoriMockMvc.perform(get("/api/kategoris/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKategori() throws Exception {
        // Initialize the database
        kategoriRepository.saveAndFlush(kategori);

		int databaseSizeBeforeUpdate = kategoriRepository.findAll().size();

        // Update the kategori
        kategori.setNamaKategori(UPDATED_NAMA_KATEGORI);
        kategori.setKapasitas(UPDATED_KAPASITAS);
        kategori.setDibuatOleh(UPDATED_DIBUAT_OLEH);
        kategori.setTanggalDibuat(UPDATED_TANGGAL_DIBUAT);
        kategori.setDiperbaharuiOleh(UPDATED_DIPERBAHARUI_OLEH);
        kategori.setTanggalDiperbaharui(UPDATED_TANGGAL_DIPERBAHARUI);
        KategoriDTO kategoriDTO = kategoriMapper.kategoriToKategoriDTO(kategori);

        restKategoriMockMvc.perform(put("/api/kategoris")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(kategoriDTO)))
                .andExpect(status().isOk());

        // Validate the Kategori in the database
        List<Kategori> kategoris = kategoriRepository.findAll();
        assertThat(kategoris).hasSize(databaseSizeBeforeUpdate);
        Kategori testKategori = kategoris.get(kategoris.size() - 1);
        assertThat(testKategori.getNamaKategori()).isEqualTo(UPDATED_NAMA_KATEGORI);
        assertThat(testKategori.getKapasitas()).isEqualTo(UPDATED_KAPASITAS);
        assertThat(testKategori.getDibuatOleh()).isEqualTo(UPDATED_DIBUAT_OLEH);
        assertThat(testKategori.getTanggalDibuat()).isEqualTo(UPDATED_TANGGAL_DIBUAT);
        assertThat(testKategori.getDiperbaharuiOleh()).isEqualTo(UPDATED_DIPERBAHARUI_OLEH);
        assertThat(testKategori.getTanggalDiperbaharui()).isEqualTo(UPDATED_TANGGAL_DIPERBAHARUI);
    }

    @Test
    @Transactional
    public void deleteKategori() throws Exception {
        // Initialize the database
        kategoriRepository.saveAndFlush(kategori);

		int databaseSizeBeforeDelete = kategoriRepository.findAll().size();

        // Get the kategori
        restKategoriMockMvc.perform(delete("/api/kategoris/{id}", kategori.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Kategori> kategoris = kategoriRepository.findAll();
        assertThat(kategoris).hasSize(databaseSizeBeforeDelete - 1);
    }
}
