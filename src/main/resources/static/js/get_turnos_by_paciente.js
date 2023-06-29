//El evento load se ejecuta al cargar la página que muestra la lista de pacientes 
window.addEventListener('load', function () {

    let id_paciente = document.querySelector('#id_paciente').value;
    let btn_turnosList = document.querySelector('#btn_turnosList');
    let div_turnos_table = document.querySelector('#div_turnos_table');

    btn_turnosList.addEventListener('click', function() {

        let urlTurnosByPaciente = `/turnos?paciente-id=${document.querySelector('#id_paciente').value}`;
        let payload = {
            method: 'GET'
        }

        fetch(urlTurnosByPaciente,payload)

        .then(response => response.json())

        .then(data => {

            if (data.mensaje) {alert(data.mensaje)}
            else {

                div_turnos_table.style.display = "block";

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
                    '<td class=\"td_hora\">' + turno.hora + '</td>' + // el paciente.apellido en la columna apellido
                    '<td>' + deleteButton + '</td>'; //como última columna, el botón eliminar

                });
            }

        })

    })

})
 
