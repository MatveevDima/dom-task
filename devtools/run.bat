@ REM Runs locally a new container.

docker run -p 8080:8080 --name dts -it dts:1-SNAPSHOT

docker container rm dts