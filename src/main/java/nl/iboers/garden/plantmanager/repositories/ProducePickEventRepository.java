package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.entities.ProducePickEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducePickEventRepository extends JpaRepository<ProducePickEvent, Long> {

    List<ProducePickEvent> findAllByPlantOrderByDateDesc(Plant plant);
}
