package com.baixiaowen.alllearning.service.impl;

import com.baixiaowen.alllearning.exception.BusinessException;
import com.baixiaowen.alllearning.exception.ErrorCodeEnum;
import com.baixiaowen.alllearning.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 文件上传服务实现类 -- 本地文件上传服务
 */
@Slf4j
// 系统可能会指定文件上传服务的多个实现类，所以这里注入的时候需要指定每个文件上传服务实现类的名称
@Service("localFileServiceImpl")
public class LocalFileServiceImpl implements FileService {

    /**
     * 存储空间
     */
    private static final String BUCKET = "uploads";

    @Override
    public void upload(InputStream inputStream, String filename) {
        // 拼接文件的存储路径
        String storagePath = BUCKET + File.separator + filename;

        try(
                // JDK8 TWR 不能关闭外部资源的。这里的inputStream就是一个外部资源，是通过参数传递过来的，
                // JDK8 TWR 可以关闭内部资源，通过关闭内部资源达到关闭外部资源的目的
                InputStream innerInputStream = inputStream;

                FileOutputStream outputStream = new FileOutputStream(new File(storagePath))
        ) {
            // 拷贝缓冲区
            byte[] buffer = new byte[1024];
            // 读取文件流长度
            int len;

            // 循环读取innerInputStream中数据写入到outputStream
            while((len = innerInputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, len);
            }

            // 冲刷流
            outputStream.flush();

        } catch (Exception e){
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }

    @Override
    public void upload(File file) {
        try {
            upload(new FileInputStream(file), file.getName());
        } catch (Exception e){
            log.error("文件上传失败", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }
}
