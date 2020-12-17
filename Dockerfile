FROM openjdk:8-jre
ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt

COPY /target/bookings-api-0.0.1.jar bookings-api-0.0.1-.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar pet-0.0.1.jar --spring.profiles.active=${PROFILE}