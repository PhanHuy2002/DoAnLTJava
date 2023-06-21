package THJava.Ngay2.Books.Models;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title", nullable = false, length = 255)
	private String title;
	@Column(name = "author", nullable = false, length = 255)
	private String author;
	@Column(name= "story",nullable = true,length = 500)
	private String story;
	@Column(nullable = true, length = 255)
	private String photourl;
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	@Column(name="isdeleted",columnDefinition = "boolean default false")
	private boolean isdeleted;
	
	
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getphotourl() {
		return photourl;
	}

	public void setphotourl(String photourl) {
		this.photourl = photourl;
	}
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Transient
	public String getPhotosImagePath() {
		if (photourl == null || id == null)
			return null;

		return "/photos/" + id + "/" + photourl;
	}

}
