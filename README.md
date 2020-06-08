## jpl-validator

Application that validates the main YAML-based configuration file from the v2 
series of the 
[indigo-dc/jenkins-pipeline-library](https://github.com/indigo-dc/jenkins-pipeline-library). 

The validation uses a [JSON schema](src/main/resources/schema.json) (Draft 7).

### Usage
```
$ ./gradlew run --args='.sqa/config.yml' 
```
