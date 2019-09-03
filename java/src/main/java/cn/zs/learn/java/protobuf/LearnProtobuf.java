package cn.zs.learn.java.protobuf;


import com.google.protobuf.InvalidProtocolBufferException;

public class LearnProtobuf {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        FirstProtobuf.testBuf.Builder builder = FirstProtobuf.testBuf.newBuilder();
        builder.setID(117);
        builder.setUrl("bangde");
        FirstProtobuf.testBuf build = builder.build();
        byte[] bytes = build.toByteArray();
        System.out.println("反序列化"+bytes.toString());
        FirstProtobuf.testBuf testBuf = FirstProtobuf.testBuf.parseFrom(bytes);
        System.out.println("序列化"+testBuf);


    }
}
