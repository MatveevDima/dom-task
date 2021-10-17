@ REM Cleans up Docker container and images.

docker container rm dts
docker image rm dts:1-SNAPSHOT
docker image prune --force