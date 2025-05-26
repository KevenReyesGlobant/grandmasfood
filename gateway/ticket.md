# Maven Parent Overrides Issue

## Description
The project POM inherits unwanted elements like `<license>` and `<developers>` from the parent POM, which requires manual overrides to prevent this inheritance.

## Expected Behavior
The project POM should only inherit necessary elements from the parent POM without requiring manual overrides for unwanted elements.

## Actual Behavior
The project POM inherits unwanted elements such as `<license>` and `<developers>` from the parent POM, necessitating manual overrides.

## Steps to Reproduce
1. Set up a Maven project with a parent POM.
2. Observe the inherited elements in the project POM.
3. Note the presence of unwanted elements like `<license>` and `<developers>`.

## Related Files
- `path/to/project/pom.xml`: Project POM file where unwanted elements are inherited.
- `path/to/parent/pom.xml`: Parent POM file from which elements are inherited.

## Code and Symbols
```xml
<!-- Example of unwanted inheritance in project POM -->
<project>
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>com.example</groupId>
    <artifactId>parent-project</artifactId>
    <version>1.0.0</version>
  </parent>
  <artifactId>child-project</artifactId>
  <!-- Unwanted inherited elements -->
  <licenses>
    <license>
      <name>Unwanted License</name>
    </license>
  </licenses>
  <developers>
    <developer>
      <name>Unwanted Developer</name>
    </developer>
  </developers>
</project>
```
