package THJava.Ngay2.Books.Models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FavoriteItem {
	private Book book;
	private Long id;
	private String title;
	private String author;
	private String story;
	private String photourl;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the story
	 */
	public String getStory() {
		return story;
	}
	/**
	 * @param story the story to set
	 */
	public void setStory(String story) {
		this.story = story;
	}
	/**
	 * @return the photourl
	 */
	public String getPhotourl() {
		return photourl;
	}
	/**
	 * @param photourl the photourl to set
	 */
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public FavoriteItem() {}
	public FavoriteItem(Long id, String title, String author, String story, String photourl) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.story = story;
		this.photourl = photourl;
	}
	 public FavoriteItem(Book book) {
	        this.book = book;
	 }
	 public Book getBook() {
	        return book;
	    }

	    public void setBook(Book book) {
	        this.book = book;
	    }
}
