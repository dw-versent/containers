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