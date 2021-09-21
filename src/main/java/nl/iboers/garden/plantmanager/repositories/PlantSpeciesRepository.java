package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlantSpeciesRepository extends JpaRepository<PlantSpecies, Long> {

    List<PlantSpecies> getPlantSpeciesByParentIdOrderByName(Long parentId);
}
