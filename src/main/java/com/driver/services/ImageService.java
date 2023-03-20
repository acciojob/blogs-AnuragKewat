package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        List<Image> imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        Image image = imageRepository2.findById(id).get();
        Blog blog = image.getBlog();
        for(Image image1: blog.getImageList()) {
            if(image1.equals(image)) {
                blog.getImageList().remove(image1);
                break;
            }
        }
        blogRepository2.save(blog);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Blog blog = blogRepository2.findById(id).get();
        int count = 0;
        for(Image image: blog.getImageList()) {
            if(image.getDimensions().equals(screenDimensions)) {
                count++;
            }
        }
        return count;
    }
}
