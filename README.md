
## Requirements

Running tests requires [Maven](https://maven.apache.org/) to be installed.

## Usage

To run tests, simply execute:

```shell
mvn surefire-report:report 
```

To generate CSS run (once):
```shell
mvn site -DgenerateReports=false
```

Report will be generated to the directory:
```shell
target/site/surefire-report.html
```



###### Author
Agata Białkowska-Ciołek




