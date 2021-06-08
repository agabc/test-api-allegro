
## Requirements
1. Running tests requires [Maven](https://maven.apache.org/) to be installed.
2. There is necessity to prepare `api.local.properties` file with own creedentials. You need to:

    2.1. Register your account by https://apps.developer.allegro.pl/new
   
    2.2. Create bearer-token-for-application or bearer-token-for-user using https://developer.allegro.pl/documentation/#section/Authentication. This is mandatory for authorization any request in REST API.

<ins>`api.local.properties` file should contain:</ins>

```
clientId=XXXX

clientSecret=XXXX
```

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




