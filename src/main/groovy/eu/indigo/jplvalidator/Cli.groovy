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

import java.util.concurrent.Callable

@Command(
  name = "jpl-validator",
  description = "Validates config.yml from jenkins-pipeline-library (v2)",
  version = "jpl-validator jib-with-jpl",
  mixinStandardHelpOptions = true
)
public class Cli implements Callable<Integer> {
    @Parameters(index="0", paramLabel="FILE", description="The config file to validate.")
    private File file
    private validationExitCode = -1

    public Integer call() throws Exception {
        if (file.exists()) {
            Set result = validate(file)
            if (result) {
                println(result)
            }
            else {
                validationExitCode = 0
            }
        }
        return validationExitCode
    }

    private Set validate(File file) {
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
        int exitCode = new CommandLine(new Cli()).execute(args)
        System.exit(exitCode)
    }
}
