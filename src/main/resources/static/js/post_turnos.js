window.addEventListener('load', function() {

    // Capturo el form
    let form = document.querySelector('#add_new_turno');

    // Escucho el submit
    form.addEventListener('submit', function(event) {

        event.preventDefault();

        // Preparo el fetch
        let url = '/turnos'
        let carga = {
            fecha : fecha = document.querySelector('#fecha').value,
            hora: document.querySelector('#hora').value,
            idOdontologo : document.querySelector('#dropdown_odontologos').value,
            idPaciente : document.querySelector('#id_paciente').value
        }
        let payload = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(carga)
        }

        fetch(url, payload)

        .then(response => response.json())

        .then(data => {

            console.log(data)
            if(data.mensaje) {alert(data.mensaje)}
            else {
                alert('¡Turno agendado!')
                resetTurnoForm();
            }
        })
        
        .catch(error => {alert(error)})

    })

})


function resetTurnoForm() {
    document.querySelector('#dropdown_odontologos').value = "";
    document.querySelector('#fecha').value = "";
}