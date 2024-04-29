# Tool Stack

- Java - Development Language
- IntelliJ IDE - Development IDE
- Maven - Package Management
- Junit5 - Testing Framework
- Selenium - Web Driver for Testing

# Run Commands

### Without Parameters
```
mvn clean test
```

### With Parameters

```
mvn clean test -Dbrowser="chrome" -Dheadless=false
```

## Parameters

| key  | value | default  |
| ------------- | ------------- | ------------- |
| browser | chrome  | chrome | 
| headless  | true,false  | false | 
| locale | tr,en | tr | 
| env | test,prod | prod |
