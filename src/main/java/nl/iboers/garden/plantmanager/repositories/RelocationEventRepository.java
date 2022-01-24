package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.RelocationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelocationEventRepository extends JpaRepository<RelocationEvent, Long> {
}
