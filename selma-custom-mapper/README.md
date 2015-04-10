# selma-custom-mapper

## Goal

Try to demonstrate that there is an issue if you initialize the selma mapper into field declaration of the custom
mapper.

## Scenario

Given a mapper `SelmaMapper` that declares a custom mapper `CustomSelmaMapper`.    
Given this `CustomSelmaMapper` wants to use `SelmaMapper` again.  
If `CustomSelmaMapper` initialize `SelmaMapper` into a field declaration,      
When `SelmaMapper` is first used and initialized,  
Then there is a `StackOverflowError`.

## Reproduced

This failing scenario is reproduced as an implementation example into this project.

    -------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    Running selma.SelmaMapperTest
    Tests run: 2, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.212 sec <<< FAILURE!
    should_use_eagerly_loaded_selma_mapper_from_a_custom_selma_mapper(selma.SelmaMapperTest)  Time elapsed: 0.124 sec  <<< ERROR!
    java.lang.StackOverflowError
    (...)
    	at fr.xebia.extras.selma.Selma.getMapper(Selma.java:146)
    	at fr.xebia.extras.selma.Selma.access$100(Selma.java:55)
    	at fr.xebia.extras.selma.Selma$MapperBuilder.build(Selma.java:265)
    	at selma.eager.CustomSelmaMapper.<init>(CustomSelmaMapper.java:9)
    	at selma.eager.SelmaMapperSelmaGeneratedClass.<init>(SelmaMapperSelmaGeneratedClass.java:54)
    	at sun.reflect.GeneratedConstructorAccessor1.newInstance(Unknown Source)
    	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    	at java.lang.reflect.Constructor.newInstance(Constructor.java:422)
    	at java.lang.Class.newInstance(Class.java:442)
    (...)
    
    
    Results :
    
    Tests in error: 
      should_use_eagerly_loaded_selma_mapper_from_a_custom_selma_mapper(selma.SelmaMapperTest)
    
    Tests run: 2, Failures: 0, Errors: 1, Skipped: 0
    
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD FAILURE
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 1.227 s
    [INFO] Finished at: 2015-04-10T23:22:28+02:00
    [INFO] Final Memory: 10M/220M
    [INFO] ------------------------------------------------------------------------
