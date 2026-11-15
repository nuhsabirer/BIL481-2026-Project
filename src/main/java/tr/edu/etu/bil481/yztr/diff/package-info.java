/**
 * Pull request diff: compares the dependencies of the base and head Git refs.
 *
 * <p>Reads dependency files with {@code git show} without changing the working tree and
 * classifies each package as added, removed, upgraded, downgraded or unchanged (FR-2.1).
 *
 * <p>May depend on: {@code core}.
 */
package tr.edu.etu.bil481.yztr.diff;
