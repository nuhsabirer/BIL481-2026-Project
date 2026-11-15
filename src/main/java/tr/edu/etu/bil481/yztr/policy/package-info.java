/**
 * Policy engine (UC-4): applies {@code yztr-policy.yml} to the collected findings.
 *
 * <p>Reads the BLOCK and WARN thresholds, the project license, the distribution mode and
 * the suppression rules (FR-6.1), marks findings with an unexpired suppression as
 * suppressed, and decides whether the remaining findings reach a BLOCK threshold (FR-7.1).
 *
 * <p>May depend on: {@code core}, {@code parser} (YAML reading).
 */
package tr.edu.etu.bil481.yztr.policy;
