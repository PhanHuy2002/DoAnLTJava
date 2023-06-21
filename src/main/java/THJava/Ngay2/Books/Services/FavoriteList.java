package THJava.Ngay2.Books.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import THJava.Ngay2.Books.Models.Book;
import THJava.Ngay2.Books.Models.FavoriteItem;

@Service
public class FavoriteList {
	 private List<FavoriteItem> favoriteItems = new ArrayList<>();
	 
	 public void addToFavoriteList(Book book) {
	        // Kiểm tra xem sách đã có trong danh sách yêu thích chưa
	        boolean isFavorite = checkIfBookIsFavorite(book);
	        // Nếu sách chưa có trong danh sách yêu thích, thêm sách vào danh sách yêu thích
	        if (!isFavorite) {
	            FavoriteItem favoriteItem = new FavoriteItem(book);
	            favoriteItems.add(favoriteItem);
	        }
	 }

	 public void removeFromFavoriteList(Book book) {
	        // Xóa sách khỏi danh sách yêu thích nếu tồn tại
	        favoriteItems.removeIf(item -> item.getBook().getId().equals(book.getId()));
	    }

	    public boolean checkIfBookIsFavorite(Book book) {
	        // Kiểm tra xem sách có trong danh sách yêu thích hay không
	        return favoriteItems.stream().anyMatch(item -> item.getBook().getId().equals(book.getId()));
	    }

		/**
		 * @return the favoriteItems
		 */
		public List<FavoriteItem> getFavoriteItems() {
			return favoriteItems;
		}

		/**
		 * @param favoriteItems the favoriteItems to set
		 */
		public void setFavoriteItems(List<FavoriteItem> favoriteItems) {
			this.favoriteItems = favoriteItems;
		}
	    
}
