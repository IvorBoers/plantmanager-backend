package nl.iboers.garden.plantmanager.repositories;

import nl.iboers.garden.plantmanager.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository  extends JpaRepository<Image, Long> {
}
