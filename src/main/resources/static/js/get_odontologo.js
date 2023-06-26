//El evento load se ejecuta al cargar la página que muestra la lista de odontologos 
window.addEventListener('load', function () {

    (function() {
        
        // obtengo la lista de odontologos
        let url = '/odontologos';
        let payload = {
            method: 'GET'
        }

        fetch(url,payload)

        .then(response => response.json())

        .then(data => {

            if (data.mensaje) {alert(data.mensaje)}
            
            else {
                
                //recorremos la colección de odontologos que viene en JSON
                for(odontologo of data){
                
                    // Cada odontologo tendrá una fila que contiene como filaID el mismo ID del odontologo. 
                    // Así, la fila=1 será del odontologo=1. Esto permitirá borrarla si eliminamos el odontologo
                    let table = document.querySelector('#odontologoTable');  // selecciono la tabla
                    let odontologoRow = table.insertRow(); // inserto la fila
                    let trow_id = 'tr_' + odontologo.id; // tomo el id del odontologo
                    odontologoRow.id = trow_id; // le pongo a la fila el mismo id
                
                    // Cada fila tendrá...

                    // ...un botón que invoca la función "deleteBy()" escrita en el delete_odontologo.js que permite hacer un DELETE request.
                    let deleteButton = '<button' +
                        'id= btn_delete_' + odontologo.id + '\"' +
                        ' type="button" onclick="deleteBy(' + odontologo.id + ')"' +
                        'class="btn btn-danger btn_delete">&times' + 
                        '</button>';

                    // ...un botón que invoca la función "findBy" escrita en update_odontologo.js que permite hacer un UPDATE request.
                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                        ' type="button" onclick="findBy('+odontologo.id+')"' + 
                        ' class="btn btn-info btn_id">' + odontologo.id +
                        '</button>';
                
                    // Por ultimo, rellenamos la fila con la información que vino de la API (estamos en un foreach de la respuesta al GET request)         
                    odontologoRow.innerHTML = 
                        '<td>' + updateButton + '</td>' + // colocamos el boton de modificar (id) en la primera columna
                        '<td class=\"td_nombre\">' + odontologo.nombre + '</td>' + // el odontologo.nombre en la columna nombre
                        '<td class=\"td_apellido\">' + odontologo.apellido + '</td>' + // el odontologo.apellido en la columna apellido
                        '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' + //  el odontologo.dni en la columna dni
                        '<td>' + deleteButton + '</td>'; //como última columna, el botón eliminar
                };
            }

        })
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/odontologoList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
})
 
