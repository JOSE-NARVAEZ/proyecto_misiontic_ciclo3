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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;


/**
 * This class represents the Author as an entity model
 * @author casierrav
 */
@Entity
@Table(name="author")
public class Author implements Serializable {
    // Attributes
    @Id
    private Integer code;
    private String name;
    private String lastname;

    @ManyToMany(mappedBy="authors")
    private Set<Book> books;

    // ============ Getters and Setters ============ //
    /**
     * This method return the author code
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * This method returns the author name
     * @return author name
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets a new value for the author name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method return the author lastname
     * @return author lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method sets a new value for the author lastname
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method returns the books associated to the author
     * @return author books
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * This method sets a new value for the author books
     * @param books
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}