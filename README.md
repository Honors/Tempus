# Tempus
Tempus is a tool for the adjustment of block-based schedules on the
fly. Blocks are specified as either elastic or static, so that the 
algorithm will determine the appropriate distribution of changes.

The ability to provide an initial condition, jnsert blocks, et al. 
will need to be added. 

## Schedule
```java
Float[] reshape(Block[] blocks, Break[] breaks, float duration);
```
