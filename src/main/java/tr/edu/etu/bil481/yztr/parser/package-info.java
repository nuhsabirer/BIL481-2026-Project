/**
 * Dependency input: turns dependency files into lists of {@code PackageId}.
 *
 * <p>Supported inputs are npm {@code package-lock.json} (v2/v3), PyPI {@code poetry.lock},
 * {@code uv.lock} and pinned {@code requirements.txt}, and CycloneDX SBOM files (FR-1.1).
 * An unpinned line in {@code requirements.txt} produces a warning (FR-1.2). The package
 * also holds the shared JSON/YAML reading code built on Jackson.
 *
 * <p>May depend on: {@code core}.
 */
package tr.edu.etu.bil481.yztr.parser;
