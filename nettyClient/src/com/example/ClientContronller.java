package com.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientContronller {
    @GetMapping("/sendMsg")
    public void sendMsg(String mes){
        try {
            new EchoClient("127.0.0.1", 8090,
                    Unpooled.copiedBuffer(mes, CharsetUtil.UTF_8)).start();
        }catch (Exception e){
            System.out.println();
        }
    }
}
