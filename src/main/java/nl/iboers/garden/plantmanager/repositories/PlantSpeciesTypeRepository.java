package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.PlantSpeciesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantSpeciesTypeRepository extends JpaRepository<PlantSpeciesType, Long> {

}
