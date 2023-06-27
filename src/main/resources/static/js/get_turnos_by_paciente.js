//El evento load se ejecuta al cargar la página que muestra la lista de pacientes 
window.addEventListener('load', function () {

    let id_paciente = document.querySelector('id_paciente').value;
    let btn_turnosList = document.querySelector('#btn_turnosList');
    let div_turnos_table = document.querySelector('#div_turnos_table');

    btn_turnosList.addEventListener('click', function() {

        let urlTurnosByPaciente = '/turnos?paciente-id=' + id_paciente;
        let payload = {
            method: 'GET'
        }

        fetch(url,payload)

        .then(response => response.json())

        .then(data => {

            if (data.mensaje) {alert(data.mensaje)}
            else {

                div_turnos_table.style.display = "block";

                console.log(data);
                data.forEach(turno => {
                
                    // Añado una fila
                    let tabla = document.querySelector('#turnosTable');
                    let turnoRow = tabla.insertRow();
                    let row_id = 'row_' + turno.id;
                    turnoRow.id = row_id;
                
                    // Coloco el boton para modificar
                    let deleteButton = '<button' +
                    'id= btn_delete_' + turno.id + '\"' +
                    ' type="button" onclick="deleteBy(' + turno.id + ')"' +
                    'class="btn btn-danger btn_delete">&times' + 
                    '</button>';

                    // Coloco el boton para eliminar
                    let updateButton = '<button' +
                    ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                    ' type="button" onclick="findBy('+ turno.id+')"' + 
                    ' class="btn btn-info btn_id">' + turno.id +
                    '</button>';

                    // Relleno la fila
                    turnoRow.innerHTML =
                    '<td>' + updateButton + '</td>' + // colocamos el boton de modificar (id) en la primera columna
                    '<td class=\"td_paciente\">' + turno.paciente.apellido + '</td>' + //  el paciente.dni en la columna dni
                    '<td class=\"td_odontologo\">' + turno.odontologo.apellido + '</td>' + // el paciente.nombre en la columna nombre
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' + // el paciente.apellido en la columna apellido
                    '<td>' + deleteButton + '</td>'; //como última columna, el botón eliminar

                });
            }

        })

    })

    (function() {
        
        // obtengo la lista de pacientes
        let url = '/turnos';
        let payload = {
            method: 'GET'
        }

        fetch(url,payload)

        .then(response => response.json())

        .then(data => {

            if (data.mensaje) {alert(data.mensaje)}
            else {
                
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
            }

        })
    })

})
 
