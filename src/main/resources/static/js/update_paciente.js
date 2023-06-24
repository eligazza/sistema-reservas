window.addEventListener('load', function () {

    // Capturo el formulario de update que obtengo con la función de más abajo y al div que lo contiene
    const form_update = document.querySelector('#update_paciente_form');
    const div_formulario = this.document.querySelector('#div_paciente_updating');
 
    form_update.addEventListener('submit', function (event) {

        event.preventDefault();
 
        let pacienteId = document.querySelector('#paciente-id').value;
 
        // Creamos un JSON con los datos del paciente, pero incluimos el ID para que haya update y no post
        const carga = {
            id: pacienteId,
            dni: document.querySelector('#dni').value,
            nombre: document.querySelector('#nombre-paciente').value,
            apellido: document.querySelector('#apellido-paciente').value,
            fechaDeAlta: document.querySelector('#fecha-de-alta').value,
            domicilio: {
                id: document.querySelector('#domicilio-id').value,
                calle: document.querySelector('#domicilio-calle').value,
                numero: document.querySelector('#domicilio-numero').value,
                localidad: document.querySelector('#domicilio-localidad').value,
                provincia: document.querySelector('#domicilio-provincia').value
            }
        };
 
        // Hacemos fetch
        const url = '/pacientes'; 
        const payload = { 
            method: 'PUT', 
            headers: { 
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(carga)
        }
 
        fetch(url,payload)
        
        .then(response => response.json())
        
        .then(data => {

            if (data.error) {alert(data.error)}
            else {
                alert("Paciente modificado con éxito")
                // Una vez mandado el update, oculto el div que contiene el formulario de update
                //div_formulario.style.display = "none";
                location.reload();
            }
        
        })
        
        .catch(error => alert(error))

    })

    const botonCancelar = document.querySelector('#btn-cancelar');
    botonCancelar.addEventListener('click', function(event) {

        event.preventDefault();
        div_formulario.style.display = "none";
        
    })
})
 

// Función que se invoca cuando se hace clic en el ID del paciente: 
function findBy(id) {

    let urlFind = '/pacientes/'+id;
    let payload = {
        method: 'GET'
    }

    fetch(urlFind,payload)
    
    .then(response => response.json())
    
    .then(data => {
    
        if (data.error) {alert(data.error)}
        else {
            // Hago aparecer el formulario para el update. Este formulario tiene el id #update_paciente_form
            document.querySelector('#div_paciente_updating').style.display = "block";
            // Le "precargo" todos los boxes con la información que viene del getById()
            document.querySelector('#paciente-id').value = data.id;
            document.querySelector('#dni').value = data.dni;
            document.querySelector('#nombre-paciente').value = data.nombre;
            document.querySelector('#apellido-paciente').value = data.apellido;
            document.querySelector('#fecha-de-alta').value = data.fechaDeAlta;
            document.querySelector('#domicilio-id').value = data.domicilio.id;
            document.querySelector('#domicilio-calle').value = data.domicilio.calle;
            document.querySelector('#domicilio-numero').value = data.domicilio.numero;
            document.querySelector('#domicilio-localidad').value = data.domicilio.localidad;
            document.querySelector('#domicilio-provincia').value = data.domicilio.provincia;
        }
    })
    .catch(error => alert(error))
}