package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.PlantLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantLocationRepository extends JpaRepository<PlantLocation, Long> {

}
