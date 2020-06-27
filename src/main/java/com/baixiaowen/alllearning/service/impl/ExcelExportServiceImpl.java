package com.baixiaowen.alllearning.service.impl;

import com.baixiaowen.alllearning.domain.dto.UserQueryDTO;
import com.baixiaowen.alllearning.service.ExcelExportService;
import com.baixiaowen.alllearning.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.OutputStream;

/**
 * Excel导出服务实现类
 */
@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    /**
     * 执行数据查询和Excel导出
     * @param outputStream
     * @param query
     */
    private void export(OutputStream outputStream, UserQueryDTO query){

    }

    @Override
    public void export(UserQueryDTO query, String filename) {

        // step1. 实现数据导出的Excel中
        outputStream

        // step2. 实现文件上传
        fileService.upload(inputStream, filename);
    }
}