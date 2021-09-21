package nl.iboers.garden.plantmanager.model;

import lombok.Data;

/**
 * @author Ivor
 */
@Data
public class PostResponse {
    private Long entityId;
    private String errorMessage;

    public PostResponse(Long entityId) {
        this.entityId = entityId;
    }

    public PostResponse(Long entityId, String errorMessage) {
        this.entityId = entityId;
        this.errorMessage = errorMessage;
    }

    public PostResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
