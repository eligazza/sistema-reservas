window.addEventListener('load', function () {

    //Formulario donde están los datos que el usuario pudo haber modificado del paciente
    const form_update = document.querySelector('#update_paciente_form');
 
    form_update.addEventListener('submit', function (event) {
 
        let pacienteId = document.querySelector('#paciente_id').value;
 
        // Creamos un JSON con los datos del paciente, pero incluimos el ID para que haya update y no post
        const carga = {
            id: document.querySelector('#paciente_id').value,
            dni: document.querySelector('#dni').value,
            nombre: document.querySelector('#nombre-paciente').value,
            apellido: document.querySelector('#apellido-paciente').value,
            fechaDeAlta: document.querySelector('#fecha-de-alta').value,
            domicilio: {
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

    })
})
 

// Función que se invoca cuando se hace clic en el ID del paciente: 
function findBy(id) {

    let url = '/pacientes/'+id;

    let payload = {
        method: 'GET'
    }

    fetch(url,payload)

    .then(response => response.json())

    .then(data => {

        let paciente = data;
        document.querySelector('#paciente_id').value = paciente.id;
        document.querySelector('#dni').value = paciente.dni;
        document.querySelector('#nombre-paciente').value = paciente.nombre;
        document.querySelector('#apellido-paciente').value = paciente.apellido;
        document.querySelector('#domicilio-calle').value = paciente.domicilio.calle;
        document.querySelector('#domicilio-numero').value = paciente.domicilio.numero;
        document.querySelector('#domicilio-localidad').value = paciente.domicilio.localidad;
        document.querySelector('#domicilio-provincia').value = paciente.domicilio.provincia;

        //el formulario por default está oculto y al editar se habilita
        document.querySelector('#div_paciente_updating').style.display = "block";
    })

    .catch(error => {alert("Error: " + error);
    })

}