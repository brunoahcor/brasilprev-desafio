FROM java:8

VOLUME /tmp

ADD target/desafio-1.0.0-SNAPSHOT.jar desafio.jar

RUN bash -c 'touch /desafio.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/desafio.jar"]