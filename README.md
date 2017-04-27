# thunderkick-client
Thunderkick Casino API client written in Java

Supports the account endpoints and the free rounds template and assignments. 

Some Examples (more to come): 

**Build**
``` java
Thunderkick thunderkick = Thunderkick.build(accessCredentials); // OR
Thunderkick thunderkick = Thunderkick.build(apiUrl, operatorId, username, password);
```


**Get or Search for Free Rounds Template**
``` java
Result<FreeRoundsTemplate> template = thunderkick.freeRoundsTemplate().get(templateRef);

FreeRoundsTemplateSearchQuery searchQuery = new FreeRoundsTemplateSearchQuery();
LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
searchQuery.setCreationDateFrom(fromLocalDate(sixMonthsAgo));
Result<FreeRoundsTemplateList> templates = thunderkick.freeRoundsTemplate().search(searchQuery);
```

**Assign or Search for Assignments**
``` java
String playerRef = "myRef";
String templateRef = "templateRef";
String playerTemplateRef = playerRef + templateRef;

FreeRoundsTemplatePlayerAssignment freeRoundsTemplatePlayerAssignment = new FreeRoundsTemplatePlayerAssignment(fromLocalDate(LocalDateTime.now()), fromLocalDate(LocalDateTime.now().plusMonths(1)), templateRef);

// assign to player
Result<Void> assignment = thunderkick.freeRounds().assignByPlayerRef(playerRef, playerTemplateRef, freeRoundsTemplatePlayerAssignment);

// view player assignments
Result<FreeRoundsTemplatePlayerAssignments> assignments = thunderkick.freeRounds().getPlayerAssignmentsByPlayerRef(playerRef, false);
```

Note: When there's an error the Result class contains the message and error code. 

Usage:

```xml
<dependency>
    <groupId>com.lindar</groupId>
    <artifactId>thunderkick-client</artifactId>
    <version>1.0.0</version>
</dependency>
```
