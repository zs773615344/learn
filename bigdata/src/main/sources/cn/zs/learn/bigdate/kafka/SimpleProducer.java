package cn.zs.learn.bigdate.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {
	public static void main(String[] args) {
		String topicName = "test";
		Properties props = new Properties();
		props.put("bootstrap.servers", "10.11.100.146:9092,10.11.100.147:9092,10.11.100.148:9092,");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("acks", "all");
		props.put("retries", 10);
		props.put("batch.size", 1000);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 102400);

		// props.put("metadata.broker.list",
		// "10.11.100.146:9092,10.11.100.147:9092,10.11.100.148:9092,");
		// props.put("request.required.acks", "1");
		// props.put("producer.type", "sync");
		// props.put("compression.codec", "none");
		// props.put("serializer.class", "kafka.serializer.DefaultEncoder");
		// props.put("zookeeper.connect","10.11.100.130:2181,10.11.100.131:2181,10.11.100.132:2181");
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		System.out.println(producer.metrics());
		System.out.println(producer.partitionsFor(topicName));

		for (int i = 0; i < 100; i++)
			producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i)));

		producer.flush();
		producer.close();
		System.out.println("Message sent successfully");

	}
}
