/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package com.cccvip.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

/**
 * NioServer.
 *
 * @author Carl, 2023-06-08 14:00
 * @version CrisisGo v1.0
 */
public class NioServer {

    private ByteBuffer readBuffer;
    private Selector selector;

    //编码器
    private static CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();
    //解码器
    private static CharsetDecoder decoder = Charset.forName("utf-8").newDecoder();

    //初始化
    private void init() {
        //设置缓冲区
        readBuffer = ByteBuffer.allocate(1024);
        ServerSocketChannel serverSocketChannel;

        try {
            serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(8383));
            //注册事件 可以注册多个事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//            int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT;
//            servSocketChannel.register(selector, interestSet);
//            servSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //开启监听事件
    private void listen() {
        while (true) {
            try {
                selector.select();
                Iterator ite = selector.selectedKeys().iterator();

                while (ite.hasNext()) {
                    SelectionKey key = (SelectionKey) ite.next();
                    ite.remove();//确保不重复处理

                    handleKey(key);
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    //根据监听事件key 处理 value
    private void handleKey(SelectionKey key)
            throws IOException, ClosedChannelException {
        SocketChannel channel = null;

        try {
            if (key.isAcceptable()) {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                channel = serverChannel.accept();//接受连接请求
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                channel = (SocketChannel) key.channel();
                readBuffer.clear();
                /*当客户端channel关闭后，会不断收到read事件，但没有消息，即read方法返回-1
                 * 所以这时服务器端也需要关闭channel，避免无限无效的处理*/
                int count = channel.read(readBuffer);

                if (count > 0) {
                    //一定需要调用flip函数，否则读取错误数据
                    readBuffer.flip();
                    /*使用CharBuffer配合取出正确的数据
                    String question = new String(readBuffer.array());
                    可能会出错，因为前面readBuffer.clear();并未真正清理数据
                    只是重置缓冲区的position, limit, mark，
                    而readBuffer.array()会返回整个缓冲区的内容。
                    decode方法只取readBuffer的position到limit数据。
                    例如，上一次读取到缓冲区的是"where", clear后position为0，limit为 1024，
                    再次读取“bye"到缓冲区后，position为3，limit不变，
                    flip后position为0，limit为3，前三个字符被覆盖了，但"re"还存在缓冲区中，
                    所以 new String(readBuffer.array()) 返回 "byere",
                    而decode(readBuffer)返回"bye"。
                    */
                    CharBuffer charBuffer = decoder.decode(readBuffer);
                    String question = charBuffer.toString();
                    System.out.println("receiver client message:" + question);
                    String answer = "the message come from this server";
                    channel.write(encoder.encode(CharBuffer.wrap(answer)));
                } else {
                    //这里关闭channel，因为客户端已经关闭channel或者异常了
                    channel.close();
                }
            } else if (key.isWritable()) {
                channel = (SocketChannel) key.channel();

                String answer = "the message2 come from this server2";
                channel.write(encoder.encode(CharBuffer.wrap(answer)));
            } else if (key.isConnectable()) {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                channel = serverChannel.accept();//接受连接请求
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_WRITE);
            }
        } catch (Throwable t) {
            t.printStackTrace();
            if (channel != null) {
                channel.close();
            }
        }
    }

    public static void main(String[] args) {

        NioServer server = new NioServer();

        server.init();

        server.listen();
    }

}
