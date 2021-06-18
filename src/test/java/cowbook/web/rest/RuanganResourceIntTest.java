package cowbook.web.rest;

import cowbook.Application;
import cowbook.domain.Ruangan;
import cowbook.repository.RuanganRepository;
import cowbook.service.ruangan.RuanganService;
import cowbook.web.rest.dto.RuanganDTO;
import cowbook.web.rest.mapper.RuanganMapper;

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
 * Test class for the RuanganResource REST controller.
 *
 * @see RuanganResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RuanganResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_NAMA_RUANGAN = "AAAAA";
    private static final String UPDATED_NAMA_RUANGAN = "BBBBB";

    private static final Double DEFAULT_HARGA = 1D;
    private static final Double UPDATED_HARGA = 2D;

    private static final Boolean DEFAULT_AKTIF = false;
    private static final Boolean UPDATED_AKTIF = true;
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
    private RuanganRepository ruanganRepository;

    @Inject
    private RuanganMapper ruanganMapper;

    @Inject
    private RuanganService ruanganService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRuanganMockMvc;

    private Ruangan ruangan;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RuanganResource ruanganResource = new RuanganResource();
        ReflectionTestUtils.setField(ruanganResource, "ruanganService", ruanganService);
        ReflectionTestUtils.setField(ruanganResource, "ruanganMapper", ruanganMapper);
        this.restRuanganMockMvc = MockMvcBuilders.standaloneSetup(ruanganResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        ruangan = new Ruangan();
        ruangan.setNama_ruangan(DEFAULT_NAMA_RUANGAN);
        ruangan.setHarga(DEFAULT_HARGA);
        ruangan.setAktif(DEFAULT_AKTIF);
        ruangan.setDibuatOleh(DEFAULT_DIBUAT_OLEH);
        ruangan.setTanggalDibuat(DEFAULT_TANGGAL_DIBUAT);
        ruangan.setDiperbaharuiOleh(DEFAULT_DIPERBAHARUI_OLEH);
        ruangan.setTanggalDiperbaharui(DEFAULT_TANGGAL_DIPERBAHARUI);
    }

    @Test
    @Transactional
    public void createRuangan() throws Exception {
        int databaseSizeBeforeCreate = ruanganRepository.findAll().size();

        // Create the Ruangan
        RuanganDTO ruanganDTO = ruanganMapper.ruanganToRuanganDTO(ruangan);

        restRuanganMockMvc.perform(post("/api/ruangans")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ruanganDTO)))
                .andExpect(status().isCreated());

        // Validate the Ruangan in the database
        List<Ruangan> ruangans = ruanganRepository.findAll();
        assertThat(ruangans).hasSize(databaseSizeBeforeCreate + 1);
        Ruangan testRuangan = ruangans.get(ruangans.size() - 1);
        assertThat(testRuangan.getNama_ruangan()).isEqualTo(DEFAULT_NAMA_RUANGAN);
        assertThat(testRuangan.getHarga()).isEqualTo(DEFAULT_HARGA);
        assertThat(testRuangan.getAktif()).isEqualTo(DEFAULT_AKTIF);
        assertThat(testRuangan.getDibuatOleh()).isEqualTo(DEFAULT_DIBUAT_OLEH);
        assertThat(testRuangan.getTanggalDibuat()).isEqualTo(DEFAULT_TANGGAL_DIBUAT);
        assertThat(testRuangan.getDiperbaharuiOleh()).isEqualTo(DEFAULT_DIPERBAHARUI_OLEH);
        assertThat(testRuangan.getTanggalDiperbaharui()).isEqualTo(DEFAULT_TANGGAL_DIPERBAHARUI);
    }

    @Test
    @Transactional
    public void getAllRuangans() throws Exception {
        // Initialize the database
        ruanganRepository.saveAndFlush(ruangan);

        // Get all the ruangans
        restRuanganMockMvc.perform(get("/api/ruangans?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(ruangan.getId().intValue())))
                .andExpect(jsonPath("$.[*].nama_ruangan").value(hasItem(DEFAULT_NAMA_RUANGAN.toString())))
                .andExpect(jsonPath("$.[*].harga").value(hasItem(DEFAULT_HARGA.doubleValue())))
                .andExpect(jsonPath("$.[*].aktif").value(hasItem(DEFAULT_AKTIF.booleanValue())))
                .andExpect(jsonPath("$.[*].dibuatOleh").value(hasItem(DEFAULT_DIBUAT_OLEH.toString())))
                .andExpect(jsonPath("$.[*].tanggalDibuat").value(hasItem(DEFAULT_TANGGAL_DIBUAT_STR)))
                .andExpect(jsonPath("$.[*].diperbaharuiOleh").value(hasItem(DEFAULT_DIPERBAHARUI_OLEH.toString())))
                .andExpect(jsonPath("$.[*].tanggalDiperbaharui").value(hasItem(DEFAULT_TANGGAL_DIPERBAHARUI_STR)));
    }

    @Test
    @Transactional
    public void getRuangan() throws Exception {
        // Initialize the database
        ruanganRepository.saveAndFlush(ruangan);

        // Get the ruangan
        restRuanganMockMvc.perform(get("/api/ruangans/{id}", ruangan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(ruangan.getId().intValue()))
            .andExpect(jsonPath("$.nama_ruangan").value(DEFAULT_NAMA_RUANGAN.toString()))
            .andExpect(jsonPath("$.harga").value(DEFAULT_HARGA.doubleValue()))
            .andExpect(jsonPath("$.aktif").value(DEFAULT_AKTIF.booleanValue()))
            .andExpect(jsonPath("$.dibuatOleh").value(DEFAULT_DIBUAT_OLEH.toString()))
            .andExpect(jsonPath("$.tanggalDibuat").value(DEFAULT_TANGGAL_DIBUAT_STR))
            .andExpect(jsonPath("$.diperbaharuiOleh").value(DEFAULT_DIPERBAHARUI_OLEH.toString()))
            .andExpect(jsonPath("$.tanggalDiperbaharui").value(DEFAULT_TANGGAL_DIPERBAHARUI_STR));
    }

    @Test
    @Transactional
    public void getNonExistingRuangan() throws Exception {
        // Get the ruangan
        restRuanganMockMvc.perform(get("/api/ruangans/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRuangan() throws Exception {
        // Initialize the database
        ruanganRepository.saveAndFlush(ruangan);

		int databaseSizeBeforeUpdate = ruanganRepository.findAll().size();

        // Update the ruangan
        ruangan.setNama_ruangan(UPDATED_NAMA_RUANGAN);
        ruangan.setHarga(UPDATED_HARGA);
        ruangan.setAktif(UPDATED_AKTIF);
        ruangan.setDibuatOleh(UPDATED_DIBUAT_OLEH);
        ruangan.setTanggalDibuat(UPDATED_TANGGAL_DIBUAT);
        ruangan.setDiperbaharuiOleh(UPDATED_DIPERBAHARUI_OLEH);
        ruangan.setTanggalDiperbaharui(UPDATED_TANGGAL_DIPERBAHARUI);
        RuanganDTO ruanganDTO = ruanganMapper.ruanganToRuanganDTO(ruangan);

        restRuanganMockMvc.perform(put("/api/ruangans")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ruanganDTO)))
                .andExpect(status().isOk());

        // Validate the Ruangan in the database
        List<Ruangan> ruangans = ruanganRepository.findAll();
        assertThat(ruangans).hasSize(databaseSizeBeforeUpdate);
        Ruangan testRuangan = ruangans.get(ruangans.size() - 1);
        assertThat(testRuangan.getNama_ruangan()).isEqualTo(UPDATED_NAMA_RUANGAN);
        assertThat(testRuangan.getHarga()).isEqualTo(UPDATED_HARGA);
        assertThat(testRuangan.getAktif()).isEqualTo(UPDATED_AKTIF);
        assertThat(testRuangan.getDibuatOleh()).isEqualTo(UPDATED_DIBUAT_OLEH);
        assertThat(testRuangan.getTanggalDibuat()).isEqualTo(UPDATED_TANGGAL_DIBUAT);
        assertThat(testRuangan.getDiperbaharuiOleh()).isEqualTo(UPDATED_DIPERBAHARUI_OLEH);
        assertThat(testRuangan.getTanggalDiperbaharui()).isEqualTo(UPDATED_TANGGAL_DIPERBAHARUI);
    }

    @Test
    @Transactional
    public void deleteRuangan() throws Exception {
        // Initialize the database
        ruanganRepository.saveAndFlush(ruangan);

		int databaseSizeBeforeDelete = ruanganRepository.findAll().size();

        // Get the ruangan
        restRuanganMockMvc.perform(delete("/api/ruangans/{id}", ruangan.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Ruangan> ruangans = ruanganRepository.findAll();
        assertThat(ruangans).hasSize(databaseSizeBeforeDelete - 1);
    }
}
