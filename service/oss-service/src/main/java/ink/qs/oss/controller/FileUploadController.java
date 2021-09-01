package ink.qs.oss.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import ink.qs.oss.service.FileService;
import ink.qs.utils.Re;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api("阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/oss/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping(value = "upload",headers = "content-type=multipart/form-data")
    public Re uploadFile(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String fileUrl = fileService.upload(file);
        if (ObjectUtils.isNotEmpty(fileUrl)) {
            return Re.ok().data("fileUrl",fileUrl);
        }
        return Re.error().message("文件上传失败");
    }
}
