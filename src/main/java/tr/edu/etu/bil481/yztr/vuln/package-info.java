/**
 * Vulnerability engine (UC-1): matches package versions against OSV.dev advisories.
 *
 * <p>Looks up the local cache first, queries the OSV.dev API only in online mode, and
 * merges CVE, GHSA and PYSEC identifiers of the same vulnerability (FR-3.1).
 *
 * <p>May depend on: {@code core}, {@code cache}.
 */
package tr.edu.etu.bil481.yztr.vuln;
