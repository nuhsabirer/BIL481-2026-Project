/**
 * Entry-point layer: the picocli commands of the {@code yztr} command-line tool.
 *
 * <p>Parses command-line arguments, starts the scan through the orchestrator and maps the
 * result to the process exit code: 0 = no blocking finding, 1 = BLOCK threshold reached,
 * 2 = tool error (FR-7.1). This package contains no scanning logic.
 *
 * <p>May depend on: {@code orchestrator}, {@code core}.
 */
package tr.edu.etu.bil481.yztr.cli;
