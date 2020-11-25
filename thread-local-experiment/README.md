Launch zipkin:

```
docker run --detach --publish 9411:9411 openzipkin/zipkin-slim
```

Launch sbt twice:

 - front
   ```
   java -Dsbt.color=true \
        -javaagent:/path-to/opentelemetry-javaagent-all-0.9.0.jar \
        -Dotel.exporter=zipkin \
        -Dotel.exporter.zipkin.service.name=back \
        -Dotel.integration.play.enabled=false \
        -Dhttp.port=9001 \
        -jar /path-to/sbt-launch.jar
   $ run
   ```

 - back
   ```
   java -Dsbt.color=true \
        -javaagent:/path-to/opentelemetry-javaagent-all-0.9.0.jar \
        -Dotel.exporter=zipkin \
        -Dotel.exporter.zipkin.service.name=front \
        -Dotel.integration.play.enabled=false \
        -jar /path-to/sbt-launch.jar
   $ run
   ```

Please note:
 - `-Dotel.integration.play.enabled` is for 0.9.0
 - `-Dotel.intrumentation.play.enabled` is for 0.10.1

Then test

```shell script
curl localhost:9000/001 & curl localhost:9000/002
```
