

C:\kafka>bin\windows\kafka-server-start.bat config\server.properties

creat topic
bin\windows\kafka-topics.bat -create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic traca

show topic
bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic traca

bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic traca --from-beginning