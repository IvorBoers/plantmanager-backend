package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.dtos.Dto;
import nl.iboers.garden.plantmanager.dtos.converters.DtoConverter;
import nl.iboers.garden.plantmanager.entities.Identifiable;
import nl.iboers.garden.plantmanager.model.PostResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivor Boers
 */
public abstract class AbstractDtoCrudController<E extends Identifiable, D extends Dto<E>, R extends JpaRepository<E, Long>> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDtoCrudController.class);
    private final R entityRepository;
    private final DtoConverter<E, D> dtoConverter;

    protected AbstractDtoCrudController(R entityRepository, DtoConverter<E, D> dtoConverter) {
        this.entityRepository = entityRepository;
        this.dtoConverter = dtoConverter;
    }

    public R getEntityRepository() {
        return entityRepository;
    }

    public DtoConverter<E, D> getDtoConverter() {
        return dtoConverter;
    }

    @GetMapping()
    public List<D> getAll() {
        return entityRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    D convertToDto(E entity) {
        D newDto = dtoConverter.createNewDto();
        newDto.from(entity);
        return newDto;
    }


    public List<D> getAll(Sort sortBy) {
        return entityRepository.findAll(sortBy).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getOne(@PathVariable Long id) {
        LOG.debug("get by id={}", id);
        return entityRepository.findById(id)
                .map(this::convertToDto)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<PostResponse> save(@RequestBody D dto) {
        LOG.debug("Updating an existing item ID {}", dto.getId());
        return entityRepository.findById(dto.getId())
                .map(dbItem -> getUpdateResponse(dbItem, dto))
                .orElse(new ResponseEntity<>(new PostResponse(dto.getId(), "No entity found with ID " + dto.getId()),
                        HttpStatus.NOT_FOUND));
    }

    /**
     * override this method if you need to customize the updating of an entity item
     *
     * @param dbItem item in db
     * @param dto    item from request
     * @return the response
     */
    ResponseEntity<PostResponse> getUpdateResponse(E dbItem, D dto) {
        E convert = dtoConverter.convert(dbItem, dto);
        var saved = entityRepository.save(convert);
        return new ResponseEntity<>(new PostResponse(saved.getId()), HttpStatus.OK);
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<PostResponse> create(@RequestBody D dto) {
        LOG.debug("Creating a new item");
        E entity = dtoConverter.convert(dtoConverter.createNewEntity(), dto);
        var savedItem = entityRepository.save(entity);
        return new ResponseEntity<>(new PostResponse(savedItem.getId()), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> delete(@PathVariable Long id) {
        return entityRepository.findById(id)
                .map(this::getDeleteResponse)
                .orElse(new ResponseEntity<>(new PostResponse("No entity found with ID " + id), HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<PostResponse> getDeleteResponse(E entity) {
        Long id = entity.getId();
        entityRepository.delete(entity);
        return new ResponseEntity<>(new PostResponse(id), HttpStatus.OK);
    }
}
