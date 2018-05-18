package cn.edu.nyist.bookmanv1.biz;



import cn.edu.nyist.bookmanv1.vo.BookVo;

public interface BookBiz {
     //int saveBook(String name,String descri,double price,String author,int tid,String newFileName, Date pubDate);
     int saveBook(BookVo bookVo);
}
