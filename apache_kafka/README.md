# Kafka
https://github.com/dilipsundarraj1/kafka-for-developers-using-spring-boot

* Cluster - consist of multiple brokers
* Zookeeper manages borkers
* Kafka clients interacts with the borkers
* Producers & Consumers
* Advanced APIs:
- Kafka connect & Sink connect
- Strams API
* Order is only guaranteed at partition level

## Topics
* Topic is entity in kafka with a name (like a table in db)
* Lives in a broker

## Partitions - ordered, immutable sequense of records
* Where the message is located in a Topic
* Topci may have more than 1 Partitions
* Each record is assigned a sequential number called offset
* Parititions are independed from each other

## Example commands
* Create topic: ./kaftopics.sh --create --topic test-topic -zookeeper zookeeper_try:2181 --replication-factor 1 --partitions 4
* Start producer: ./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic
* Start consumer: ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
* Producer with a key: ./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"
* Consumer with a key: ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"
* Topics list: ./kafka-topics.sh --zookeeper zookeeper_try:2181 --list
* Viwe consumer groups: ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
* Describe topics: ./kafka-topics.sh --zookeeper localhost:2181 --describe (replicas and stuff)

## Consumer offsets - like a bookmark for the consumer to start read where it left
* from begining
* latest
* specific offset

```
root@909b2b701c0d:/opt/kafka_2.13-2.8.1/bin# ./kafka-topics.sh --zookeeper zookeeper_try:2181 --list
__consumer_offsets // auto-created by broker
test-topic // our topic
```

## Consumer groups
* Group id is important for scalable message consumption
* Each applicatoin could have unique consumer group
* For console conumers group id is auto-generated (if not provided)

## Retention policy


## Brokers
* In order to have mutiple kafka bokers we need new server.properites file for each of them with unique borker id
* Replication factor - create message copies in other brokers

## Kafka template
* KakfaTemplate.send() > Serizer (key || value serializer) > Partitioner (default logic) > RecordAccumulator (has record bach for each partition)
* Kafka admin - create topics programatically

## Tests
* Embeded kafka - in memoryh kafka
* Integration tests - combine different layers and verify ther work as expected
* Controll adivce concept

## Important configs
* acks - 0, 1 or all
* retries, retry.backoff.ms

## Consumers
* KafkaMessageListnerContainer - implemenation of message listner container - pulls record, single threaded
* ConcurrentMessageListenerContainer - mutilple instacnes of listnere containers, multple instances (threds)
* @KafkaListner - uses ConcurrentMessageListenerContainer
* Consumer groups - multiple instances of the same application
* Rebalance