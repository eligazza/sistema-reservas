window.addEventListener('load', function() {

    let url = '/odontologos'
    let payload = {
        method: 'GET'
    }
    
    fetch(url, payload)

    .then(response => response.json())
    
    .then(data => {
        
        if (data.mensaje) {alert(data.mensaje)}
        else { 
            //console.log(data);
            let dropdown = document.querySelector('#dropdown_odontologos');
            let dropdown2 = document.querySelector('#dropdown_odontologos2');
            
            // iteramos la lista de odontologos y populamos el menu desplegable
            data.forEach(odontologo => {
                let opcion = document.createElement('option');
                opcion.value = odontologo.id;
                opcion.text = `${odontologo.apellido}, ${odontologo.nombre}`;
                dropdown.appendChild(opcion);
            })
        }

    })

    .catch(error => alert(error))

});
