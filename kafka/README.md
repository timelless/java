``# Kafka

## Basics (section 4)
Cluster > Topics > Partitions  

Topics are split in partitions.  Messages in each partition are ordered (via id, this id is called kafka partition offset).  

Sequence of a messages in a topic is called data - stream, topics cannot be queried, data cannot be updated or modified.  

Data is kept for limited time, default is 1 week, but it is configurable.  

If producer sends null key - data is sent round-robin - partition 1 then partition 2 then partition 3 and so on.  

All the messages that share the same key will always end up being written in the same partition, thanks to hashing strategy. There could be order only in partitions that's how we can achieve oder in some way - when using the same key in given partition. I.e. offsets only have a meaning for specific partition.  

Data is sent to random partition unless key is specified.  

Kafka messages contains key - binary, value - binary  (both can be null) + compression type, headers, partition + offset, timestamp.  

Kafka message serializers are used for key and value since kafka understands bytes only. We can specify integer serializer for key for example and string serializer for value.  

Consumers implement the pull model. Consumers consume low to high offset.  

If we need to change type of data of topic we need to create new topic since consumers need to know data type that is going to be processed in advance.  

Consumer group - each consumer within the group will read from exclusive partitions. If we add consumer on the fly to the consumer group - it will be inactive (?).
It is acceptable to have multiple consumer groups on the same topic.
Consumer group - consumer offsets - kafka will store the offsets at which the consumer group has been reading. Offsets are stored in topic with name consumer_offset. When consumer is done processing the data it should once in while commit the offset and tell the kafka broker to write to the consumer offset topic. This way the consumer will read from where it was if it dies.  

By default, Java consumers will automatically commit offsets (at least once)
At least once -  offsets are going to be commited right after the message is processed.  
At most once - offset is commited as soon as the consumer receive messages.  
Exactly once - for kafka > kafka workflows or kafka > external system workflows : use an idempotent consumer.  

Kafka cluster - composed of multiple brokers. Brokers are just server but they are called brokers because they send and receive data. Kafka broker has an ID.  
After connecting to any broker (bootstrap broker) you will be connected to the entire cluster. 3 brokers is good number to start with.  

Topic replication - topics usually have 2 - 3 replication factor, this way if broker is down another kaka broker still has a copy of the data to serve and receive.  
Since we have replicas there is always a leader for a partition, at any time only one broker can be leader for a given partition. As a rule producers can only send data to the broker that is a leader for the partition (by default).  
ISR (in sync replica).  
Consumers will read by default only from the leader for the partition.  
Kafka consumer replica fetching (kafka 2.4) - new feature that allows consumer to read from closes replica.  

Producer acknowledgements - producers can choose to receive acknowledgements of data writes (to have confirmation from kafka broker), 3 values - 0, 1 - wait for the leader broker to acknowledge, all - leader + all replicas acknowledgements.  

Zookeeper - manages kafka brokers. Kafka 2.x cannot work w/o zookeeper. Kafka 3.x - Kafka raft could be used, kafka 4.x will not have zookeeper. Zookeeper operates with odd number of servers.  

Kafka cluster consists of topics.  
You cannot create topic with higher replication factor than number for brokers.  
If we don't send key - the data will be distributed among all partitions.  
If we produce in non-existing topic - we will have timeout or an error, we cannot produce in topic that does not exist. We can get different error few times but eventually topic will be created (with leader an all). More like warning will be displayed and to topic will be created. This topic has replication factor 1 and partition.  
We can consume from tail or beginning of the topic, we can show both key and values in the output.  
From beginning is useful only when there has never been a consumer offset that has been commited as part of the consumer group.  
When console consumer is started w/o group id - it will read all the topics and will disappear after some period of time.  
Offset cannot be reset while the consumer is running.  

In kafka data is serialized into bytes before being sent to kafka (key serializer and value serializer).  
Kafka producer callbacks - confirms the partition and offset the message was set to using callbacks.  
Sticky partitioner - if messages are produced very quickly the producers is batching the messages and send then in one batch i.e. same partition.  
addShutdownHook() - gracefully shutdown consumer.  

Consumer rebalance in consumer groups - whenever consumer leaves or joins a group - there is rebalance.  
Eager rebalance - all consumer stops, give up their membership of partitions, then they rejoin the consumer group and get new partition assignments. The entire group stop working for a short period of time.  
Cooperative Rebalance - reassign a small subset of the partitions from one consumer to another, this avoids consuming pause.  
partition.assignment.strategy - to configure.  
Static group membership - when consumer leaves - does not change re-assigned, in order to do so if we add group.instance.id as part of a consumer config this makes the consumer static member.  
Offsets are commited when we call .poll().  

There is also producer ask config: ask=all&min.insync.replcias.  
There is retry available for producers - for idempotent producers there is a chance that message will be sent out of order in case of retries.  
Idempotent producer - producer that won't produce duplications on network error. Default since kafka 3.0. Retries are set to max value for them, ack=true, producer pros put enable idempotence = true.  
Producers support message compression - at producer level, could be set on broker level (gzip, snappy, lz4 and more). Broker level is applied to all topics, topic level is applied per topic.  

Delivery semantics (consumers) - at most one - each message will be processed at most one, not twice but sometimes 0; at least once.  
We can create consumer idempotent (not to repeat ids or add new records).  

Consumer commit offset strategies - auto commit or manually commit. .poll() commits + auto.commit.interval configuration.  
commitSync() & commitAsync() are used for manual commits of the offsets.  
Consumer offset reset behavior -  latest, earlies, none config values. Offset.retention.minutes.  
Consumer internal threads - consumer heartbeat thread sends data to kafka 3s by default to tell if consumer is alive; Consumer poll thread - max time between to .poll() before declaring the consumer dead.
Consumer reads from the leader broker by default. Consumer rack awareness - for multiple data-centers.    

Kafka connect - used to connect to a source or produce in a soruce/sink. Source connectors - get data from common data sources. sink connectors - publish data to common data stores.  
Kafka streams (kafka streams application) - easy data processing and transform library with kafka. it is standalone java app.
Kafka schema registry - no data verification since data is in bytes (serialized). Schemas describes how the data look. Schema registry must be able to reject bad data before reaching kafka. Both producer and consumer talk with schema app.  
Validate request + avro data?  

Types of replications - active/active, active/pasive.  
Advertised listeners - client connect to host via advertised host not public IP.  

Partitions are made from segments (files). Segments come with two inexes (files).  
Log cleanup polices - how/when to expire data.  
Unclean leader election - config that count be enabled?  
Large messages - def max of 1MB per message.