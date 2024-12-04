package es.deusto.sd.eurostyletuning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.deusto.sd.eurostyletuning.dto.PartDTO;
import es.deusto.sd.eurostyletuning.external.GackServiceGateway;

@Service
public class GackServiceImpl implements GackService {

    private final GackServiceGateway gackServiceGateway;

    public GackServiceImpl(GackServiceGateway gackServiceGateway) {
        this.gackServiceGateway = gackServiceGateway;
    }

    /**
     * Gets all parts from the GACK service.
     *
     * @return A list of PartDTO or an empty list if no parts are available.
     */
    public List<PartDTO> getAllParts() {
        Optional<List<PartDTO>> parts = gackServiceGateway.getAllPartsFromGack();

        return parts.orElse(List.of());
    }

    /**
     * Gets parts from the GACK service filtered by brand and category.
     *
     * @param brandName    The brand name to filter by.
     * @param categoryName The category name to filter by.
     * @return A list of PartDTO or an empty list if no parts match the filters.
     */
    public List<PartDTO> getPartsByFilter(String brandName, String categoryName) {
        Optional<List<PartDTO>> parts = gackServiceGateway.getPartsFromGack(brandName, categoryName);

        return parts.orElse(List.of());
    }
}
