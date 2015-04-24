    -------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    Running selma.SelmaMapperTest
    Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.107 sec <<< FAILURE!
    should_map_a_list_to_another_even_if_it_is_defensively_copied(selma.SelmaMapperTest)  Time elapsed: 0.058 sec  <<< FAILURE!
    java.lang.AssertionError: 
    Expecting:
      <[]>
    to contain only:
      <["a", "b", "c"]>
    but could not find the following elements:
      <["a", "b", "c"]>
    
            at selma.SelmaMapperTest.should_map_a_list_to_another_even_if_it_is_defensively_copied(SelmaMapperTest.java:20)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
            at java.lang.reflect.Method.invoke(Method.java:497)
            at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
            at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
            at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
            at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
            at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
            at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
            at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
            at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
            at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
            at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
            at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
            at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)
            at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)
            at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
            at java.lang.reflect.Method.invoke(Method.java:497)
            at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)
            at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)
            at org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)
            at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)
            at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)
    
    
    Results :
    
    Failed tests:   should_map_a_list_to_another_even_if_it_is_defensively_copied(selma.SelmaMapperTest): (..)
    
    Tests run: 1, Failures: 1, Errors: 0, Skipped: 0
    
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD FAILURE
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 1.939 s
    [INFO] Finished at: 2015-04-24T15:50:52+02:00
    [INFO] Final Memory: 16M/174M
    [INFO] ------------------------------------------------------------------------
    