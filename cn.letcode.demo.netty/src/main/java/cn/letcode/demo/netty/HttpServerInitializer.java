package cn.letcode.demo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		ChannelPipeline p = socketChannel.pipeline();
		/**
		 * http-request解码器 http服务器端对request解码
		 */
		// p.addLast(new HttpRequestDecoder());
		/**
		 * http-response解码器 http服务器端对response编码
		 */
		// p.addLast(new HttpResponseEncoder());
		/**
		 * 压缩 Compresses an HttpMessage and an HttpContent in gzip or deflate
		 * encoding while respecting the "Accept-Encoding" header. If there is
		 * no matching encoding, no compression is done.
		 */
		// p.addLast("deflater", new HttpContentCompressor());
		// p.addLast(new JsonObjectDecoder());

		p.addLast(new HttpServerCodec());
		// HttpObjectAggregator会把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse
		p.addLast(new HttpObjectAggregator(65536));

		p.addLast(new HttpServerHandler());

	}

}