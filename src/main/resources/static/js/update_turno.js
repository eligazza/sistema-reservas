window.addEventListener('load', function () {

    // Capturo el formulario de update que obtengo con la función de más abajo y al div que lo contiene
    const form_update = document.querySelector('#update_turno_form');
    const div_formulario = document.querySelector('#div_turno_updating');
 
    form_update.addEventListener('submit', function (event) {

        event.preventDefault();

        let turnoId = document.querySelector('#turno_id').value;

        // Creamos un JSON con los datos del odontologo, pero incluimos el ID para que haya update y no post
        let carga = {
            id: turnoId,
            idOdontologo: document.querySelector('#dropdown_odontologos').value,
            idPaciente: document.querySelector('#id_paciente')    
        }
        
        // Hacemos fetch
        let url = '/turnos'; 
        let payload = { 
            method: 'PUT', 
            headers: { 
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(carga)
        }
        
        fetch(url,payload)
        
        .then(response => response.json())
        
        .then(data => {
            
            if (data.mensaje) {alert(data.mensaje)}
            else {
                alert("Turno modificado con éxito")
                location.reload();
            }
            
        })
        
        .catch(error => alert(error))
        
    })

    let botonCancelar = document.querySelector('#btn_cancelar');
    botonCancelar.addEventListener('click', function(event) {
        
        event.preventDefault();
        div_formulario.style.display = "none";
        
    })
})



// Función que se invoca cuando se hace clic en el ID del odontologo: 
function findBy(id) {

    let url = '/turnos/'+id;
    let payload = {
        method: 'GET'
    }

    fetch(url,payload)
    
    .then(response => response.json())
    
    .then(data => {
    
        if(data.mensaje) {alert(data.mensaje)}
        else {
            
            // Hago aparecer el formulario para el update.
            document.querySelector('#div_turno_updating').style.display = "block";
            // Le "precargo" todos los boxes con la información que viene del getById()
            document.querySelector('#turno_id').value = data.id;
            document.querySelector('#dropdown_odontologos2').value = data.odontologo.id;
            document.querySelector('#dropdown_odontologos2').text = data.odontologo.apellido;
            document.querySelector('#fecha').value = data.fecha;

        }
        
    })

    .catch(error => alert(error))
}