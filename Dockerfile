FROM openjdk:17
ADD /build/libs/quotes-0.0.1.jar Quotes.jar
ENTRYPOINT java -jar Quotes.jar