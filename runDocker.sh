#! /bin/sh

# run the service
imageName=$(dockerImageName.sh)
imageVersion=$(getPomAttribute.sh version | sed 's/-RELEASE$//')
containerName=$(getPomAttribute.sh artifactId | sed -re 's/\W//g' |  tr '[[:upper:]]' '[[:lower:]]')

port=$(grep ^server.port src/main/resources/prod.properties | awk -F= '{ print $2 }')

docker stop $containerName
docker rm $containerName
docker run --user $(id -u):$(id -g) --network qotd --name $containerName -d -p $port:$port -v ~/.blazartech:/home/$(whoami)/.blazartech $imageName:$imageVersion
