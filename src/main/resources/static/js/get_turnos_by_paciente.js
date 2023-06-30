window.addEventListener('load', function() {
    
    document.querySelector('#btn_listar').addEventListener('click', function (e) {

        // Variables
        let id_paciente = document.querySelector('#id_paciente').value;
        let div_turnos_table = document.querySelector('#div_turnos_table');
        
        // Tiro un error si no seleccionó al paciente o si es negativo
        // if(id_paciente == "" || id_paciente < 1) {
        //     alert("Ingrese el número de paciente para consultar")
        // }

        // Primero refresco la lista (sino se acumulan con cada click que haga en "listar")
        document.querySelector('#turnosTable').innerHTML = `<thead>
        <tr>
             <th></th>
             <th>Paciente</th>
             <th>Odontologo</th>
             <th>Fecha</th>
             <th>Hora</th>
             <th></th>
        </tr>
        </thead>
        <tbody id="turnosTableBody"></tbody>`;

        
        

        let urlTurnosByPaciente = `/turnos?paciente-id=${id_paciente}`;
        let payload = {
            method: 'GET'
        }

        fetch(urlTurnosByPaciente)

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

 
