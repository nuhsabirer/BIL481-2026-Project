/**
 * Application layer: runs the scan pipeline.
 *
 * <p>Order of a diff scan: load the policy, read and parse the base and head dependency
 * files, classify the changed packages, pass them to the vulnerability, license and
 * typosquatting engines, apply the policy to the collected findings, build the report.
 * This is the only package that knows every analysis component.
 *
 * <p>May depend on: {@code parser}, {@code diff}, {@code vuln}, {@code license},
 * {@code typosquat}, {@code policy}, {@code report}, {@code core}.
 */
package tr.edu.etu.bil481.yztr.orchestrator;
