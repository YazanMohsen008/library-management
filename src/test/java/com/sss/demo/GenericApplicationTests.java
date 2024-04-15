package com.sss.demo;


import com.maids.librarymanagementsystem.dao.BorrowingDao;
import com.maids.librarymanagementsystem.domain.Book;
import com.maids.librarymanagementsystem.domain.Borrowing;
import com.maids.librarymanagementsystem.domain.Patron;
import com.maids.librarymanagementsystem.service.BookService;
import com.maids.librarymanagementsystem.service.BorrowingService;
import com.maids.librarymanagementsystem.service.PatronService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class GenericApplicationTests {

    @MockBean
    BookService bookService;
    @MockBean
    PatronService patronService;
    @MockBean
    BorrowingDao borrowingDao;
    BorrowingService borrowingService= new BorrowingService(bookService,patronService,borrowingDao);


    @Test
   public void borrowReservedBook(){
        Borrowing borrowing = new Borrowing (new Book(),new Patron(),"Borrowing");
        assertThat(borrowingService.validateBorrowing(borrowing)).isEqualTo(false);
    }
}
