FROM mcr.microsoft.com/devcontainers/java:1-17-bullseye

COPY zscaler.crt /usr/local/share/ca-certificates/zscaler.crt

RUN $JAVA_HOME/bin/keytool -importcert \
    -trustcacerts \
    -file /usr/local/share/ca-certificates/zscaler.crt \
    -alias zscaler \
    -keystore $JAVA_HOME/lib/security/cacerts \
    -storepass changeit \
    -noprompt

RUN apt-get update && apt-get install -y \
        ca-certificates \
        wget \
        unzip \
        gnupg \
        dirmngr \
        curl \
    && update-ca-certificates

RUN wget --no-check-certificate https://services.gradle.org/distributions/gradle-8.4-bin.zip \
    && unzip gradle-8.4-bin.zip -d /opt/ \
    && ln -s /opt/gradle-8.4/bin/gradle /usr/bin/gradle