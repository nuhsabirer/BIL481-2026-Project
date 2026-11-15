package tr.edu.etu.bil481.yztr.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.yaml.snakeyaml.reader.UnicodeReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

/** Reads JSON metadata and the YAML policy through Jackson's tree model. */
public final class JacksonParser {

    private static final ObjectReader JSON_READER = treeReader(new JsonFactory());
    private static final ObjectReader YAML_READER = treeReader(new YAMLFactory());

    private JacksonParser() {
    }

    /**
     * Parses a single JSON value, including JSON metadata read from a Git ref.
     *
     * @throws IOException if the input is empty, malformed, or contains duplicate keys or trailing tokens
     */
    public static JsonNode parseJson(String content) throws IOException {
        JsonNode root = JSON_READER.readTree(content);
        if (root.isMissingNode()) {
            throw new IOException("JSON input is empty");
        }
        return root;
    }

    /**
     * Reads {@code yztr-policy.yml} from the path supplied by the policy engine.
     * Accepts UTF-8 and UTF-16 with a byte order mark.
     *
     * @throws IOException if the file cannot be read or has invalid policy YAML
     */
    public static ObjectNode readPolicy(Path policyPath) throws IOException {
        try (Reader input = new UnicodeReader(Files.newInputStream(policyPath))) {
            return requirePolicyMapping(YAML_READER.readTree(input));
        }
    }

    /**
     * Parses a single YAML mapping. The policy layer validates fields and values.
     *
     * @throws IOException if the input is not a single valid mapping with unique keys
     */
    public static ObjectNode parsePolicy(String content) throws IOException {
        return requirePolicyMapping(YAML_READER.readTree(content));
    }

    private static ObjectNode requirePolicyMapping(JsonNode root) throws IOException {
        if (root instanceof ObjectNode policy) {
            return policy;
        }
        throw new IOException("Policy YAML must contain a mapping at the root");
    }

    private static ObjectReader treeReader(JsonFactory factory) {
        return new ObjectMapper(factory).reader().with(
                DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY,
                DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
    }
}
