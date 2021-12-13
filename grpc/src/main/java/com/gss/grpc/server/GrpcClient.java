package com.gss.grpc.server;

import com.gss.grpc.MessageRequest;
import com.gss.grpc.MessageResponse;
import com.gss.grpc.MessageServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import static com.gss.grpc.util.Constant.HOST;
import static com.gss.grpc.util.Constant.PORT;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext()
                .build();
        MessageServiceGrpc.MessageServiceBlockingStub stub =
                MessageServiceGrpc.newBlockingStub(channel);
        MessageResponse messageResponse = stub.ping(MessageRequest.newBuilder()
                .setMessage("Ping")
                .build());
        System.out.println("response " + messageResponse.getMessage());
        channel.shutdown();
    }
}
