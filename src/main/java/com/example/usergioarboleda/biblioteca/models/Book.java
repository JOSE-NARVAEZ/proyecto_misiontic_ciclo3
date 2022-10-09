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

package com.example.usergioarboleda.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * This class represents the Book as an entity model
 * @author casierrav
 */
@Entity
@Table(name="book")
public class Book implements Serializable {
    //Attributes
    @Id
    private Integer isbn;
    private String title;
    private String registerDate;
    private Integer year;

    //Make relation using foreign key into book
    @ManyToOne
    @JoinColumn(name="id")
    @JsonIgnoreProperties("books")
    private Editorial editorialFK;

    //Make main definition for n to n relation between book and author
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns        = @JoinColumn(name="isbn"), // Book PK
            inverseJoinColumns = @JoinColumn(name="code")) // Author PK
    private Set<Author> authors;


    // ============ Getters and Setters ============ //
    /**
     * This method returns the book isbn
     * @return book isbn
     */
    public Integer getIsbn() {
        return isbn;
    }

    /**
     * This method returns the book title
     * @return book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method sets a new value for the book title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method returns the book register date
     * @return book register date
     */
    public String getRegisterDate() {
        return registerDate;
    }

    /**
     * This method sets a new value for the book register date
     * @param registerDate
     */
    public void setRegister_date(String registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * This method returns the book year
     * @return book year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * This method sets a new value for the book year
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * This method returns the book editorial
     * @return book editorial
     */
    public Editorial getEditorialFK() {
        return editorialFK;
    }

    /**
     * This method sets a new value for the book editorial
     * @param editorialFK
     */
    public void setEditorialFK(Editorial editorialFK) {
        this.editorialFK = editorialFK;
    }

    /**
     * This method returns the book authors
     * @return book authors
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    /**
     * This method sets a new value for the book authors
     * @param authors
     */
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}