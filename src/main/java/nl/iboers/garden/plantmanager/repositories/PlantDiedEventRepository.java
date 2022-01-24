package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.PlantDiedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantDiedEventRepository extends JpaRepository<PlantDiedEvent, Long> {
}
