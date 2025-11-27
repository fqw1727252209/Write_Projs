package com.study.platform.service.impl;

import com.study.platform.entity.StudyResource;
import com.study.platform.mapper.StudyResourceMapper;
import com.study.platform.service.StudyResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学习资源Service实现类
 * 
 * @author Student
 * @date 2024-11-25
 */
@Service
public class StudyResourceServiceImpl implements StudyResourceService {

    @Autowired
    private StudyResourceMapper studyResourceMapper;

    @Override
    public void createResource(StudyResource studyResource) {
        if (studyResource.getDownloadCount() == null) {
            studyResource.setDownloadCount(0);
        }

        int result = studyResourceMapper.insert(studyResource);
        if (result == 0) {
            throw new RuntimeException("创建学习资源失败");
        }
    }

    @Override
    public List<StudyResource> getResourceList(Long userId, Long courseId, Integer resourceType) {
        if (courseId != null) {
            return studyResourceMapper.selectByCourseId(courseId);
        } else if (resourceType != null) {
            return studyResourceMapper.selectByUserIdAndType(userId, resourceType);
        } else {
            return studyResourceMapper.selectByUserId(userId);
        }
    }

    @Override
    public StudyResource getResourceById(Long id) {
        return studyResourceMapper.selectById(id);
    }

    @Override
    public void updateResource(StudyResource studyResource) {
        int result = studyResourceMapper.update(studyResource);
        if (result == 0) {
            throw new RuntimeException("更新学习资源失败");
        }
    }

    @Override
    public void deleteResource(Long id) {
        int result = studyResourceMapper.deleteById(id);
        if (result == 0) {
            throw new RuntimeException("删除学习资源失败");
        }
    }

    @Override
    public void incrementDownload(Long id) {
        studyResourceMapper.incrementDownloadCount(id);
    }

}
