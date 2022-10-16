// Variables globales
let URL_BASE = "http://localhost:8080";
let book = null;

//Ejecuta cuando se carga la pagina
$(document).ready(function (){
    getAllBooks();
    $("#modalNewBook").hide();
});

function loadEditorials(value){
    let editorials = getAllEditorials();
    $("#cbxEditorial").empty();
    for(let i = 0; i < editorials.length; i++)
        $("#cbxEditorial").append( new Option(editorials[i].name, editorials[i].idEditorial) );
    if(value!=-1)
        $("#cbxEditorial").val(value);
}

function loadYears(value){
    $("#cbxYear").empty();
    for(let i = 2022; i >=1900; i--)
        $("#cbxYear").append( new Option(i, i) );
    if(value!=-1)
        $("#cbxYear").val(value);
}

//Acciones con el Modal
function openModal(isbn){
    if(isbn==-1){ // Activar para agregar
        $("#btnAdd").show();
        $("#btnUpdate").hide();
        $("#txtIsbn").val();
        $("#txtTitle").val("");
        loadEditorials(-1);
        loadYears(-1);
    }
    else { // Activar para actualizar
        $("#btnAdd").hide();
        $("#btnUpdate").show();
        book = getBook(isbn);
        $("#txtIsbn").val(book.isbn);
        $("#txtTitle").val(book.title);
        loadEditorials(book.editorialFK.idEditorial);
        loadYears(book.year);
    }
    $("#modalNewBook").show();
}

function closeModal(){
    $("#modalNewBook").hide();
}

// Actualizar la tabla de datos
function updateDataGrid(books){
    $("#tblBooks").find("tr:gt(0)").remove();
    let data = "";
    for(let i = 0; i < books.length; i++){
        data += "<tr>";
            data += "<td>" + books[i].isbn + "</td>";
            data += "<td>" + books[i].title + "</td>";
            data += "<td>" + books[i].year + "</td>";
            data += "<td>" + books[i].editorialFK.name + "</td>";
            data += "<td><span onclick=\"openModal(" + books[i].isbn + ")\">A</span></td>";
            data += "<td><span onclick=\"deleteBook(" + books[i].isbn + ")\">E</span></td>";
        data += "</tr>";
    }
    $("#tblBooks > tbody:last-child").append(data);
}

// Llamado a backend para CRUD
function getAllBooks(){
    $.ajax({
        url: URL_BASE + "/book/all",
        type: "GET",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        updateDataGrid( response );
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in getAllBooks. " + textStatus)
        alert("Falla obteniendo todos los libros");
    });
}

function getBook(isbn){
    $.ajax({
        async: false,
        url: URL_BASE + "/book/isbn/"+isbn,
        type: "GET",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        book = response;
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in getBook with isbn "+isbn+". "+textStatus);
    });
    return book;
}

function insertBook(){
    const current = new Date();
    book = {
        isbn: $("#txtIsbn").val(),
        title: $("#txtTitle").val(),
        year: $("#cbxYear").val(),
        editorialFK: getEditorial( $("#cbxEditorial").val() ),
        registerDate: current.toISOString()
    }

    let body = JSON.stringify(book);
    console.log("Book\n"+body);

    $.ajax({
        url: URL_BASE + "/book/insert",
        type: "POST",
        datatype: "JSON",
        data: body,
        contentType: "application/json;charset=UTF-8"
    })
    .done( function(response){
        console.log( response );
        alert("Libro agregado");
        getAllBooks();
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in insertBook. "+textStatus);
        alert("Falla creando el nuevo libro");
    });
    closeModal();
}

function updateBook(){
    book = {
        isbn: $("#txtIsbn").val(),
        title: $("#txtTitle").val(),
        year: $("#cbxYear").val(),
        editorialFK: getEditorial( $("#cbxEditorial").val() )
    }

    let body = JSON.stringify(book);
    $.ajax({
        url: URL_BASE + "/book/update",
        type: "PUT",
        datatype: "JSON",
        data: body,
        contentType: "application/json;charset=UTF-8"
    })
    .done( function(response){
        console.log( response );
        alert("Libro actualizado");
        getAllBooks();
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in updateBook. "+textStatus);
        alert("Falla actualizando el libro");
    });
    closeModal();
}

function deleteBook(isbn){
    $.ajax({
        url: URL_BASE + "/book/delete/"+isbn,
        type: "DELETE",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        alert("Libro eliminado");
        getAllBooks();
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in deleteBook with isbn "+isbn+". "+textStatus);
        alert("Falla eliminando el libro de isbn "+isbn)
    });
}

// Llamado al servicio que trae las editoriales
let editorials;
function getAllEditorials(){
    $.ajax({
        async: false,
        url: URL_BASE + "/editorial/all",
        type: "GET",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        editorials = response;
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in getAllEditorials. " + textStatus)
    });
    return editorials;
}

function getEditorial(idEditorial){
    $.ajax({
        async: false,
        url: URL_BASE + "/editorial/id/"+idEditorial,
        type: "GET",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        editorial = response;
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in getEditorial with "+idEditorial+". "+textStatus);
    });
    return editorial;
}