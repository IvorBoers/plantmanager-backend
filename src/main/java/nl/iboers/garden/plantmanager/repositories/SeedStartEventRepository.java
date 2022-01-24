package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.SeedStartEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeedStartEventRepository extends JpaRepository<SeedStartEvent, Long> {
}
