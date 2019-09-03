package cn.zs.learn.bigdate.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {
	public static void main(String[] args) {

		String topicName = "test";
		Properties props = new Properties();
		props.put("bootstrap.servers", "10.11.100.146:9092,10.11.100.147:9092,10.11.100.148:9092,");
		props.put("group.id", "java");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		@SuppressWarnings("resource")
		Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		consumer.subscribe(Arrays.asList(topicName));

		System.out.println();

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d,key= %s,value= %s", record.offset(), record.key(), record.value());
			}
		}
	}
}
