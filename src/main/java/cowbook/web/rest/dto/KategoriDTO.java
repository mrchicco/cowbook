package cowbook.web.rest.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Kategori entity.
 */
public class KategoriDTO implements Serializable {

    private Long id;

    private String namaKategori;


    private Integer kapasitas;


    private String dibuatOleh;


    private ZonedDateTime tanggalDibuat;


    private String diperbaharuiOleh;


    private ZonedDateTime tanggalDiperbaharui;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
    public Integer getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }
    public String getDibuatOleh() {
        return dibuatOleh;
    }

    public void setDibuatOleh(String dibuatOleh) {
        this.dibuatOleh = dibuatOleh;
    }
    public ZonedDateTime getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(ZonedDateTime tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }
    public String getDiperbaharuiOleh() {
        return diperbaharuiOleh;
    }

    public void setDiperbaharuiOleh(String diperbaharuiOleh) {
        this.diperbaharuiOleh = diperbaharuiOleh;
    }
    public ZonedDateTime getTanggalDiperbaharui() {
        return tanggalDiperbaharui;
    }

    public void setTanggalDiperbaharui(ZonedDateTime tanggalDiperbaharui) {
        this.tanggalDiperbaharui = tanggalDiperbaharui;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KategoriDTO kategoriDTO = (KategoriDTO) o;

        if ( ! Objects.equals(id, kategoriDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "KategoriDTO{" +
            "id=" + id +
            ", namaKategori='" + namaKategori + "'" +
            ", kapasitas='" + kapasitas + "'" +
            ", dibuatOleh='" + dibuatOleh + "'" +
            ", tanggalDibuat='" + tanggalDibuat + "'" +
            ", diperbaharuiOleh='" + diperbaharuiOleh + "'" +
            ", tanggalDiperbaharui='" + tanggalDiperbaharui + "'" +
            '}';
    }
}
