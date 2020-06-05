package eu.indigo.jplvalidator

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion
import com.networknt.schema.JsonSchema

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

@Command(
  name = "jpl-validator",
  description = "Validates config.yml from jenkins-pipeline-library (v2)",
  version = "jpl-validator 1.0.0",
  mixinStandardHelpOptions = true
)
public class Cli implements Runnable {
    @Parameters(arity="1", paramLabel="FILE", description="The file(s) whose checksum to calculate.")
    File[] files

    void run() {
        files.each { file ->
            if (file.exists()) {
                println(validate(file))
            }
        }
    }

    Set validate(File file) {
		String schema = this.getClass().getResource('/schema.json').text
        
        ObjectMapper objMapper = new ObjectMapper(new YAMLFactory())

        JsonSchemaFactory factory = JsonSchemaFactory.builder(
                JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7))
                .objectMapper(objMapper).build()

        Set invalidMessages = factory.getSchema(schema)
                .validate(objMapper.readTree(file.text))
                .message
        return invalidMessages
    }

    static void main(String[] args) {
        System.exit(new CommandLine(new Cli()).execute(args))
    }
}
