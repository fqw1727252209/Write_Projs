package com.study.platform.service;

import com.study.platform.entity.StudyResource;

import java.util.List;

/**
 * 学习资源Service接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StudyResourceService {

    void createResource(StudyResource studyResource);

    List<StudyResource> getResourceList(Long userId, Long courseId, Integer resourceType);

    StudyResource getResourceById(Long id);

    void updateResource(StudyResource studyResource);

    void deleteResource(Long id);

    void incrementDownload(Long id);

}
