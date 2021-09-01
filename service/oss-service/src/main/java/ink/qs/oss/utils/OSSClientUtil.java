package ink.qs.oss.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;

/**
 * OSSClient创建类
 */
@Component
public class OSSClientUtil {

    public static OSS createClient() {
        // https://help.aliyun.com/document_detail/32010.html 官方文档
        String endpoint = OSSPropertiesUtil.END_POINT;
        String accessKeyId = OSSPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = OSSPropertiesUtil.ACCESS_KEY_SECRET;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        return ossClient;
    }

    public static void shutClient(OSS oss) {
        //TODO 日志插件
        oss.shutdown();
    }
}
