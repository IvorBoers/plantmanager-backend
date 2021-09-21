package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.SeedPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeedPackageRepository extends JpaRepository<SeedPackage, Long> {
}
