window.addEventListener('load', function() {

    // Capturo el form
    let form = document.querySelector('#add_new_turno');

    // Escucho el submit
    form.addEventListener('submit', function(event) {

        event.preventDefault();

        // Convierto la selección de horario en tipo Time
        let horaString = document.querySelector('#hora').value;
        let horaTime = new Date(horaString);
    

        // Preparo el fetch
        let url = '/turnos'
        let carga = {
            fecha : fecha = document.querySelector('#fecha').value,
            hora: horaString,
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