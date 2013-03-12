#!/bin/sh

mkdir tmp
cd tmp
cp -R ../target/classes/twitterid .
mkdir META-INF
echo "Main-Class: twitterid.TwitterIdToScreenName" > META-INF/MANIFEST.MF
zip -r twitter-id-to-screenname.jar *
cd ..
mv tmp/twitter-id-to-screenname.jar .
rm -fr tmp
