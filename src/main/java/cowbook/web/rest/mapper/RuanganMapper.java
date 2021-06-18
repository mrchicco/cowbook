package cowbook.web.rest.mapper;

import cowbook.domain.*;
import cowbook.web.rest.dto.RuanganDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ruangan and its DTO RuanganDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RuanganMapper {

    @Mapping(source = "kategori.id", target = "kategoriId")
    RuanganDTO ruanganToRuanganDTO(Ruangan ruangan);

    @Mapping(source = "kategoriId", target = "kategori")
    Ruangan ruanganDTOToRuangan(RuanganDTO ruanganDTO);

    default Kategori kategoriFromId(Long id) {
        if (id == null) {
            return null;
        }
        Kategori kategori = new Kategori();
        kategori.setId(id);
        return kategori;
    }
}
