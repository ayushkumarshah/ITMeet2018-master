package np.edu.ku.itmeet18;

/**
 * Created by ayush on 12/14/17.
 */

public class News {
    private  String content,date,title,link;
    public News(String title, String date, String content,String link){
        this.content=content;
        this.date=date;
        this.title=title;
        this.link=link;


    }
    public  String getTitle()
    {
        return  title;
    }
    public  String getDate()
    {
        return  date;
    }
    public String getContent()
    {
        return  content;
    }
    public String getLink()
    {
        return  link;
    }

}
