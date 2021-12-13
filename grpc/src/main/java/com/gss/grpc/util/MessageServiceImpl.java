package com.gss.grpc.util;

import com.gss.grpc.MessageRequest;
import com.gss.grpc.MessageResponse;
import com.gss.grpc.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;

public class MessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {

    @Override
    public void ping(MessageRequest request,
                     StreamObserver<MessageResponse> responseObserver) {
        System.out.println("request " + request.getMessage());
        MessageResponse response = MessageResponse.newBuilder()
                .setMessage("Pong")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
