/**
 * Typosquatting engine (UC-3): detects look-alike and non-existent package names.
 *
 * <p>Compares each added npm or PyPI package name with the 15,000 most popular names of
 * its ecosystem using the Damerau-Levenshtein distance (FR-5.1) and flags names that do
 * not exist in the registry (FR-5.2). Maven packages are not checked.
 *
 * <p>May depend on: {@code core}, {@code cache}.
 */
package tr.edu.etu.bil481.yztr.typosquat;
