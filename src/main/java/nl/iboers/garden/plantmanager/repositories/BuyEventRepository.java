package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.BuyEvent;
import nl.iboers.garden.plantmanager.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyEventRepository extends JpaRepository<BuyEvent, Long> {
}
