### extract data

Clone repository, then run `./count.sh HEAD`

### import data

```shell
cat data.sql | docker compose exec --no-TTY database \
  psql --username grafana
```

### visualize data

```shell
docker compose up
```

Go to [grafana] with user admin and password admin; and then:
 - change your password
 - create a postgresql connection to host `database`, user `grafana` and disabling TLS
 - create a time series with `scalatra_migration` as table, `time` as first column and `scalatra_migration` as second
   one.

[grafana]: http://localhost:3000
