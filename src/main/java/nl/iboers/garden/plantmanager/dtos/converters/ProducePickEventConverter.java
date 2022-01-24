package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.DtoConvertionException;
import nl.iboers.garden.plantmanager.dtos.ProducePickEventDto;
import nl.iboers.garden.plantmanager.entities.Image;
import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.entities.ProducePickEvent;
import nl.iboers.garden.plantmanager.repositories.ImageRepository;
import nl.iboers.garden.plantmanager.repositories.PlantRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Ivor Boers
 */
@Component
public class ProducePickEventConverter extends AbstractEventDtoConverter<ProducePickEvent, ProducePickEventDto> {

    private final PlantRepository plantRepository;
    private final ImageRepository imageRepository;

    public ProducePickEventConverter(PlantRepository plantRepository, ImageRepository imageRepository) {
        this.plantRepository = plantRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ProducePickEvent createNewEntity() {
        return new ProducePickEvent();
    }

    @Override
    public ProducePickEventDto createNewDto() {
        return new ProducePickEventDto();
    }

    @Override
    public ProducePickEvent convert(ProducePickEvent entity, ProducePickEventDto dto) {
        ProducePickEvent result = super.convert(entity, dto);
        result.setWeight(dto.getWeight());
        result.setCount(dto.getCount());
        if (dto.getImageId() != null) {
            Optional<Image> image = imageRepository.findById(dto.getImageId());
            image.ifPresent(result::setImage);
        }
        if (dto.getPlantId() == null) {
            throw new DtoConvertionException("Field plantId is required");
        }
        Optional<Plant> plant = plantRepository.findById(dto.getPlantId());
        plant.ifPresent(result::setPlant);
        return result;
    }
}
