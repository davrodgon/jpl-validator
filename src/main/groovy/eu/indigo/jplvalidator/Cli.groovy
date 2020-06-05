package eu.indigo.jplvalidator

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option

@Command(
  name = "jpl-validator",
  description = "Validates config.yml from jenkins-pipeline-library (v2)",
  version = "jpl-validator 1.0.0",
  mixinStandardHelpOptions = true
)
public class Cli implements Runnable {
    void run() {
        validate()
    }

    void validate() {
		String schema = this.getClass().getResource('/schema.json').text
        println(schema)
    }

    static void main(String[] args) {
        System.exit(new CommandLine(new Cli()).execute(args))
    }
}
