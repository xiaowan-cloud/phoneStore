package com.scut.mall.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092000556120";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCMIAp+8IWo2zdkHOy/VAWlTD8LY6EHMWMuST8xBqHXu8aW3SndBw/MCwGI4D2bXqA73tVu6QWGPSeZfBtfHCQu72srsNC+ahL4Uo+T/PAhupWpwluvPu4Z5tbsdZHG5OQZ1fhhWld6Pn7Ltp08Ow2Gvt4kTHVc1EdEYlZNY1cpe9b5jSx0kKD3PHFyUVlYHuHV4/0+pz4n09YpQ9ILPUyjlmQ3Sv7mERtVyYtGbuneoFqFXHQVDOFvB5+/mUWwZ9fIJI2fIt/i0RhIzYLgQQSERvsULROd+aNx/ZoR0eBDOMub/4iRD62Ga8QdNMDdA0jRZNz46quGAbLJwF6ivyxpAgMBAAECggEBAIcujhGihr+9JLXvdbKrI3sxkIyzQ/FFgxJvVYg9hrsyi0aV/MIgAuxtHcDn3Gdcpaa8thl2sn6r1t0qrtlVs86X6UNQJEHe4YgxVhSF60AO2j3YpEzvoBLa8TNTxRwlS61lEGcTGdt0GSz8crpdmhIJZxOLs5mnrU1atTmZgcghH1+P7tCrDRGGYyWvDGqroHqUgcx0KX6NNOvpsU6F9iw1NE5MNnlP6zC36DenDkzL2Fz7jksFhhupYLAmSLxezjqJTBlmpDHN42SBo9OI0RXLY76YGryVuB4gpwP/fK2BAj4BhAv9RRoD6XhYL/wByoiX4MynqAF2FhZ2qdAMlBECgYEA37/IHkOBWFQVftH0xkOCmv27QVKRboR4bAOld1KzwFDWe/t9O/RWuOpSThXtgenOhA572bZsOOV4snC0mhtDVBuUdK68dcXHVEEkW4zHnJ+ZfOnORyaGvWh8rFfE0iXpWJE8Dip/2aJLKNnshy+KqJaEUBSrqNb/TMZd/iSof70CgYEAoFKUuo1rLGvcW5r4uJFM2a+KDyFE9lcEkrnRmA8KKrZqPmg4MwX6z4OpkqJIO+QtdWXLeVsFR8GzQOPjvUthFd79zrF1e4ruvNdfM2VBRpE4oKSgmzQK8ejVOpdHqYETYQUOMM2rS8P9eqvPK4qqqFe6l9iac3055XZckMEMxB0CgYEApr9dWziBx5r6XgYIfk1Ho0xe5iKroYp12EGrac6niTZ1cfaNcUX571sptWngFAZz0rSclja+VnSlS450+bhBqAfZzk9+weKAV4komFXFWTMUBySThzDuD3A0IZhTZS29xjSOZhxEJaQukSY/gMvscRGjT67n8y8vGj9nQwR80p0CgYA7w+07yiJM0fy4jYHw1AGxGNY/o6hgqLnEtM6kf97LY2shzpo8sxr3pt5dEnAZG4uyuvrqR3slb9kR9KJeW65ah+FX3yGrp3QuI5+SuklmNBbHE/fnQ7Rcz+5FREXrs3l07nyGxvT3bp2c0FWPpsbNsL6XgqC5MtKVbtU9BSpsJQKBgQCaBPLnm4l1Yw2Lw5O9ECkDd3flDxXxPBAI1tZZPtXpf+BUisfOTp4HIvgX5cpL0b4Q/8CGkvN9U1EsGkOS4k1Nwrd7+p0WgkhE8DhvtOhAa80NgNqkzES4i+RIVmUyhrlK69LZmOg8Kta5b80Y4jQsrmgnMwZT4uWebcAcyPDnXw==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtr9q+FgX+af+kraC0Pm47fwjsN0d4eGshkwQtxihvGD1GLP82kaPgaPIXrPPILwnk3TfEq5dh8np47wVPe1EYOMzmRRQ09nUyw7tCZyWrD38+Of4YlsxqJHsLAU3sOY8MI5tbXQ9FwKXD3KV0kmFbYLNs0sOFA3HcNikJRGH4BQk3FoMkg7LHBKCxWfGcOIqkrs92dOYy1pBR6VwkMjhbokVAtAecIUDGXxPeLYhyJGtoA0jmPZq8qd44t8dSxqYeTKvEnsyNoohsqWuQKLtdLVi9Iw/GF25pZtuAuhFyvnKrLrV7hxoAb/Q+/tX2facouNcjWGdvSXThVU4EnLV6QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//这里地址为，支付成功之后跳转的地址，异步地址
	//public static String notify_url = "";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//这里地址为，支付成功之后跳转的地址，同步
	public static String return_url = "http://localhost:8080/mall/order/payreturn";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

