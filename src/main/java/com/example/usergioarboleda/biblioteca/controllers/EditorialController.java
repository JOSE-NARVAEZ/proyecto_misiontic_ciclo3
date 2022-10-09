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

package com.example.usergioarboleda.biblioteca.controllers;

import java.util.List;
import java.util.Optional;
import com.example.usergioarboleda.biblioteca.models.Editorial;
import com.example.usergioarboleda.biblioteca.services.EditorialServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/editorial")
public class EditorialController {
    @Autowired
    private EditorialServices editorialServices;

    @GetMapping("/all")
    public List<Editorial> getAllEditorials(){
        return editorialServices.getAllEditorials();
    }

    @GetMapping("/all/{country}")
    public List<Editorial> getAllEditorialsByCountry(@PathVariable("country") String country){
        return editorialServices.getAllEditorialsByCountry(country);
    }

    @GetMapping("/id/{id}")
    public Optional<Editorial> getEditorial(@PathVariable("id") int editorialId){
        return editorialServices.getEditorial(editorialId);
    }

    @GetMapping("/name/{name}")
    public Optional<Editorial> getEditorialByName(@PathVariable("name") String name){
        return editorialServices.getEditorialByName(name);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Editorial insert(@RequestBody Editorial editorial){
        return editorialServices.insertEditorial(editorial);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Editorial update(@RequestBody Editorial editorial){
        return editorialServices.updateEditorial(editorial);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return editorialServices.deleteEditorial(id);
    }
}