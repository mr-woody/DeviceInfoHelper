package com.woodys.fingerprint;

public class TaskSocket {
    /*

    //AES加密
    public String encrypt(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = "QuantDataSKEY@#$".getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec("0101010101010101".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);// 此处使用BASE64做转码。
    }

    //发送消息
    private void sendMessage(OutputStream out, String content) {
        try {
            if(content != null || "".equals(content)){
                //RT作为响应报文开始的标识，方便服务端解析
                out.write("RT".getBytes("UTF-8"));
                out.write(encrypt(content).getBytes("UTF-8"));
                out.write("\n".getBytes("UTF-8"));
                out.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    //获取socket连接
    Socket socket = getSocketConnection();
    InputStream ins = null;
    OutputStream out = null;
    //连接建立或者重连后，需要拿到输入输出流，这里设置flag标志
    boolean flag = true;
while (true){
        try {
            if(flag) {
                ins = socket.getInputStream();
                out = socket.getOutputStream();
                flag = false;
            }
            byte[] bytes = new byte[2];
            //这里是读取服务端下发的命令，固定为两个字节
            ins.read(bytes, 0, 2);
            String command = new String(bytes, "utf-8");
            System.out.println(">>>>>>>>command"+command);

            //连接断开后，导致读取失败，需要重连
            if(command == null || isEmpty(bytes)){
                sleepMinis(1000);
                closeSocket(socket);
                socket = getSocketConnection();
                flag = true;
                continue;
            }

             //BS----基本信息
            if("BS".equals(command)){
                //getBaseInfo()获取基本信息方法
                sendMessage(out, getBaseInfo());
            } else if("RF".equals(command)){
                closeSocket(socket);
                break;
            } else {
                continue;
            }
            //再次判断连接状态
            if(socket.isOutputShutdown() || socket.isInputShutdown() || socket.isClosed() || !socket.isConnected()){
                closeSocket(socket);
                socket = getSocketConnection();
            }
        }catch (Exception e){
            System.out.println("管理通道异常"+e);
            sleepMinis(2000);
            closeSocket(socket);
            socket = getSocketConnection();
            flag = true;
        }
    }

    private void closeSocket(Socket socket) {
        try {
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    */
}
