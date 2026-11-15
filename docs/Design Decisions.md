# 5. Design Decisions

## Technology Comparison 1 — JSON/YAML Parsing (Jackson vs. Gson)

yztr uses Jackson for JSON dependency metadata and `yztr-policy.yml`. Gson provides a JSON API. Using Gson for this scanner would also require a YAML parser. With `jackson-dataformat-yaml`, Jackson uses the same tree model and parsing API for both inputs. The YAML module adds a Maven dependency and keeps parsing and error handling consistent across formats. [Jackson data formats](https://github.com/FasterXML/jackson-dataformats-text#overview) · [Gson documentation](https://github.com/google/gson#readme)

`JacksonParser` reads JSON content and opens the YAML policy from its file path. It rejects duplicate keys and additional documents so a policy has one unambiguous value for each setting. The policy engine validates the policy fields and applies suppression rules, including expiry, in UC-4.

## Technology Comparison 2 — Local Database (H2 Embedded vs. SQLite)

Both H2 and SQLite support persistent local storage for OSV responses. H2 runs inside the Java 21 process through JDBC. The SQLite JDBC driver bundles native libraries and loads the binary for the host platform at runtime. yztr uses embedded H2 to keep the CLI's database dependency JVM-only across Windows, Linux, and macOS. [H2 documentation](https://www.h2database.com/html/main.html) · [SQLite JDBC documentation](https://github.com/xerial/sqlite-jdbc#how-does-sqlitejdbc-work)

The vulnerability engine looks up cached OSV responses by ecosystem, package name, and resolved version. File-backed H2 preserves responses between CLI invocations and supplies the data available for offline scans. Integration tests can seed a temporary H2 database to reproduce cache hits and misses.
