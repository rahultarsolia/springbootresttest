# Getting Started

Refer to API.md for details of the API.

### Build

cd <git folder>
mvn install


### Run

java -jar <path to .jar file>


By default it will run on 8080


### CI Notes

#### Build requirements

	- Java 8 JDK
	- MVN 3.6.*
	
#### Running tests

Can vary which tests are executed using the following command.
mvn test -Dgroups="com.syne.mockusermanagement.categories.IntegrationTest"
mvn test -Dgroups="com.syne.mockusermanagement.categories.UnitTest"


#### Build number for version number

The version number should be updated as part of the build process to correctly return in the API.

echo "app.version.number=2.0.${BUILDNUMBER}" > src/main/resource/application.properties
