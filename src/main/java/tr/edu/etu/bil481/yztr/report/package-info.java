/**
 * Report generator: renders findings as the Markdown pull request comment and as JSON.
 *
 * <p>The same inputs always produce the same JSON output (NFR-4). The Markdown report is
 * the body of the single pull request comment that the GitHub Action updates in place
 * (FR-8.1).
 *
 * <p>May depend on: {@code core}.
 */
package tr.edu.etu.bil481.yztr.report;
