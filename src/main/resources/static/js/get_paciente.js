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
            
                //por cada paciente armaremos una fila con ID, que luego nos permitirá borrarla si eliminamos el paciente
                var table = document.querySelector('#pacienteTable');  //selecciono la tabla
                var pacienteRow = table.insertRow(); //inserto la fila
                let trow_id = 'tr_' + paciente.id; // tomo el id del paciente
                pacienteRow.id = trow_id; //le pongo a la fila el mismo id
            
                // Cada paciente tendrá... 


                //Un botón que invoca la función "deleteByKey" de JS que llamará a la API para eliminar el paciente
                let deleteButton = '<button' +
                    'id= btn_delete_' + paciente.id + '\"' +
                    ' type="button" onclick="deleteBy(' + paciente.id + ')"' +
                    'class="btn btn-danger btn_delete">&times' + 
                    '</button>';

                //Un botón que invoca la función "findBy" de JS que buscará el paciente a modificar y mostrar sus datos en el form.
                let updateButton = '<button' +
                    ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                    ' type="button" onclick="findBy('+paciente.id+')"' + 
                    ' class="btn btn-info btn_id">' + paciente.id +
                    '</button>';
            
                //armamos la fila           
                pacienteRow.innerHTML = 
                    '<td>' + updateButton + '</td>' + //colocamos el boton de modificar (id) en la primera columna
                    '<td class=\"td_dni\">' + paciente.dni + '</td>' + //luego los datos de la paciente
                    '<td class=\"td_nombre\">' + paciente.nombre + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido + '</td>' +
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
 
