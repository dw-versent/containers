#Containers

## A TQXR project

This is a reference implementation of a client-server setup using multi-project
maven and docker-compose.

The client will wait for the server to become available - however there's a
catch: the client must itself know that the server might not be available.

A "beautiful" scenario might involve the client not being started at all,
until the server has started and is ready to serve on the TCP port. There is
a lot of talk about this on the internets.

In practice I subscribe to the policy that my client should know that it's a big
scary world out there and the server may not actually be available. It's most
likely going to be ok, so wait a while and keep trying before giving up.

```
___________________  ____  _____________ 
\__    ___/\_____  \ \   \/  /\______   \
  |    |    /  / \  \ \     /  |       _/
  |    |   /   \_/.  \/     \  |    |   \
  |____|   \_____\ \_/___/\  \ |____|_  /
                  \__>     \_/        \/ 
```

## Building and Running

## Preparing our docker world

For some reason docker-compose will not automatically pull down images for us
so we need to run `docker-compose pull` once in order to get the base images we
need.

*Windows is of course causing problems too!*
Make sure you set docker for windows up so that the drive you have cloned this
repo to (in my case `c:`) is selected as a shared drive in the docker control
applet (the thing you click from the system tray to change settings etc).


### To build:
From the root folder, use `mvn package`.
This will build the jars which are used when running the project.

### To run it:
From the root folder, use `docker-compose up`. This will run both images and
you will see the server starting, then after it has come up the client will 
continue to start.