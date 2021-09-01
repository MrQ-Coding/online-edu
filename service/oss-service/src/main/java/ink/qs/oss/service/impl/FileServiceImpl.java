package ink.qs.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CannedUdfAcl;
import com.aliyun.oss.model.SetBucketAclRequest;
import ink.qs.excp.EduGlobalException;
import ink.qs.oss.service.FileService;
import ink.qs.oss.utils.OSSClientUtil;
import ink.qs.oss.utils.OSSPropertiesUtil;
import ink.qs.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private OSSClientUtil ossClientUtil;

    @Override
    public String upload(MultipartFile file) {
        OSS ossClient = ossClientUtil.createClient();
        String bucketName = OSSPropertiesUtil.BUCKET_NAME;
        String fileHost =  OSSPropertiesUtil.FILE_HOST;



        String uploadUrl = null;
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            String fileUrl = fileHost + "/" + filePath + "/" + newName;

            //文件上传至阿里云
            ossClient.putObject(bucketName, fileUrl, inputStream);

            //获取url地址
            uploadUrl = "https://" + bucketName + "." + OSSPropertiesUtil.END_POINT + "/" + fileUrl;
        } catch (Exception e) {
            try {
                throw new EduGlobalException(ResultCodeEnum.FILE_UPLOAD_ERROR);
            } catch (EduGlobalException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 关闭OSSClient。
            ossClientUtil.shutClient(ossClient);
        }
        return uploadUrl;
    }
}
