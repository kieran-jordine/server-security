https://spring.io/blog/2023/05/24/spring-authorization-server-is-on-spring-initializr

** get an Access Token **
httpie:
http -f POST :8080/oauth2/token grant_type=client_credentials scope='user.read' -a admin-client:secret
curl:
curl -X POST http://localhost:8080/oauth2/token -d "grant_type=client_credentials&scope=user.read" --user "admin-client:secret"

** introspecting an Access Token **
curl -X POST http://localhost:8080/oauth2/introspect -d "token=$TOKEN" --user "$client-id:$client-secret"
