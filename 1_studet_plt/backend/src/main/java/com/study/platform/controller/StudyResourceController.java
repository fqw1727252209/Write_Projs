package com.study.platform.controller;

import com.study.platform.common.Result;
import com.study.platform.entity.StudyResource;
import com.study.platform.service.StudyResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 学习资源Controller
 * 
 * @author Student
 * @date 2024-11-25
 */
@RestController
@RequestMapping("/resource")
public class StudyResourceController {

    @Autowired
    private StudyResourceService studyResourceService;

    @Value("${file.upload-path}")
    private String uploadPath;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public Result<StudyResource> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam Long userId,
            @RequestParam(required = false) Long courseId,
            @RequestParam String resourceName,
            @RequestParam Integer resourceType) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }

        try {
            // 创建上传目录
            String datePath = new SimpleDateFormat("yyyy/MM").format(new Date());
            String dirPath = uploadPath + datePath;
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + suffix;
            String filePath = dirPath + File.separator + fileName;

            // 保存文件
            file.transferTo(new File(filePath));

            // 创建资源记录
            StudyResource resource = new StudyResource();
            resource.setUserId(userId);
            resource.setCourseId(courseId);
            resource.setResourceName(resourceName);
            resource.setFileName(originalFilename);
            resource.setFilePath(filePath);
            resource.setFileType(suffix.substring(1));
            resource.setFileSize(file.getSize() / 1024); // 转换为KB
            resource.setResourceType(resourceType);

            studyResourceService.createResource(resource);

            return Result.success("上传成功", resource);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败");
        }
    }

    @GetMapping("/list")
    public Result<List<StudyResource>> getResourceList(@RequestParam Long userId,
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) Integer resourceType) {
        List<StudyResource> list = studyResourceService.getResourceList(userId, courseId, resourceType);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<StudyResource> getResourceById(@PathVariable Long id) {
        StudyResource resource = studyResourceService.getResourceById(id);
        return Result.success(resource);
    }

    @PutMapping("/update")
    public Result<String> updateResource(@RequestBody StudyResource studyResource) {
        studyResourceService.updateResource(studyResource);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteResource(@PathVariable Long id) {
        studyResourceService.deleteResource(id);
        return Result.success("删除成功");
    }

    @PostMapping("/download/{id}")
    public Result<String> downloadResource(@PathVariable Long id) {
        studyResourceService.incrementDownload(id);
        return Result.success("下载次数已更新");
    }

}
