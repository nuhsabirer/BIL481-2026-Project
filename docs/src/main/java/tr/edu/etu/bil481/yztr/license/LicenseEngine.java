package tr.edu.etu.bil481.yztr.license;

import tr.edu.etu.bil481.yztr.core.Finding;
import tr.edu.etu.bil481.yztr.core.PackageId;

import java.util.List;
import java.util.Optional;

/** Evaluates dependency licenses for UC-2 (FR-4.1 and FR-4.2). */
@FunctionalInterface
public interface LicenseEngine {

    /**
     * Normalizes license metadata to SPDX and evaluates AND, OR, and WITH expressions
     * against the project license, distribution mode, and configured license rules.
     * For the proprietary policy in TC-2 and TC-3, an allowed MIT dependency produces
     * no findings, and a prohibited GPL-3.0 dependency produces an incompatibility
     * finding. The policy engine applies suppressions and decides the exit code.
     *
     * @param pkg the dependency's ecosystem, name, and resolved version
     * @param declaredLicense the declaration from the input, or {@link Optional#empty()} if absent
     * @param distributionMode the project's distribution mode
     * @param projectLicense the project's SPDX expression or license reference, such as
     *        {@code LicenseRef-Proprietary}
     * @param offlineMode whether to use only the declaration and local license metadata
     * @return findings for the given package, or an empty list if its license is compatible.
     *         An unrecognized declaration needs review. Missing license metadata in offline
     *         mode produces an UNKNOWN finding with zero network requests.
     */
    List<Finding> evaluate(
            PackageId pkg, Optional<String> declaredLicense, DistributionMode distributionMode,
            String projectLicense, boolean offlineMode);

    enum DistributionMode {
        INTERNAL,
        DISTRIBUTED_BINARY,
        NETWORK_SERVICE
    }
}
