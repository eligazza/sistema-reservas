window.addEventListener('load', function() {

    // Capturo los formularios al cargar la página
    let form = document.querySelector('#add_new_paciente');

    // Preparo el fetch
    form.addEventListener('submit', function(e) {

        // Me anticipo al comportamiento por default
        e.preventDefault();

        // praparo el JSON
        let carga = {
            dni: document.querySelector('#dni').value,
            nombre: document.querySelector('#nombre-paciente').value,
            apellido: document.querySelector('#apellido-paciente').value,
            domicilio: {
                calle: document.querySelector('#domicilio-calle').value,
                numero: document.querySelector('#domicilio-numero').value,
                localidad: document.querySelector('#domicilio-localidad').value,
                provincia: document.querySelector('#domicilio-provincia').value
            } 
        };

        // preparo el payload
        let url = '/pacientes';
        let payload = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(carga)
        }

        // hago el fetch
        fetch(url, payload)

        .then(response => response.json())
        
        .then(data => {
            // alerta en caso de éxito
            /*let successAlert = '<div class="alert alert-success alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong></strong> Paciente agregado </div>';
            document.querySelector('#response-paciente').innerHTML = successAlert;
            document.querySelector('#response-paciente').style.display = 'block';*/
            
            if (data.mensaje) {alert(data.mensaje)}
            else {
                alert("Paciente registrado con éxito");
                resetPacienteForm();
            }
            
        })
        
        .catch(error => { 
            // alerta en caso de error
            /*let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong>' + error + '</strong> </div>';
            document.querySelector('#response-paciente').innerHTML = errorAlert;
            document.querySelector('#response-paciente').style.display = "block";*/
            alert(error);
        })

    })     

});


function resetPacienteForm(){
    document.querySelector('#dni').value = "";
    document.querySelector('#nombre-paciente').value = "";
    document.querySelector('#apellido-paciente').value = "";
    document.querySelector('#domicilio-calle').value = "";
    document.querySelector('#domicilio-numero').value = "";
    document.querySelector('#domicilio-localidad').value = "";
    document.querySelector('#domicilio-provincia').value = "";
}

