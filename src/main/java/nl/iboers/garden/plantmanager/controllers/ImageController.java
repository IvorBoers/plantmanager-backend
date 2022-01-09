package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.entities.Image;
import nl.iboers.garden.plantmanager.model.PostResponse;
import nl.iboers.garden.plantmanager.repositories.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

/**
 * @author Ivor Boers
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "images")
public class ImageController {
    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);
    public static final int MAX_IMAGE_SIZE = 4194304;
    private final ImageRepository entityRepository;

    protected ImageController(ImageRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getOne(@PathVariable Long id) {
        LOG.debug("get by id={}", id);
        return entityRepository.findById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> save(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        LOG.debug("Updating an existing item ID {}", id);
        return entityRepository.findById(id)
                .map(dbItem -> getUpdateResponse(dbItem, file))
                .orElse(new ResponseEntity<>(new PostResponse(id, "No entity found with ID " + id),
                        HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<PostResponse> getUpdateResponse(Image dbItem, MultipartFile file) {
        LOG.debug("Uploaded File {}", file.getName());
        if (file.getSize() > MAX_IMAGE_SIZE) {
            return new ResponseEntity<>(new PostResponse("Image is too big"), HttpStatus.BAD_REQUEST);
        }
        if (file.isEmpty()) {
            return new ResponseEntity<>(new PostResponse("Image has no contents"), HttpStatus.BAD_REQUEST);
        }
        return toImageEntity(file)
                .map(imgBytes -> {
                    dbItem.setBytes(imgBytes);
                    return entityRepository.save(dbItem);
                })
                .map(savedImg -> new ResponseEntity<>(new PostResponse(savedImg.getId()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new PostResponse("Failed to update image with id=" + dbItem.getId()), HttpStatus.BAD_REQUEST));
    }

    private Optional<byte[]> toImageEntity(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // The image can be jpg png or gif, but we store it always as png file in this example
                BufferedImage inputImage = ImageIO.read(file.getInputStream());
                var pngContent = new ByteArrayOutputStream();
                ImageIO.write(inputImage, "png", pngContent);

                return Optional.of(pngContent.toByteArray());
            } catch (Exception e) {
                LOG.warn("You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage(), e);
                return Optional.empty();
            }
        }
        LOG.warn("You failed to upload {} because the file was empty.", file.getOriginalFilename());
        return Optional.empty();
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<PostResponse> create(@RequestParam("file") MultipartFile file) {
        LOG.debug("Uploaded File {}", file.getName());
        if (file.getSize() > MAX_IMAGE_SIZE) {
            return new ResponseEntity<>(new PostResponse("Image is too big"), HttpStatus.BAD_REQUEST);
        }
        if (file.isEmpty()) {
            return new ResponseEntity<>(new PostResponse("Image has no contents"), HttpStatus.BAD_REQUEST);
        }
        return toImageEntity(file)
                .map(Image::new)
                .map(entityRepository::save)
                .map(image -> new ResponseEntity<>(new PostResponse(image.getId()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new PostResponse("An error occurred while saving the image"), HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> delete(@PathVariable Long id) {
        return entityRepository.findById(id)
                .map(this::getDeleteResponse)
                .orElse(new ResponseEntity<>(new PostResponse("No entity found with ID " + id), HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<PostResponse> getDeleteResponse(Image entity) {
        Long id = entity.getId();
        entityRepository.delete(entity);
        return new ResponseEntity<>(new PostResponse(id), HttpStatus.OK);
    }
}
