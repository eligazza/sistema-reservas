//El evento load se ejecuta al cargar la página que muestra la lista de películas 
window.addEventListener('load', function () {

    (function() {
        // obtengo la lista de pacientes
        let url = '/pacientes';
        let payload = {
        method: 'GET'
        }

        fetch(url,payload)

        .then(response => response.json())

        .then(data => {
            
            //recorremos la colección de pacientes que viene en JSON
            for(paciente of data){
            
                // Cada paciente tendrá una fila que contiene como filaID el mismo ID del paciente. 
                // Así, la fila=1 será del paciente=1. Esto permitirá borrarla si eliminamos el paciente
                let table = document.querySelector('#pacienteTable');  // selecciono la tabla
                let pacienteRow = table.insertRow(); // inserto la fila
                let trow_id = 'tr_' + paciente.id; // tomo el id del paciente
                pacienteRow.id = trow_id; // le pongo a la fila el mismo id
            
                // Cada fila tendrá...

                // ...un botón que invoca la función "deleteBy()" escrita en el delete_paciente.js que permite hacer un DELETE request.
                let deleteButton = '<button' +
                    'id= btn_delete_' + paciente.id + '\"' +
                    ' type="button" onclick="deleteBy(' + paciente.id + ')"' +
                    'class="btn btn-danger btn_delete">&times' + 
                    '</button>';

                // ...un botón que invoca la función "findBy" escrita en update_paciente.js que permite hacer un UPDATE request.
                let updateButton = '<button' +
                    ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                    ' type="button" onclick="findBy('+paciente.id+')"' + 
                    ' class="btn btn-info btn_id">' + paciente.id +
                    '</button>';
            
                // Por ultimo, rellenamos la fila con la información que vino de la API (estamos en un foreach de la respuesta al GET request)         
                pacienteRow.innerHTML = 
                    '<td>' + updateButton + '</td>' + // colocamos el boton de modificar (id) en la primera columna
                    '<td class=\"td_dni\">' + paciente.dni + '</td>' + //  el paciente.dni en la columna dni
                    '<td class=\"td_nombre\">' + paciente.nombre + '</td>' + // el paciente.nombre en la columna nombre
                    '<td class=\"td_apellido\">' + paciente.apellido + '</td>' + // el paciente.apellido en la columna apellido
                    '<td>' + deleteButton + '</td>'; //como última columna, el botón eliminar
            };

        })
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/peliculaList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
})
 
