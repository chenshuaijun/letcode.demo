package cn.letcode.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.CharsetUtil;

/**
 * netty http server handler data deal
 * 
 * @author chenshuaijun
 *
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			FullHttpRequest req = (FullHttpRequest) msg;
			req.protocolVersion();

			String contentType = req.headers().get(HttpHeaderNames.CONTENT_TYPE);

			// TODO check content_type swich doing
			if (HttpHeaderValues.APPLICATION_JSON.contentEquals(contentType)) {
				System.out.println("true===true");
			}

			ByteBuf bb = req.content();
			String content = bb.toString(CharsetUtil.UTF_8);
			System.out.println(content);

			boolean keepAlive = HttpUtil.isKeepAlive(req);
			// http version from http request
			FullHttpResponse rep = new DefaultFullHttpResponse(req.protocolVersion(), HttpResponseStatus.OK,
					Unpooled.wrappedBuffer(content.getBytes(CharsetUtil.UTF_8)));

			req.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);
			rep.headers().set(HttpHeaderNames.CONTENT_LENGTH, rep.content().readableBytes());
			if (!keepAlive) {
				ctx.write(rep).addListener(ChannelFutureListener.CLOSE);
			} else {
				rep.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
				ctx.write(rep);
			}
		}else{  // http request method get or other go this
			FullHttpRequest req = (FullHttpRequest) msg;
			String contentType = req.headers().get(HttpHeaderNames.CONTENT_TYPE);
			FullHttpResponse rep = new DefaultFullHttpResponse(req.protocolVersion(), HttpResponseStatus.OK,
					Unpooled.wrappedBuffer("server is ok !!".getBytes(CharsetUtil.UTF_8)));

			req.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);
			rep.headers().set(HttpHeaderNames.CONTENT_LENGTH, rep.content().readableBytes());
		}
	}
}