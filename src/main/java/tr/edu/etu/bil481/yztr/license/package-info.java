/**
 * License engine (UC-2): checks dependency licenses against the project license.
 *
 * <p>Normalizes license data to SPDX identifiers, evaluates AND, OR and WITH expressions
 * (FR-4.1) and decides compatibility from the project license and distribution mode.
 * In offline mode a package without license data is reported as UNKNOWN with no network
 * request (FR-4.2).
 *
 * <p>May depend on: {@code core}, {@code cache}.
 */
package tr.edu.etu.bil481.yztr.license;
