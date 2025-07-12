# java-design-patterns
Examples of Design Patterns in Java
## Creational Design Patterns
### Singleton
This design pattern ensures that only one instance is created and provide a global access method to access it. This pattern is useful for some scenarios such as database connections, global configurations, logging, managing thread pools and printer jobs,  centrally managing state and actions on user interface components and where there is a need to control memory usage on low resource computing devices.
#### Different Implementations
- Lazy Singleton
- Eager Singleton (guaranteed thread-safe)
- "Double Checked Locking" Singleton 
- Inner Class Lazy Singleton (guaranteed thread-safe)