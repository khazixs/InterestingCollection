package com.ic.webCrawlers.dao;

import com.ic.webCrawlers.entity.Story;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("storyDao")
public interface StoryDao {
     Story selectByAuthor(@Param("author") String author);
     Story selectByUrl(@Param("url") String url);
     Integer insertStory(@Param("story") Story story);
}
