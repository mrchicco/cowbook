package cowbook.domain;

import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Kategori.
 */
@Entity
@Table(name = "kategori")
public class Kategori implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nama_kategori")
    private String namaKategori;
    
    @Column(name = "kapasitas")
    private Integer kapasitas;
    
    @Column(name = "dibuat_oleh")
    private String dibuatOleh;
    
    @Column(name = "tanggal_dibuat")
    private ZonedDateTime tanggalDibuat;
    
    @Column(name = "diperbaharui_oleh")
    private String diperbaharuiOleh;
    
    @Column(name = "tanggal_diperbaharui")
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
        Kategori kategori = (Kategori) o;
        if(kategori.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, kategori.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Kategori{" +
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
