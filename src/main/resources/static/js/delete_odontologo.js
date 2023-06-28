function deleteBy(id) {

    let url = "/odontologos/" + id;

    let payload = {
        method: "DELETE"
    }

    fetch(url, payload)
    
    .then(response => response.json())
    
    .then(data => {

        if (data.mensaje) {alert(data.mensaje)}
        
        else {
            
            // muestro por consola el odontologo eliminado
            console.log("Odontologo eliminado: " + data)
            // muestro en un cartel que fue eliminado
            alert("Odontologo eliminado");
            // elimino la fila de la tabla
            let filaSeleccionada = document.querySelector("#tr_"+ id);
            filaSeleccionada.remove()
        
        }
    })
    
    .catch(error => alert(error))

}