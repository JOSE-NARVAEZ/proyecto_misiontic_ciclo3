// Variables globales
let URL_BASE = "http://localhost:8080";
let author = null;

// Ejecuta cuando se carga la pagina
$(document).ready(function(){
    getAllAuthors();
    $("#modalNewAuthor").hide();
});

// Acciones con el modal
function openModal(code){
    if(code==-1){ // Activar para agregar
        $("#modalTitle").html("Nuevo Autor");
        $("#btnAdd").show();
        $("#btnUpdate").hide();
        $("#txtCode").val("");
        $("#txtCode").prop("disabled", false);
        $("#txtNames").val("");
        $("#txtLastnames").val("");
    }
    else { // Activar para actualizar
        $("#modalTitle").html("Actualizar Autor");
        $("#btnAdd").hide();
        $("#btnUpdate").show();
        author = getAuthor(code);
        $("#txtCode").val( author.code );
        $("#txtCode").prop("disabled", true);
        $("#txtNames").val( author.name );
        $("#txtLastnames").val( author.lastname );
    }
    $("#modalNewAuthor").show();
}

function closeModal(){
    $("#modalNewAuthor").hide();
}


// Actualizar la tabla de datos
function updateDataGrid(authors){
    $("#tblAuthors").find("tr:gt(0)").remove();
    let data = "";
    for(let i = 0; i < authors.length; i++){
        data += "<tr>";
            data += "<td>" + authors[i].code + "</td>";
            data += "<td>" + authors[i].name + "</td>";
            data += "<td>" + authors[i].lastname + "</td>";
            data += "<td><span onclick=\"openModal(" + authors[i].code + ")\">A</span></td>";
            data += "<td><span onclick=\"deleteAuthor(" + authors[i].code + ")\">E</span></td>";
        data += "</tr>";
    }
    $("#tblAuthors > tbody:last-child").append(data);
}


// Llamado a backend para CRUD
function getAllAuthors(){
    $.ajax({
        url: URL_BASE + "/author/all",
        type: "GET",
        datatype: "JSON"
    })
    .done( function(response){
        console.log(response);
        updateDataGrid(response);
    })
   .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in getAllAuthors. "+textStatus);
        alert("Falla obteniendo todos los autores");
   });
}


function getAuthor(code){
    $.ajax({
        async: false,
        url: URL_BASE + "/author/code/"+code,
        type: "GET",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        author = response;
    })
   .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in getAuthor with "+code+". "+textStatus);
   });
   return author;
}

function insertAuthor(){
    author = {
        code: $("#txtCode").val(),
        name: $("#txtNames").val(),
        lastname: $("#txtLastnames").val()
    }

    let body = JSON.stringify(author);
    $.ajax({
        url: URL_BASE + "/author/insert",
        type: "POST",
        datatype: "JSON",
        data: body,
        contentType: "application/json;charset=UTF-8"
    })
    .done( function(response){
        console.log( response );
        alert("Autor creado");
        getAllAuthors();
    })
   .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in insertAuthor. " + textStatus);
        alert("Falla creando autor.");
   });
   closeModal();
}

function updateAuthor(){
    author = {
            code: $("#txtCode").val(),
            name: $("#txtNames").val(),
            lastname: $("#txtLastnames").val()
    }

    let body = JSON.stringify(author);
    $.ajax({
        url: URL_BASE + "/author/update",
        type: "PUT",
        datatype: "JSON",
        data: body,
        contentType: "application/json;charset=UTF-8"
    })
    .done( function(response){
        console.log( response );
        alert("Autor actualizado");
        getAllAuthors();
    })
   .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in updateAuthor. "+textStatus);
        alert("Falla actualizando el autor");
   });
   closeModal();
}

function deleteAuthor(code){
    $.ajax({
        url: URL_BASE + "/author/delete/"+code,
        type: "DELETE",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        alert("Autor eliminado");
        getAllAuthors();
    })
   .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in deleteAuthor with "+code+". "+textStatus);
        alert("Falla intentando eliminar el autor "+code);
   });
}