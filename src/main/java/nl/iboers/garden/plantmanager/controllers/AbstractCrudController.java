package nl.iboers.garden.plantmanager.controllers;

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

/**
 * @author Ivor
 */
public abstract class AbstractCrudController<T extends Identifiable, R extends JpaRepository<T, Long>> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCrudController.class);

    final R entityRepository;

    protected AbstractCrudController(R entityRepository) {
        this.entityRepository = entityRepository;
    }


    @GetMapping()
    public List<T> getAll() {
        return entityRepository.findAll();
    }
    public List<T> getAll(Sort sortBy) {
        return entityRepository.findAll(sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable Long id) {
        LOG.debug("get by id={}", id);
        return entityRepository.findById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping()
    public ResponseEntity<PostResponse> save(@RequestBody T item) {
        LOG.debug("Updating an existing item ID {}", item.getId());
        return entityRepository.findById(item.getId())
                .map(dbItem -> getUpdateResponse(dbItem, item))
                .orElse(new ResponseEntity<>(new PostResponse(item.getId(), "No entity found with ID " + item.getId()),
                        HttpStatus.NOT_FOUND));
    }

    /**
     * override this method if you need to customize the updating of an entity item
     * @param dbItem item in db
     * @param updatedItem item from request
     * @return the response
     */
    ResponseEntity<PostResponse> getUpdateResponse(T dbItem, T updatedItem) {
        var saved = entityRepository.save(updatedItem);
        return new ResponseEntity<>(new PostResponse(saved.getId()), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PostResponse> create(@RequestBody T item) {
        LOG.debug("Creating a new item");
        var savedItem = entityRepository.save(item);
        return new ResponseEntity<>(new PostResponse(savedItem.getId()), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> delete(@PathVariable Long id) {
        return entityRepository.findById(id)
                .map(this::getDeleteResponse)
                .orElse(new ResponseEntity<>(new PostResponse("No entity found with ID " + id), HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<PostResponse> getDeleteResponse(T entity) {
        Long id = entity.getId();
        entityRepository.delete(entity);
        return new ResponseEntity<>(new PostResponse(id), HttpStatus.OK);
    }


}
