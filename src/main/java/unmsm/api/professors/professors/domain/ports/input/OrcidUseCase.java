package unmsm.api.professors.professors.domain.ports.input;

import unmsm.api.professors.professors.application.dto.OrcidResponse;

import java.util.Optional;

/**
 * @author garyn
 * @date 14/06/2025 16:45
 */
public interface OrcidUseCase {
    Optional<String> getOrcidId(String name);

    OrcidResponse getOrcidInfo(String orcidId);
}
