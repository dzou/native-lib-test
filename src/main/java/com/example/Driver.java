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
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class Driver {

  public static void main(String[] args) throws Exception {

    /**
     * Print all the debug output since there's some nice error messages here which
     * hint at what is going wrong.
     */
    Logger root = Logger.getLogger("");
    root.setLevel(Level.FINEST);
    root.addHandler(new StreamHandler(System.out, new SimpleFormatter()));

    for (Handler handler : root.getHandlers()) {
      handler.setLevel(Level.FINEST);
    }

    root.info("Epoll.isAvailable(): " + Epoll.isAvailable());
    root.info("OpenSsl.isAvailable(): " + OpenSsl.isAvailable());

    final SelfSignedCertificate ssc = new SelfSignedCertificate("blah.net");
    final SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();

    root.info("SSL Context is " + sslCtx + ", class is " + sslCtx.getClass());
  }
}
