#!/usr/bin/env bash

cd datacatalog
while read -r REV; do
    git checkout "${REV}"
    COUNT=$(find . -name "*Controller.scala" | wc -l)
    DATE=$(git show --no-patch --format=format:%cI) 
    echo "INSERT INTO scalatra_migration(time, count) VALUES ('${DATE}', ${COUNT});" >> ../data.sql
done < <(git rev-list "$1")
cd ..
