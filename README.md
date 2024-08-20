## Same type is generated twice into openapi model by Tapir

This project focuses on what are the consequences of the bugfix provided to fix this issue.

In case where a type is referenced both
 - by an endpoint with a super type thus using polymorphism and discriminator
 - by an endpoint directly

Then this type is exported into two different models:
 - one using a required discriminator
 - the other, suffixed by `1`, that do not inclue discriminator
