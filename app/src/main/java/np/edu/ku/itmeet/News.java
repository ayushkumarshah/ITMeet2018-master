package np.edu.ku.itmeet;

/**
 * Created by ayush on 12/14/17.
 */

public class News {
    private  String content,date,title;
    public News(String title, String date, String content){
        this.content=content;
        this.date=date;
        this.title=title;

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

}
