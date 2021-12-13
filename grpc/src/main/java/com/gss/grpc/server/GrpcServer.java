package com.gss.grpc.server;

import com.gss.grpc.util.MessageServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

import static com.gss.grpc.util.Constant.PORT;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(PORT)
                .addService(new MessageServiceImpl())
                .build();
        server.start();
        server.awaitTermination();
    }
}
