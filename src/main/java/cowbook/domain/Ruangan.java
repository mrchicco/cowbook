package cowbook.domain;

import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Ruangan.
 */
@Entity
@Table(name = "ruangan")
public class Ruangan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nama_ruangan")
    private String nama_ruangan;
    
    @Column(name = "harga")
    private Double harga;
    
    @Column(name = "aktif")
    private Boolean aktif;
    
    @Column(name = "dibuat_oleh")
    private String dibuatOleh;
    
    @Column(name = "tanggal_dibuat")
    private ZonedDateTime tanggalDibuat;
    
    @Column(name = "diperbaharui_oleh")
    private String diperbaharuiOleh;
    
    @Column(name = "tanggal_diperbaharui")
    private ZonedDateTime tanggalDiperbaharui;
    
    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;

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

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ruangan ruangan = (Ruangan) o;
        if(ruangan.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, ruangan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Ruangan{" +
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
