### extract data

Clone repository, then run `./count.sh HEAD`

### import data

```shell
docker compose up
cat schema.sql | docker compose exec --no-TTY database psql --username grafana
cat data.sql   | docker compose exec --no-TTY database psql --username grafana
```

### visualize data

```shell
docker compose up
```

Go to [grafana] with user admin and password admin; and then:
 - change your password
 - create a postgresql connection to host `database`, user `grafana`, database `grafana` and disabling TLS
 - create a time series with `scalatra_migration` as table, `time` as first column and `count` as second
   one and add `WHERE $__timeFilter("time")` as `WHERE` clause.

[grafana]: http://localhost:3000
