window.addEventListener('load', function () {

    // Capturo el formulario de update que obtengo con la función de más abajo y al div que lo contiene
    const form_update = document.querySelector('#update_odontologo_form');
    const div_formulario = this.document.querySelector('#div_odontologo_updating');
 
    form_update.addEventListener('submit', function (event) {

        event.preventDefault();

        let odontologoId = document.querySelector('#odontologo-id').value;

        // Creamos un JSON con los datos del odontologo, pero incluimos el ID para que haya update y no post
        const carga = {
            id: odontologoId,
            nombre: document.querySelector('#nombre-odontologo').value,
            apellido: document.querySelector('#apellido-odontologo').value,
            matricula: document.querySelector('#matricula').value,
        }
        
        // Hacemos fetch
        const url = '/odontologos'; 
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
                alert("odontologo modificado con éxito")
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



// Función que se invoca cuando se hace clic en el ID del odontologo: 
function findBy(id) {

    let urlFind = '/odontologos/'+id;
    let payload = {
        method: 'GET'
    }

    fetch(urlFind,payload)
    
    .then(response => response.json())
    
    .then(data => {
    
        if (data.error) {alert(data.error)}
        else {
            // Hago aparecer el formulario para el update. Este formulario tiene el id #update_odontologo_form
            document.querySelector('#div_odontologo_updating').style.display = "block";
            // Le "precargo" todos los boxes con la información que viene del getById()
            document.querySelector('#odontologo-id').value = data.id;
            document.querySelector('#nombre-odontologo').value = data.nombre;
            document.querySelector('#apellido-odontologo').value = data.apellido;
            document.querySelector('#matricula').value = data.matricula;
        }
    })

    .catch(error => alert(error))
}