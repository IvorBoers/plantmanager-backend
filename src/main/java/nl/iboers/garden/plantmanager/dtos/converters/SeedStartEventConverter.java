package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.SeedStartEventDto;
import nl.iboers.garden.plantmanager.entities.SeedStartEvent;
import nl.iboers.garden.plantmanager.repositories.SeedPackageRepository;
import org.springframework.stereotype.Component;

/**
 * @author Ivor Boers
 */
@Component
public class SeedStartEventConverter extends  AbstractEventDtoConverter<SeedStartEvent, SeedStartEventDto> {
    private final SeedPackageRepository seedPackageRepository;

    public SeedStartEventConverter(SeedPackageRepository seedPackageRepository) {
        this.seedPackageRepository = seedPackageRepository;
    }

    @Override
    public SeedStartEvent createNewEntity() {
        return new SeedStartEvent();
    }

    @Override
    public SeedStartEventDto createNewDto() {
        return new SeedStartEventDto();
    }

    @Override
    public SeedStartEvent convert(SeedStartEvent entity, SeedStartEventDto dto) {
        SeedStartEvent event = super.convert(entity, dto);
        entity.setSeedPackage(
                seedPackageRepository
                        .findById(dto.getSeedPackageId())
                        .orElseThrow(() -> new IllegalArgumentException("SeedPackage id=" + dto.getSeedPackageId() + " was not found")));
        return event;
    }
}
