/**
 * Infrastructure layer: file-backed metadata cache on embedded H2.
 *
 * <p>Stores OSV.dev responses and registry metadata between runs so that repeated scans
 * are fast (NFR-1) and offline scans have data to work with. Engines reach the cache
 * through interfaces, so tests can use a temporary database.
 *
 * <p>May depend on: {@code core}.
 */
package tr.edu.etu.bil481.yztr.cache;
