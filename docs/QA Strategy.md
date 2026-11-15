# 3. QA Strategy, Factors & Metrics

The QA strategy verifies that identical inputs produce identical scan results and that the policy blocks pull requests when active findings meet the configured thresholds. Run automated tests on GitHub Actions with Ubuntu 24.04, Java 21 LTS, and Maven 3.9. Seed a dedicated embedded H2 database for each integration test that uses the cache.

## Testing methodologies

- JUnit 5 unit tests cover Damerau-Levenshtein distance, SPDX normalization and compound expressions, and parsing `yztr-policy.yml`. For the policy, exercise valid settings, duplicate keys, and malformed YAML.
- WireMock integration tests simulate OSV.dev and PyPI responses. Verify response handling for an online lookup, reuse of cached metadata, and zero requests on an offline cache miss. Set a fixed clock in tests that exercise suppression expiry.
- Run the core acceptance cases against fixed fixtures. TC-1 expects a `HIGH` ReDoS finding for `cross-spawn@7.0.3` and exit code `1` under the configured `CVSS >= 7.0` blocking threshold. For the proprietary project, TC-2 expects an MIT dependency to pass with exit code `0` and TC-3 expects a prohibited GPL-3.0 dependency to block with exit code `1`. TC-4 expects a `CRITICAL` alert for `reqeusts`, a transposition of `requests`.

## Automation and manual checks

GitHub Actions will automate all core logic, policy parsing, and algorithm accuracy checks on every pull request. Review the CLI output and GitHub pull request comment manually for legible severity labels, readable package and version details, and correct Markdown rendering.

## Quality factors and metrics

| Quality factor | How to measure | Target |
| --- | --- | --- |
| Performance | Mean wall-clock time per PR scan on the Ubuntu runner. Use the same representative lockfile and cache state across runs. | `< 20 s` |
| Accuracy | Typosquatting false-positive rate on the legitimate popular-package fixture, calculated as false positives divided by all legitimate samples. | `< 1%` |
| Reliability | Percentage of repeated scan pairs whose JSON outputs match byte for byte. Hold the inputs, policy, clock, and metadata snapshot fixed. | `100%` |
| Maintainability | Percentage of production Java source lines covered by the automated suite, as reported by JaCoCo. [JaCoCo coverage counters](https://www.jacoco.org/jacoco/trunk/doc/counters.html) | `> 80%` |
