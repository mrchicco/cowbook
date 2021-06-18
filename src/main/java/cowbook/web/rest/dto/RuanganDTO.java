package cowbook.web.rest.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Ruangan entity.
 */
public class RuanganDTO implements Serializable {

    private Long id;

    private String nama_ruangan;


    private Double harga;


    private Boolean aktif;


    private String dibuatOleh;


    private ZonedDateTime tanggalDibuat;


    private String diperbaharuiOleh;


    private ZonedDateTime tanggalDiperbaharui;


    private Long kategoriId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNama_ruangan() {
        return nama_ruangan;
    }

    public void setNama_ruangan(String nama_ruangan) {
        this.nama_ruangan = nama_ruangan;
    }
    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }
    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
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

    public Long getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Long kategoriId) {
        this.kategoriId = kategoriId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RuanganDTO ruanganDTO = (RuanganDTO) o;

        if ( ! Objects.equals(id, ruanganDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RuanganDTO{" +
            "id=" + id +
            ", nama_ruangan='" + nama_ruangan + "'" +
            ", harga='" + harga + "'" +
            ", aktif='" + aktif + "'" +
            ", dibuatOleh='" + dibuatOleh + "'" +
            ", tanggalDibuat='" + tanggalDibuat + "'" +
            ", diperbaharuiOleh='" + diperbaharuiOleh + "'" +
            ", tanggalDiperbaharui='" + tanggalDiperbaharui + "'" +
            '}';
    }
}
