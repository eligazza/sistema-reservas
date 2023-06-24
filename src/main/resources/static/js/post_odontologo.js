window.addEventListener('load', function() {

    // Capturo los formularios al cargar la página
    let form = document.querySelector('#add_new_odontologo');

    // Preparo el fetch
    form.addEventListener('submit', function(e) {

        // Me anticipo al comportamiento por default
        e.preventDefault();

        // praparo el JSON
        let carga = {
            nombre: document.querySelector('#nombre-odontologo').value,
            apellido: document.querySelector('#apellido-odontologo').value,
            matricula: document.querySelector('#matricula').value,
        }; 
        
        // preparo el payload
        let url = '/odontologos';
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
                '<strong></strong> Odontologo agregado </div>';
            document.querySelector('#response-odontologo').innerHTML = successAlert;
            document.querySelector('#response-odontologo').style.display = 'block';*/
            if (data.error) {alert(data.error)}
            else {
                alert('Odontologo registrado con éxito');
                resetOdontologoForm();
            }
        })
        
        .catch(error => { 
            // alerta en caso de error
            /*let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong>' + error + '</strong> </div>';
            document.querySelector('#response-odontologo').innerHTML = errorAlert;
            document.querySelector('#response-odontologo').style.display = "block";*/
            alert(error);
        })

    })     

});

function resetOdontologoForm(){
    document.querySelector('#nombre-odontologo').value = "";
    document.querySelector('#apellido-odontologo').value = "";
    document.querySelector('#matricula').value = "";
}

