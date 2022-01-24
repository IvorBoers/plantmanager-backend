package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.iboers.garden.plantmanager.entities.SeedPackage;
import nl.iboers.garden.plantmanager.entities.SeedStartEvent;

/**
 * @author Ivor Boers
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SeedStartEventDto extends AbstractEventDto<SeedStartEvent> {

    private long seedPackageId;

    @Override
    public void from(SeedStartEvent entity) {
        super.from(entity);
        SeedPackage seedPackage = entity.getSeedPackage();
        if (seedPackage != null) {
            this.seedPackageId = seedPackage.getId();
        }
    }
}
