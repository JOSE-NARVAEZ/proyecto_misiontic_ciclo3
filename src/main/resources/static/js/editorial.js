// Variables globales
let URL_BASE = "http://localhost:8080";
let editorial = null;

//Ejecuta cuando se carga la pagina
$(document).ready(function (){
    getAllEditorials();
    $("#modalNewEditorial").hide();
});

//Acciones con el Modal
function openModal(id){
    if(id==-1){ // Activar para agregar
        $("#btnAdd").show();
        $("#btnUpdate").hide();
        $("#txtId").val(-1);
        $("#txtName").val("");
        $("#txtCountry").val("");
    }
    else { // Activar para actualizar
        $("#btnAdd").hide();
        $("#btnUpdate").show();
        editorial = getEditorial(id);
        $("#txtId").val( editorial.idEditorial );
        $("#txtName").val( editorial.name );
        $("#txtCountry").val( editorial.country );
    }
    $("#modalNewEditorial").show();
}

function closeModal(){
    $("#modalNewEditorial").hide();
}

// Actualizar la tabla de datos
function updateDataGrid(items){
    $("#tblEditorials").find("tr:gt(0)").remove();
    let data = "";
    for(let i = 0; i < items.length; i++){
        data += "<tr>";
        data += "<td>" + items[i].name + "</td>";
        data += "<td>" + items[i].country + "</td>";
        data += "<td><span onclick=\"openModal(" + items[i].idEditorial + ")\">A</span></td>";
        data += "<td><span onclick=\"deleteEditorial(" + items[i].idEditorial + ")\">E</span></td>";
        data += "</tr>";
    }
    $("#tblEditorials > tbody:last-child").append(data);
}

// Llamado a backend para CRUD
function getAllEditorials(){
    $.ajax({
        url: URL_BASE + "/editorial/all",
        type: "GET",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        updateDataGrid( response );
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in getAllEditorials. " + textStatus)
        alert("Falla obteniendo todas las editoriales");
    });
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

function insertEditorial(){
    editorial = {
        name: $("#txtName").val(),
        country: $("#txtCountry").val()
    }

    let body = JSON.stringify(editorial);
    $.ajax({
        url: URL_BASE + "/editorial/insert",
        type: "POST",
        datatype: "JSON",
        data: body,
        contentType: "application/json;charset=UTF-8"
    })
    .done( function(response){
        console.log( response );
        alert("Editorial agregada");
        getAllEditorials();
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in insertEditorial. "+textStatus);
        alert("Falla creando la nueva editorial");
    });
    closeModal();
}

function updateEditorial(){
    editorial = {
        idEditorial:$("#txtId").val(),
        name: $("#txtName").val(),
        country: $("#txtCountry").val()
    }

    let body = JSON.stringify(editorial);
    $.ajax({
        url: URL_BASE + "/editorial/update",
        type: "PUT",
        datatype: "JSON",
        data: body,
        contentType: "application/json;charset=UTF-8"
    })
    .done( function(response){
        console.log( response );
        alert("Editorial actualizada");
        getAllEditorials();
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in updateEditorial. "+textStatus);
        alert("Falla actualizando la editorial");
    });
    closeModal();
}

function deleteEditorial(idEditorial){
    $.ajax({
        url: URL_BASE + "/editorial/delete/"+idEditorial,
        type: "DELETE",
        datatype: "JSON"
    })
    .done( function(response){
        console.log( response );
        alert("Editorial eliminada");
        getAllEditorials();
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error in deleteEditorial with id "+idEditorial+". "+textStatus);
        alert("Falla eliminando la editorial")
    });
}