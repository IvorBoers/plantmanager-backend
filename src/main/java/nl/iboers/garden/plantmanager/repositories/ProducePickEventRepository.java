package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.ProducePickEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducePickEventRepository extends JpaRepository<ProducePickEvent, Long> {
}
