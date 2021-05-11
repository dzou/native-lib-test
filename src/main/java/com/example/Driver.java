package com.example;

import io.grpc.netty.shaded.io.netty.channel.epoll.Epoll;
import io.grpc.netty.shaded.io.netty.handler.ssl.OpenSsl;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.SelfSignedCertificate;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver {

  public static void main(String[] args) throws Exception {

    /**
     * The first step is we need to figure out how to enable debug logging...
     * still dunno how to do that yet; this code below doesn't work.
     */
    Logger root = Logger.getLogger(OpenSsl.class.getCanonicalName());
    root.setLevel(Level.FINEST);
    for (Handler handler : root.getHandlers()) {
      handler.setLevel(Level.FINEST);
    }

    System.out.println("Epoll.isAvailable(): " + Epoll.isAvailable());
    System.out.println("OpenSsl.isAvailable(): " + OpenSsl.isAvailable());

    final SelfSignedCertificate ssc = new SelfSignedCertificate("blah.net");
    final SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();

    System.out.println("SSL Context is " + sslCtx + ", class is " + sslCtx.getClass());
  }

}
