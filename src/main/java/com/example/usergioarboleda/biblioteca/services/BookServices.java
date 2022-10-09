/**
 * <p>Copyright: Copyright (c) 2022</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2022 by Carlos Andres Sierra Virguez. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="https://www.linkedin.com/in/casierrav/"> Carlos Andres Sierra </A>
 * (E-mail: <A HREF="mailto:carlos.sierra1.mt@usa.edu.co">carlos.sierra1.mt@usa.edu.co</A> )
 * @version 1.0
 */

package com.example.usergioarboleda.biblioteca.services;

import java.util.List;
import java.util.Optional;
import com.example.usergioarboleda.biblioteca.models.Book;
import com.example.usergioarboleda.biblioteca.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author casierrav
 */
@Service
public class BookServices {
    @Autowired
    private BookRepository bookRepository;

    /**
     *
     * @return
     */
    public List<Book> getAllBooks(){
        return bookRepository.getAll();
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public List<Book> getAllBooksByDates(int start, int end){
        if(end < start)
            return null;
        return bookRepository.getAllByDates(start, end);
    }

    /**
     *
     * @param isbn
     * @return
     */
    public Optional<Book> getBook(int isbn){
        return bookRepository.getBook(isbn);
    }

    /**
     *
     * @param book
     * @return
     */
    public Book insertBook(Book book){
        if(book.getIsbn() != null) {
            Optional<Book> temp = bookRepository.getBook( book.getIsbn() );
            if(temp.isEmpty())
                return bookRepository.save(book);
            else
                return book;
        }
        else
            return book;
    }

    /**
     *
     * @param book
     * @return
     */
    public Book updateBook(Book book){
        if(book.getIsbn() != null){
            Optional<Book> temp = bookRepository.getBook( book.getIsbn() );
            if( !temp.isEmpty() ){
                if(book.getTitle() != null)
                    temp.get().setTitle( book.getTitle() );
                if(book.getYear() != null)
                    temp.get().setYear( book.getYear() );
                if(book.getAuthors() != null)
                    temp.get().setAuthors( book.getAuthors() );
                if(book.getEditorialFK() != null)
                    temp.get().setEditorialFK( book.getEditorialFK() );
                return bookRepository.save(temp.get());
            }
            else
                return book;
        }
        else
            return book;
    }

    /**
     *
     * @param isbn
     */
    public boolean deleteBook(int isbn){
        Boolean success = getBook(isbn).map(book -> {
            bookRepository.delete(book);
            return true;
        }).orElse(false);
        return success;
    }
}