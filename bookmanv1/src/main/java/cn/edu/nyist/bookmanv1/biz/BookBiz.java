package cn.edu.nyist.bookmanv1.biz;

import java.util.List;

import cn.edu.nyist.bookmanv1.vo.BookVo;
import cn.edu.nyist.bookmanv1.vo.TypeVo;
public interface BookBiz {
	/**
	 * 
	 * @param bookVo
	 *            Ҫ�����bookֵ����
	 * @return �ɹ�����0�����򷵻�1
	 */
	int saveBook(BookVo bookVo);

	/**
	 * 
	 * @param pageNo
	 *            Ҫ�鿴��ҳ��
	 * @param tid
	 * @param name
	 * @return ��ǰҳ���鼮�б�
	 */
	List<BookVo> findAllBooks(int pageNo, String name, int tid);

	/**
	 * 
	 * @param tid
	 * @param name
	 * @return ����������
	 */
	int findTotal(String name, int tid);

	List<TypeVo> findAllTypes();

	/**
	 * 
	 * @param id
	 * @return ����idɾ��
	 */
	boolean delById(int id);

	/**
	 * 
	 * @param id
	 * @return ����id��Book
	 */
	BookVo findBookById(int id);

	/**
	 * 
	 * @param bookVo
	 * @return �����鼮
	 */
	int editBook(BookVo bookVo);

}