/**
 * 
 */
package com.main.article.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.article.repositories.FinArticlesRepository;
import com.main.article.services.AmazonClientService;
import com.main.article.services.FinArticleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ravirao1224
 *
 */
@RestController
@Slf4j
@RequestMapping("/nextgenfin/api")
@RequiredArgsConstructor
@EnableAsync
public class FinArticlesImageStorController {
	
    private AmazonClientService amazonClient;

    @Autowired
    FinArticlesImageStorController(AmazonClientService amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
	

}
