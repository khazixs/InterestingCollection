package com.ic.webCrawlers.service.impl;

import com.ic.webCrawlers.dao.StoryDao;
import com.ic.webCrawlers.entity.Story;
import com.ic.webCrawlers.service.WebCrawlerService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.xml.internal.bind.v2.TODO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

//@Service("webCrawlerService")
public class WebCrawlerServiceImpl implements WebCrawlerService {
    @Resource
    private StoryDao storyDao;

    public void processPage(String URL) throws IOException {
        //检查一下是否给定的URL已经在数据库中
        Story searchRes = storyDao.selectByUrl(URL);
        if (searchRes != null) {
            return;
        }

        //将uRL存储到数据库中避免下次重复
        storyDao.insertStory(new Story(URL));
        // TODO: 2020/6/6 也要添加作者

        //得到有用的信息
        Document doc = Jsoup.connect("http://www.mit.edu/").get();

        if (doc.text().contains("睡前故事")) {
            System.out.println(URL);
        }
        //得到所有的链接，并递归调用
        Elements questions = doc.select("a[href]");
        for (Element link : questions) {
            if (link.attr("href").contains("睡前故事"))
                processPage(link.attr("abs:href"));
        }
    }

    public static void main(String[] args) throws IOException {
        //要爬取的网站
        String url = "https://www.qidian.com/search?kw=LOL";
        //获得一个和网站的链接，注意是Jsoup的connect
        Connection connect = Jsoup.connect(url);
        //获得该网站的Document对象
        Document document = connect.get();
        int cnt = 1;
        //我们可以通过对Document对象的select方法获得具体的文本内容
        //下面的意思是获得.bool-img-text这个类下的 ul 下的 li
        Elements rootselect = document.select(".book-img-text ul li");
        for(Element ele : rootselect){
            //然后获得a标签里面具体的内容
            Elements novelname = ele.select(".book-mid-info h4 a");
            String name  = novelname.text();

            Elements author = ele.select(".book-mid-info p a");
            String authorName = author.first().text();

            Elements sumAdvice = ele.select(".total p");
            String sum = sumAdvice.last().text();

            System.out.println("书名:"+name+" 作者:"+authorName+" 推荐量:"+sum);
        }
    }
}
