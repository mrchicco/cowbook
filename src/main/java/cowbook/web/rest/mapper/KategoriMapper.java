package cowbook.web.rest.mapper;

import cowbook.domain.*;
import cowbook.web.rest.dto.KategoriDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Kategori and its DTO KategoriDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KategoriMapper {

    KategoriDTO kategoriToKategoriDTO(Kategori kategori);

    Kategori kategoriDTOToKategori(KategoriDTO kategoriDTO);
}
